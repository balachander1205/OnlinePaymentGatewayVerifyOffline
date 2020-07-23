package com.dhfl.OnlinePaymentGatewayVerifyOffline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlinePaymentGatewayVerifyOfflineApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinePaymentGatewayVerifyOfflineApplication.class, args);
	}
}
