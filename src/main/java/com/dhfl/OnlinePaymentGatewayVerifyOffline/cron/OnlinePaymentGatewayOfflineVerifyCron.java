package com.dhfl.OnlinePaymentGatewayVerifyOffline.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OnlinePaymentGatewayOfflineVerifyCron {
	private static final Logger LOG = LoggerFactory.getLogger(OnlinePaymentGatewayOfflineVerifyCron.class);

	@Scheduled(cron = "0/10 * * * * ?")
	public void publish() {
		int count = 0;
		LOG.info("Average value is 0::--->>"+count++);
	}
}
