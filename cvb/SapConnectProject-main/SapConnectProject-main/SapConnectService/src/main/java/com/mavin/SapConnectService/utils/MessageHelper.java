package com.mavin.SapConnectService.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mavin.SapConnectService.manager.MessageResourceManager;


@Component
public class MessageHelper {
	@Autowired
	private MessageResourceManager messageResourceManager;
	
	public String getText(String msgKey) {
        return messageResourceManager.getText(msgKey);
    }
	
	public String getText(String msgKey, Locale locale) {
        return messageResourceManager.getText(msgKey, locale);
    }
	
	public String getTextWithParam(String msgKey, String...args) {
        return messageResourceManager.getTextWithParam(msgKey, args);
    }
}
