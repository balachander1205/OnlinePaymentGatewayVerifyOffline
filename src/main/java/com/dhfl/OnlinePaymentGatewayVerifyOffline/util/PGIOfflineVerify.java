package com.dhfl.OnlinePaymentGatewayVerifyOffline.util;

import com.tp.pg.util.TransactionRequestBean;

public class PGIOfflineVerify {
	public static void main(String[] args) {
		getTxNdetails();
	}
	public static String getTxNdetails() {
		String key = "3330290559QVHYCU";
		String iv = "8168854229WUSGEL";
		TransactionRequestBean objTransactionRequestBean = new TransactionRequestBean();
		objTransactionRequestBean.setStrRequestType("O");
		objTransactionRequestBean.setStrMerchantCode("T519384");
		objTransactionRequestBean.setMerchantTxnRefNumber("TXN112000009781594541927978");
		objTransactionRequestBean.setStrAmount("1.00");
		objTransactionRequestBean.setStrCurrencyCode("INR");
		objTransactionRequestBean.setStrReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrS2SReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrShoppingCartDetails("FIRST_1.0_0.0");
		objTransactionRequestBean.setTxnDate("12-07-2020");
		objTransactionRequestBean
				.setWebServiceLocator("https://www.tpsl-india.in/PaymentGateway/services/TransactionDetailsNew");
		objTransactionRequestBean.setCustID("");
		objTransactionRequestBean.setStrTPSLTxnID("");
		objTransactionRequestBean.setStrMobileNumber("");
		objTransactionRequestBean.setKey(key.getBytes());
		objTransactionRequestBean.setIv(iv.getBytes());
		objTransactionRequestBean.setStrCustomerName("");
		objTransactionRequestBean.setStrEmail("");
		String token = objTransactionRequestBean.getTransactionToken();
		System.out.println("Token :--->> " + token);
		return token;
	}
	
	public static String doOfflineVerify(String key, String iv, String webUrl, String txnId, 
			String merchantCode, String merchantCur, String txnDate, String mobileNumber, String custName) {
		TransactionRequestBean objTransactionRequestBean = new TransactionRequestBean();
		objTransactionRequestBean.setStrRequestType("O");
		objTransactionRequestBean.setStrMerchantCode(merchantCode);
		objTransactionRequestBean.setMerchantTxnRefNumber(txnId);
		objTransactionRequestBean.setStrAmount("1.00");
		objTransactionRequestBean.setStrCurrencyCode(merchantCur);
		objTransactionRequestBean.setStrReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrShoppingCartDetails("FIRST_1.0_0.0");
		objTransactionRequestBean.setTxnDate(txnDate);
		objTransactionRequestBean.setWebServiceLocator(webUrl);
		objTransactionRequestBean.setCustID("");
		objTransactionRequestBean.setStrTPSLTxnID("");
		objTransactionRequestBean.setStrMobileNumber(mobileNumber);
		objTransactionRequestBean.setKey(key.getBytes());
		objTransactionRequestBean.setIv(iv.getBytes());
		objTransactionRequestBean.setStrCustomerName(custName);
		objTransactionRequestBean.setStrEmail("");
		String token = objTransactionRequestBean.getTransactionToken();
		System.out.println("Token :--->> " + token);
		return token;
	}

}
