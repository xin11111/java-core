package com.mavin.SapConnectService.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class KeycloakUtils {
	@Autowired
    Environment env;
	
	public String getClientName() {
		return env.getProperty("keycloak.resource");
	}
}
