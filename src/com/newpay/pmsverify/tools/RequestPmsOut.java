/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月15日 下午3:36:46
 */
package com.newpay.pmsverify.tools;

public class RequestPmsOut {

	private String outbatch;
	private String outdate;
	private String crttime;
	private String mchtno;
	// private String outcount;
	private String outamt;
	private String settleAcctNm;
	private String settleAcctNo;
	private String settleBankNm;
	private String settleBankNo;
	private String groupFlag;
	private String outRemarks;
	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

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

	public String getCrttime() {
		return crttime;
	}

	public void setCrttime(String crttime) {
		this.crttime = crttime;
	}

	public String getMchtno() {
		return mchtno;
	}

	public void setMchtno(String mchtno) {
		this.mchtno = mchtno;
	}

	// public String getOutcount() {
	// return outcount;
	// }
	//
	// public void setOutcount(String outcount) {
	// this.outcount = outcount;
	// }

	public String getOutamt() {
		return outamt;
	}

	public void setOutamt(String outamt) {
		this.outamt = outamt;
	}

	public String getSettleAcctNm() {
		return settleAcctNm;
	}

	public void setSettleAcctNm(String settleAcctNm) {
		this.settleAcctNm = settleAcctNm;
	}

	public String getSettleAcctNo() {
		return settleAcctNo;
	}

	public void setSettleAcctNo(String settleAcctNo) {
		this.settleAcctNo = settleAcctNo;
	}

	public String getSettleBankNm() {
		return settleBankNm;
	}

	public void setSettleBankNm(String settleBankNm) {
		this.settleBankNm = settleBankNm;
	}

	public String getSettleBankNo() {
		return settleBankNo;
	}

	public void setSettleBankNo(String settleBankNo) {
		this.settleBankNo = settleBankNo;
	}

	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	public String getOutRemarks() {
		return outRemarks;
	}

	public void setOutRemarks(String outRemarks) {
		this.outRemarks = outRemarks;
	}

}
