/**
 *	@copyright wanruome-2017
 * 	@author wanruome
 * 	@create 2017年3月24日 上午9:09:23
 */
package com.newpay.bankcardverify.entry;

public class VerifyResponse {
	private String resultCode;
	private String resultMessage;
	private String orderNo;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "VerifyResponse [resultCode=" + resultCode + ", resultMessage=" + resultMessage + ", orderNo=" + orderNo
				+ "]";
	}

}
