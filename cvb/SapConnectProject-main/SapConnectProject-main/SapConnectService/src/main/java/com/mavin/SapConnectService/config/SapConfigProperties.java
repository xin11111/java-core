package com.mavin.SapConnectService.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("sap")
public class SapConfigProperties {
	private String ashost;
	private String sysnr;
	private String client;
	private String user;
	private String password;
	private String lang;
	
}
