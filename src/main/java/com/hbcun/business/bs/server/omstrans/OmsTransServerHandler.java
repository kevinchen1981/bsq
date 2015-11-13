package com.hbcun.business.bs.server.omstrans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.CharSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hbcun.business.bs.constant.OmsTranConfig;
import com.hbcun.business.bs.omstrans.entity.CallBackRQ;
import com.hbcun.business.bs.omstrans.entity.CallBackRS;
import com.hbcun.business.bs.omstrans.entity.ServiceResult;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.EndOfDataDecoderException;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.ErrorDataDecoderException;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;

import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.handler.codec.http.HttpHeaders.Names.*;

public class OmsTransServerHandler extends
		SimpleChannelInboundHandler<HttpObject> {
	private final StringBuilder responseContent = new StringBuilder();
	private HttpRequest request;	

	public void messageReceived(ChannelHandlerContext ctx, HttpObject msg)
			throws Exception {
		if (msg instanceof HttpRequest) {			
			HttpRequest req = this.request = (HttpRequest) msg;
			System.out.println("handleHttpRequest method=========="
					+ req.getMethod());
			System.out
					.println("handleHttpRequest uri==========" + req.getUri());
			if (req.getMethod() == HttpMethod.GET) { // 处理get请求
				String uri = req.getUri();
				uri = uri.substring(1);
				if (uri.equals(OmsTranConfig.OmsTransServer)) {
					responseContent.append("\r\n\r\n请用Post方式请求\r\n");
					writeResponse(ctx.channel());
				} else {
					responseContent.append("\r\n\r\n无效页面\r\n");
					writeResponse(ctx.channel());
				}
			} else if (req.getMethod() == HttpMethod.POST) {
				/*decoder = new HttpPostRequestDecoder(
						new DefaultHttpDataFactory(false), req);
				boolean readingChunks = HttpHeaders
						.isTransferEncodingChunked(req);
				responseContent.append("Is Chunked: " + readingChunks + "\r\n");
				responseContent.append("IsMultipart: " + decoder.isMultipart()
						+ "\r\n");
				if (readingChunks) {
					// Chunk version
					responseContent.append("Chunks: ");
					readingChunks = true;
				}*/
			}
		}
		//if (decoder != null) {
			if (msg instanceof HttpContent) {
				// New chunk is received				
				
				HttpContent chunk = (HttpContent) msg;
				ByteBuf buf = chunk.content();				
				String str = buf.toString(CharsetUtil.UTF_8);
				StorServiceResult(str);
				/*try {
					decoder.offer(chunk);
				} catch (ErrorDataDecoderException e1) {
					e1.printStackTrace();
					responseContent.append(e1.getMessage());
					writeResponse(ctx.channel());
					ctx.channel().close();
					return;
				}
                try{
                    List<InterfaceHttpData> postList = decoder.getBodyHttpDatas();
                    // 读取从客户端传过来的参数
                    for (InterfaceHttpData data : postList) {
                        String name = data.getName();
                        //logger.info(data.toString());
                        String value = null;
                        if (InterfaceHttpData.HttpDataType.Attribute == data.getHttpDataType()) {
                            MemoryAttribute attribute = (MemoryAttribute) data;
                            attribute.setCharset(CharsetUtil.UTF_8);
                            value = attribute.getValue();
                            //logger.info("name:"+name+",value:"+value);
                        }
                    }
 
                }catch (Exception e){
                    e.printStackTrace();
                }
				responseContent.append('o');
				try {
					while (decoder.hasNext()) {
						InterfaceHttpData data = decoder.next();
						if (data != null) {
							try {
								writeHttpData(data);
							} finally {
								data.release();
							}
						}
					}
				} catch (EndOfDataDecoderException e1) {
					responseContent
							.append("\r\n\r\nEND OF CONTENT CHUNK BY CHUNK\r\n\r\n");
				}*/

				// example of reading only if at the end
				/*if (chunk instanceof LastHttpContent) {
					writeResponse(ctx.channel());
					readingChunks = false;
					reset();
				}*/
			}
		//}

		/*
		 * try{ List<InterfaceHttpData> postList = decoder.getBodyHttpDatas();
		 * // 读取从客户端传过来的参数 for (InterfaceHttpData data : postList) { String name
		 * = data.getName(); //logger.info(data.toString()); String value =
		 * null; if (InterfaceHttpData.HttpDataType.Attribute ==
		 * data.getHttpDataType()) { MemoryAttribute attribute =
		 * (MemoryAttribute) data; attribute.setCharset(CharsetUtil.UTF_8);
		 * value = attribute.getValue();
		 * //logger.info("name:"+name+",value:"+value); } }
		 * 
		 * }catch (Exception e){ e.printStackTrace(); }
		 */
		CallBackRS rs = new CallBackRS();
		rs.setResult("Success");
		JSONObject jo = JSONObject.fromObject(rs);
		ByteBuf buf = copiedBuffer(jo.toString(), CharsetUtil.UTF_8);
		FullHttpResponse response = new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
		response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");
		boolean close = request.headers().contains(CONNECTION,
				HttpHeaders.Values.CLOSE, true)
				|| request.getProtocolVersion().equals(HttpVersion.HTTP_1_0)
				&& !request.headers().contains(CONNECTION,
						HttpHeaders.Values.KEEP_ALIVE, true);
		if (!close) {
			// There's no need to add 'Content-Length' header
			// if this is the last response.
			response.headers().set(CONTENT_LENGTH, buf.readableBytes());
		}

		// Write the response.
		ChannelFuture future = ctx.channel().writeAndFlush(response);
		// Close the connection after the write operation is done if necessary.
		if (close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
	private void StorServiceResult(String result)
	{
		CallBackRQ rq = new CallBackRQ();
		JSONObject obj = JSONObject.fromObject(result);
		JSONArray arr = obj.getJSONArray("result");
		//token = arr.toString();
		ArrayList<ServiceResult> results = new ArrayList<ServiceResult>(); 
		for(int i=0;i<arr.size();i++)
		{
			ServiceResult sor = new ServiceResult();
			sor.setBusiness_no(arr.getJSONObject(i).getString("business_no"));
			sor.setBusiness_type(arr.getJSONObject(i).getString("business_type"));
			sor.setChk_mark(arr.getJSONObject(i).getString("chk_mark"));
			sor.setNotice_content(arr.getJSONObject(i).getString("notice_content"));
			sor.setNotice_time(arr.getJSONObject(i).getString("notice_time"));
			sor.setWay_bills(arr.getJSONObject(i).getString("way_bills"));
			results.add(sor);
		}
		//
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.channel().close();
	}	

	private void writeResponse(Channel channel) {
		// Convert the response content to a ChannelBuffer.
		ByteBuf buf = copiedBuffer(responseContent.toString(),
				CharsetUtil.UTF_8);
		responseContent.setLength(0);

		// Decide whether to close the connection or not.
		boolean close = request.headers().contains(CONNECTION,
				HttpHeaders.Values.CLOSE, true)
				|| request.getProtocolVersion().equals(HttpVersion.HTTP_1_0)
				&& !request.headers().contains(CONNECTION,
						HttpHeaders.Values.KEEP_ALIVE, true);

		// Build the response object.
		FullHttpResponse response = new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
		response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");

		if (!close) {
			// There's no need to add 'Content-Length' header
			// if this is the last response.
			response.headers().set(CONTENT_LENGTH, buf.readableBytes());
		}

		// Write the response.
		ChannelFuture future = channel.writeAndFlush(response);
		// Close the connection after the write operation is done if necessary.
		if (close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg)
			throws Exception {
		messageReceived(ctx, msg);
	}

}