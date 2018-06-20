/**
 *	@copyright wanruome-2017
 * 	@author wanruome
 * 	@create 2017年3月23日 下午3:13:34
 */
package com.newpay.bankcardverify.entry;

public class CardName {
	/**
	 * 订单流水号
	 */
	private String orderNo;

	/**
	 * 订单流水时间
	 */
	private String orderTime;
	private String				cardNo;				// 银行卡号
	private String				name;				// 姓名
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


}
