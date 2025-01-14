package com.mavin.SapConnectService.sapManager;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveListCommands.LSetCommand;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mavin.SapConnectService.common.DatePatternEnum;
import com.mavin.SapConnectService.dto.ZFIR0033Dto;
import com.mavin.SapConnectService.manager.BaseManager;
import com.mavin.SapConnectService.utils.DateTimeUtils;
import com.mavin.SapConnectService.utils.SapConnectionUtils;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;

@Service
public class ZFIR0033SapManager extends BaseManager{
	@Autowired
	private SapConnectionUtils sapConnection;
	
	public List<ZFIR0033Dto> getData(String companyCode, LocalDate from, LocalDate to) {
		JCoFunction function = sapConnection.getFunction("ZFM_ZFIR0033_GET");
		if(function == null)
			throw new RuntimeException(messageHelper.getText("error.sap.func.not-found"));
		
		String fromString = DateTimeUtils.toDateString(from, DatePatternEnum.YYYYMMDD.pattern);
		String toString = DateTimeUtils.toDateString(to, DatePatternEnum.YYYYMMDD.pattern);
		int year = from.getYear();
		
		Map<String,Object> params = new HashMap<>();
		params.put("COMPANY_CODE", companyCode);
		params.put("HKONT_FROM", "1110000000");
		params.put("HKONT_TO", "9999999999");
		params.put("YEAR", year);
		params.put("FROM", fromString);
		params.put("TO", toString);
		params.put("SIZE", 50000);
		
		
		int page = 0;
		List<ZFIR0033Dto> listItem = new ArrayList<ZFIR0033Dto>();
		do {
			params.put("PAGE", page);
			sapConnection.setParam(function, params);
			
        	List<ZFIR0033Dto> list = getDataPagination(function, page);
        	if (list == null || CollectionUtils.isEmpty(list)) break;
        	
        	listItem.addAll(list);
        	page++;
        } while (1 > 0);
		
		return listItem;
	}
	
	public List<ZFIR0033Dto> getDataPagination(JCoFunction function, int page){
		sapConnection.funcExecute(function);
		JCoStructure returnStructure = function.getExportParameterList().getStructure("RETURN");
		if (!(returnStructure.getString("TYPE").equals("")||returnStructure.getString("TYPE").equals("S")) ) {
			throw new RuntimeException(returnStructure.getString("MESSAGE")); 
		}
        
		JCoTable codes = sapConnection.getDataTable(function, "IT_TABLE");
		List<ZFIR0033Dto> list = mapFromTable(codes);
		codes.clear();
		
		return list;
	}
	
	
	public List<ZFIR0033Dto> mapFromTable(JCoTable table){
		List<ZFIR0033Dto> listItem = new ArrayList<ZFIR0033Dto>();
		
		for (int i = 0; i < table.getNumRows(); i++) {
			table.setRow(i);
			ZFIR0033Dto model = new ZFIR0033Dto();
            Field[] fields = model.getClass().getDeclaredFields();
            
            
        	
        	for(Field field : fields) {
        		try {
        			field.setAccessible(true);
        			String fieldName = field.getName();
        			Class fieldType = field.getType();
        			if(fieldType.equals(String.class)) {
        				String value = table.getString(fieldName);
                		field.set(model, value);
        			} else if (fieldType.equals(Double.class)) {
						Double value = table.getDouble(fieldName);
						field.set(model, value);
					} else if(fieldType.equals(LocalDate.class)) {
						try {
							String dateString = table.getString(fieldName);
							LocalDate valueDate = DateTimeUtils.toLocalDate(dateString, DatePatternEnum.YYYY_MM_DD.pattern);
							field.set(model, valueDate);
						} catch (Exception e) {
							field.set(model, null);
						}
					} else if(fieldType.equals(Integer.class)) {
						Integer value = table.getInt(fieldName);
                        field.set(model, value);
					} else {
						field.set(model, null);
					}
        			
            		field.setAccessible(false);
        		} catch (Exception e) {
        			continue;
        		}
        	}
        	
        	String companyCode = model.getBUKRS();
            String year = model.getGJAHR().toString();
            String period = model.getMONAT().toString();
            String matdoc = model.getBELNR();
            String item = model.getBUZEI();
        	
        	model.setId(generateId(companyCode, year, period, matdoc, item));
        	listItem.add(model);
        }
		
		return listItem;
	}
	
	public String generateId(String companyCode, String year, String period, String matdoc, String item) {
		if(StringUtils.isAnyBlank(companyCode, year, period, matdoc, item))
			return null;
		
		StringBuilder builder = new StringBuilder();
		builder = builder.append(companyCode).append("_").append(year).append("_").append(period).append("_").append(matdoc).append("_").append(item);
		return builder.toString();
	}
}
