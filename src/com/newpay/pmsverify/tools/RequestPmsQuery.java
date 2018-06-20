/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月17日 上午10:54:42
 */
package com.newpay.pmsverify.tools;

public class RequestPmsQuery {
	private String outbatch;
	private String outdate;
	private String mchtno;
	private String sign;

	public String getOutbatch() {
		return outbatch;
	}

	public void setOutbatch(String outbatch) {
		this.outbatch = outbatch;
	}

	public String getOutdate() {
		return outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}

	public String getMchtno() {
		return mchtno;
	}

	public void setMchtno(String mchtno) {
		this.mchtno = mchtno;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
