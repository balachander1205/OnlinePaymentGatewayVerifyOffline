package com.dhfl.OnlinePaymentGatewayVerifyOffline.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	@Value("${pg.config.merchant.key}")
	private String merchantKey;
	
	@Value("${pg.config.merchant.iv}")
	private String merchantIv;
	
	@Value("${pg.config.merchant.code}")
	public String merchantCode;
	
	@Value("${pg.config.merchant.cur}")
	public String merchantCur;
	
	@Value("${pg.config.merchant.webservice}")
	public String merchantWebServiceUrl;

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getMerchantIv() {
		return merchantIv;
	}

	public void setMerchantIv(String merchantIv) {
		this.merchantIv = merchantIv;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getMerchantCur() {
		return merchantCur;
	}

	public void setMerchantCur(String merchantCur) {
		this.merchantCur = merchantCur;
	}

	public String getMerchantWebServiceUrl() {
		return merchantWebServiceUrl;
	}

	public void setMerchantWebServiceUrl(String merchantWebServiceUrl) {
		this.merchantWebServiceUrl = merchantWebServiceUrl;
	}
	
}
