/**
 *	@copyright wanruome-2017
 * 	@author wanruome
 * 	@create 2017年3月24日 上午9:07:33
 */
package com.newpay.bankcardverify.entry;

public class MessageFormat {
	private String f;
	private String r;
	private String m;
	private VerifyResponse verifyResponse;

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public VerifyResponse getVerifyResponse() {
		return verifyResponse;
	}

	public void setVerifyResponse(VerifyResponse verifyResponse) {
		this.verifyResponse = verifyResponse;
	}

	@Override
	public String toString() {
		return "MessageFormat [f=" + f + ", r=" + r + ", m=" + m + ", verifyResponse=" + verifyResponse + "]";
	}

}
