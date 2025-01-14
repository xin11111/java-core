package com.mavin.SapConnectService.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("app")
public class AppProperties {
	private int defaultPage;
	private int defaultPageSize;
}
