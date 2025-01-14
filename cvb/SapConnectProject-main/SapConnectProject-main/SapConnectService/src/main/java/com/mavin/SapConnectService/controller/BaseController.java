package com.mavin.SapConnectService.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import com.mavin.SapConnectService.config.AppProperties;
import com.mavin.SapConnectService.dto.BaseDto;
import com.mavin.SapConnectService.manager.MessageResourceManager;
import com.mavin.SapConnectService.utils.JwtTokenProvider;

public class BaseController {
	@Autowired
	protected AppProperties appProperties;
	@Autowired
	private MessageResourceManager messageResourceManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public String getText(String msgKey) {
        return messageResourceManager.getText(msgKey);
    }
	
	public String getText(String msgKey, Locale locale) {
        return messageResourceManager.getText(msgKey, locale);
    }
	
	public void setDefaultPage(BaseDto base) {
		if(base.getPage() == null) {
			base.setPage(appProperties.getDefaultPage());
		}
		
		if(base.getSize() == null) {
			base.setSize(appProperties.getDefaultPageSize());
		}
	}
	
	public String getUserName(HttpServletRequest request) {
		return jwtTokenProvider.getUserName(request);
    }
    
	public String getJwtFromRequest(HttpServletRequest request) {
        return jwtTokenProvider.getJwtFromRequest(request);
    }
	
	public List<String> getRoles(HttpServletRequest request){
		return jwtTokenProvider.getRoles(request);
	}
	
	public Boolean isAdmin(HttpServletRequest request) {
		return jwtTokenProvider.isAdmin(request);
	}
}
