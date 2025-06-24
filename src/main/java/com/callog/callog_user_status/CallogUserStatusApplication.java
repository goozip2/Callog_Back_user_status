package com.callog.callog_user_status;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CallogUserStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallogUserStatusApplication.class, args);
	}

}
