package com.pubkart.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;

@Configuration
public class FeignConfiguration {

	@Bean
	public Logger.Level configureLogLevel() {
		return Logger.Level.FULL;
	}
	
	@Bean 
	public Request.Options timeoutConfiguration(){
	  return new Request.Options(5000, 30000);
	}
}
