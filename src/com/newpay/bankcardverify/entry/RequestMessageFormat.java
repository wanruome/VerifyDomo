package com.newpay.bankcardverify.entry;


public class RequestMessageFormat {

	//商户号
	private String m;
	//商户号明文
	private String merchantId;
	//工作密钥密文
	private String k;
	//工作密钥明文
	private String dataTKey;
	//数据密文
	private String d;
	//数据明文
	private String data;

	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getDataTKey() {
		return dataTKey;
	}
	public void setDataTKey(String dataTKey) {
		this.dataTKey = dataTKey;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
