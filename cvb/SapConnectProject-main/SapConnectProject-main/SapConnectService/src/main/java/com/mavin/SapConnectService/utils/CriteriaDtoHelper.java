package com.mavin.SapConnectService.utils;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

@Component
public class CriteriaDtoHelper {
	public Object trimAllTextInput(Object obj) {
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for(Field field : fields) {
				if(field.getType() == String.class) {
					String valueString = (String) field.get(obj);
					field.set(obj, valueString.trim());
				}
				
				//kiểm tra nếu field class là Dto thì tiếp tục  trim nested object
				if(field.getType().getName().contains("DTO")) {
					Object fieldValue = field.get(obj);
					Object trimObj = trimAllTextInput(fieldValue);
					field.set(obj, trimObj);
				}
			}
			return obj;
		} catch (Exception e) {
			return obj;
		}
	}
}
