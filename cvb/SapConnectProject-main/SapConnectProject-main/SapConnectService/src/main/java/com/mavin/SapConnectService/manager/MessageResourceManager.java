package com.mavin.SapConnectService.manager;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

@Service
public class MessageResourceManager {
	@Autowired
    private MessageSource messageSource;
	
	private MessageSourceAccessor messages;
	
	@PostConstruct
    private void init() {
		messages = new MessageSourceAccessor(messageSource);
    }
	
	public String getText(String msgKey) {
		return getText(msgKey, null);
    }
	
	public String getTextWithParam(String msgKey, String... args) {
		return messages.getMessage(msgKey, args);
	}
	
	public String getText(String msgKey, Locale locale) {
		return messages.getMessage(msgKey, locale);
    }
}
