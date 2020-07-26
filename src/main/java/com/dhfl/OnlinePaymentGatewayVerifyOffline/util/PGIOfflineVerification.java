package com.dhfl.OnlinePaymentGatewayVerifyOffline.util;

import com.tp.pg.util.TransactionRequestBean;

public class PGIOfflineVerification {
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
		objTransactionRequestBean.setStrAmount("");
		objTransactionRequestBean.setStrCurrencyCode("INR");
		objTransactionRequestBean.setStrShoppingCartDetails("FIRST_1.0_0.0");
		objTransactionRequestBean.setTxnDate("12-07-2020");
		objTransactionRequestBean
				.setWebServiceLocator("https://www.tpsl-india.in/PaymentGateway/services/TransactionDetailsNew");
		objTransactionRequestBean.setCustID("");
		objTransactionRequestBean.setStrTPSLTxnID("1165219539");
		objTransactionRequestBean.setStrMobileNumber("");
		objTransactionRequestBean.setKey(key.getBytes());
		objTransactionRequestBean.setIv(iv.getBytes());
		objTransactionRequestBean.setStrCustomerName("");
		objTransactionRequestBean.setStrEmail("myname@domain.com");
		String token = objTransactionRequestBean.getTransactionToken();
		System.out.println("Token :--->> " + token.length());
		return token;
	}
}
