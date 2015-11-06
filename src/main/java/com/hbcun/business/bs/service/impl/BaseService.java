package com.hbcun.business.bs.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.order.entity.vo.OrderProductSimpleVo;
import com.hbcun.business.order.service.OrderInfoService;
import com.hbcun.business.order.service.OrderPackageService;
import com.hbcun.business.product.entity.vo.interfacee.ProductListSimpleVo;
import com.hbcun.business.product.service.ProductPackageService;
import com.hbcun.business.product.service.ProductService;
import com.hbcun.common.dao.mapper.OrderPackageMapper;
import com.hbcun.common.dao.mapper.OrderPackageProductMapper;
import com.hbcun.common.entity.po.OrderInfo;
import com.hbcun.common.entity.po.OrderPackage;
import com.hbcun.common.entity.po.ProductPackage;
import com.hbcun.common.sys.exception.InvalidRequestException;
import com.hbcun.common.sys.util.NumberFormatUtils;

public abstract class BaseService {

	protected static final Logger logger = Logger.getLogger(BaseService.class);
	
	/**
	 * 币制（人民币）
	 */
	protected static final String currCode = "142";

	/**
	 * 关区代码    杭州经济技术开发区海关驻出口加工区办事处（下沙）
	 */
	protected static final String customsCode = "2991";
	
	
	
	@Autowired
	protected OrderInfoService orderInfoService;
	
	@Autowired
	protected OrderPackageMapper orderPackageMapper;

	@Autowired
	protected OrderPackageService orderPackageService;
	
	@Autowired
	protected OrderPackageProductMapper orderPackageProductMapper;

	@Autowired
	protected ProductPackageService productPackageService;

	@Autowired
	protected ProductService productService;
	
	
	
	/**
	 * 获取订单信息
	 * @param orderId
	 * @return
	 */
	protected OrderInfo getOrder(String orderId){
		
		if(StringUtils.isEmpty(orderId)) {
			throw new InvalidRequestException("orderId不能为空");
		}
		return orderInfoService.selectByPrimaryKey(orderId);
	}
	
	/**
	 * 获取包裹信息
	 * @param packageId
	 * @return
	 */
	protected OrderPackage getOrderPackage(String packageId) {
		
		if(StringUtils.isEmpty(packageId)) {
			throw new InvalidRequestException("packageId不能为空");
		}
		return orderPackageMapper.selectByPrimaryKey(packageId);
	}

	/**
	 * 获取包裹商品数据
	 * @param packageId
	 * @return
	 */
	protected List<OrderProductSimpleVo> getOrderPackageProduct(String packageId) {
		if(StringUtils.isEmpty(packageId)) {
			throw new InvalidRequestException("packageId不能为空");
		}
		return orderPackageService.getPackageRealBuyProductByPackageId(packageId);
	}
	
	/**
	 * 返回商品Id数组
	 * @param list
	 * @return
	 */
	protected List<String> getProductIdArray(List<OrderProductSimpleVo> list) {
		
		Set<String> productIdArray = new HashSet<String>();
		for (OrderProductSimpleVo vo : list) {
			productIdArray.add(vo.getProductId());
		}
		return new ArrayList<String>(productIdArray);
	}
	
	/**
	 * 返回商品信息,如果商品是套装，由套装中的商品替换
	 * @param list
	 * @return
	 */
	protected PackageProductVo getPackageProductVo(List<OrderProductSimpleVo> list) {
		
		Set<String> productIdArray = new HashSet<String>();
		for (OrderProductSimpleVo vo : list) {
			productIdArray.add(vo.getProductId());
		}
		List<String> productIdlist = new ArrayList<String>(productIdArray);
		Map<String, ProductPackage> package2Product = productPackageService.getPackage2Product(productIdlist); //套装
		Map<String, OrderProductSimpleVo> returnMap = new HashMap<>();
		for (OrderProductSimpleVo op : list) {
			returnMap.put(op.getProductId(), op);
		}
		PackageProductVo vo = new PackageProductVo();
		//说明商品都不是套装
		if(package2Product.isEmpty()) {
			
			vo.addAll(productIdArray);
			vo.setMap(returnMap);
		} else {
			List<String> tempProductIdArray = new ArrayList<>(package2Product.size());
			for (ProductPackage pp : package2Product.values()) {
				tempProductIdArray.add(pp.getProductId());
			}
			List<ProductListSimpleVo> productList = productService.getSimpleProductList(false, tempProductIdArray, false);
			//有商品是套装
			List<String> packageIdArray = new ArrayList<String>(package2Product.keySet());	//套装的商品
			productIdArray.removeAll(packageIdArray);
			OrderProductSimpleVo op = null;
			OrderProductSimpleVo packageProduct = null;
			ProductListSimpleVo productSimpleVo = null;
			for (ProductPackage pp : package2Product.values()) {
				
				for (int i = 0; i < productList.size(); i++) {
					productSimpleVo = productList.get(i);
					if(!productSimpleVo.getProductId().equals(pp.getProductId())) {
						continue;
					}
				}
				//将套装移除
				packageProduct = returnMap.remove(pp.getProductPackageId());
				//添加新的商品
				op = new OrderProductSimpleVo();
				op.setProductId(pp.getProductId());
				op.setTitle(productSimpleVo != null ? productSimpleVo.getTitle() : packageProduct.getTitle());
				op.setBuyCount(packageProduct.getBuyCount() * pp.getPackageNum());
				op.setPrice(NumberFormatUtils.formatNumberDivide(packageProduct.getPrice(), pp.getPackageNum()));
				returnMap.put(pp.getProductId(), op);
				productIdArray.add(pp.getProductId());
			}
			vo.addAll(productIdArray);
			vo.setMap(returnMap);
		}
		return vo;
	}
	
	public static class PackageProductVo {
		
		private List<String> productIdArray = new ArrayList<String>();
		
		private Map<String, OrderProductSimpleVo> map = new HashMap<>();

		public List<String> getProductIdArray() {
			return productIdArray;
		}

		public void addAll(Collection<? extends String> productIdArray) {
			this.productIdArray.addAll(productIdArray);
		}

		public Map<String, OrderProductSimpleVo> getMap() {
			return map;
		}

		public void setMap(Map<String, OrderProductSimpleVo> map) {
			this.map.putAll(map);
		}
		
	}
}
