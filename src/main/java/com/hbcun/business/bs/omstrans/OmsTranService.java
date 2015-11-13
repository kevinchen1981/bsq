package com.hbcun.business.bs.omstrans;

import com.hbcun.business.bs.omstrans.entity.Authors;
import com.hbcun.business.bs.omstrans.entity.SubmitOrderRQ;
import com.hbcun.business.bs.omstrans.entity.SubmitOrderRS;

public interface OmsTranService {
	
	String AuthorIDConfirm(Authors author);
	
	SubmitOrderRS SubmitOrder(SubmitOrderRQ request);

}
