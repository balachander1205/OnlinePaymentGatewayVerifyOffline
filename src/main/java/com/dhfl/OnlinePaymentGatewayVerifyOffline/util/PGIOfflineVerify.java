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
		// objTransactionRequestBean.setStrITC("");
		// objTransactionRequestBean.setStrReturnURL("https://www.tekprocess.co.in/MerchantIntegrationClient/Responsepayload.jsp");
		objTransactionRequestBean.setStrReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrS2SReturnURL("http://localhost:1665/callback");
		objTransactionRequestBean.setStrShoppingCartDetails("FIRST_1.0_0.0");
		objTransactionRequestBean.setTxnDate("20-01-2020");
		// objTransactionRequestBean.setStrBankCode("");
		objTransactionRequestBean
				.setWebServiceLocator("https://www.tpsl-india.in/PaymentGateway/services/TransactionDetailsNew");
		objTransactionRequestBean.setCustID("");
		objTransactionRequestBean.setStrTPSLTxnID("");
		objTransactionRequestBean.setStrMobileNumber("9999999999");
		objTransactionRequestBean.setKey(key.getBytes());
		objTransactionRequestBean.setIv(iv.getBytes());
		objTransactionRequestBean.setStrCustomerName("xyz");
		objTransactionRequestBean.setStrEmail("myname@domain.com");
		String token = objTransactionRequestBean.getTransactionToken();
		System.out.println("Token :--->> " + token);
		return token;
	}

}
