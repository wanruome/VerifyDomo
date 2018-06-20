package com.newpay.bankcardverify.entry;

import java.io.Serializable;

/**
 * @author ykz 银行卡账号验证
 */
public class CardNameCid implements Serializable {

	/**
	 * 订单流水号
	 */
	private String orderNo;

	/**
	 * 订单流水时间
	 */
	private String orderTime;
	private String cardNo; // 银行卡号
	private String name; // 姓名
	private String cid; // 身份证号

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
