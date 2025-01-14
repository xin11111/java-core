package com.mavin.SapConnectService.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.keycloak.jose.jws.JWSInput;
import org.keycloak.jose.jws.JWSInputException;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
	@Autowired
	private KeycloakUtils keyCloakUtils;
	
	public String getUserName(HttpServletRequest request) {
    	log.info("entering getAccessToken()... ");
    	
    	final String token = getJwtFromRequest(request);
    	if (StringUtils.isNotBlank(token)) {
            JWSInput input;
			try {
				input = new JWSInput(token);
	        	AccessToken resulToken = input.readJsonContent(AccessToken.class);
	        	return resulToken.getPreferredUsername();
			} catch (JWSInputException e) {
				log.error("ERROR: {}", e);
			}
    	}
    	return null;
    }
    
	public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
	
	public List<String> getRoles(HttpServletRequest request){
		log.info("entering getGroupRoles()... ");
		final String token = getJwtFromRequest(request);
		if (StringUtils.isNotBlank(token)) {
            JWSInput input;
			try {
				input = new JWSInput(token);
	        	AccessToken resulToken = input.readJsonContent(AccessToken.class);
	        	return new ArrayList<String>(resulToken.getResourceAccess(keyCloakUtils.getClientName()).getRoles());
			} catch (JWSInputException e) {
				log.error("ERROR: {}", e);
			}
    	}
		return null;
	}
	
	public Boolean isAdmin(HttpServletRequest request) {
		log.info("entering isAdmin()... ");
        List<String> roles = getRoles(request);
        if (CollectionUtils.isEmpty(roles)) return false;
        
        String roleAdmin = roles.stream().filter(r -> r.contains("ADMIN")).findFirst().orElse(null);
        if(roleAdmin == null) return false;
        return true;
	}
	
}
