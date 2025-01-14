package com.mavin.SapConnectService.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mavin.SapConnectService.config.SapConfigProperties;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;

@Component
public class SapConnectionUtils {
	@Autowired
	protected SapConfigProperties sapProperties;
	
//	private String destinationName;
//	private JCoDestination destination;
	
//	public SapConnectionUtils() {
//		destinationName = generateDestinationName(sapProperties.getClient());
//		destination = getDestination();
//	}
	
	public void initDestination() {
        Properties connectProperties = new Properties();
        
        String ashost = sapProperties.getAshost();
        String sysnr = sapProperties.getSysnr();
        String client = sapProperties.getClient();
        String user = sapProperties.getUser();
        String password = sapProperties.getPassword();
        String lang = sapProperties.getLang();
        
        ashost = EncryptUtils.decrypt(ashost);
        password = EncryptUtils.decrypt(password);
        
        if(StringUtils.isAnyBlank(ashost, sysnr, client, user, password, lang))
        	throw new IllegalArgumentException("Cannot create destination file");
        
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, ashost);
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  sysnr);
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, client);
        connectProperties.setProperty(DestinationDataProvider.JCO_USER,   user);
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, password);
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   lang);
        
        String destinationName = generateDestinationName(sapProperties.getClient());
        createDestinationDataFile(destinationName, connectProperties);
	}
	
	private String generateDestinationName(String client) {
		if(StringUtils.isBlank(client))
			throw new IllegalArgumentException("Client not available");
		
		StringBuilder sb = new StringBuilder();
		sb = sb.append("SAP_SYSTEM").append("_").append(client);
		
		return sb.toString();
	}
	
	public JCoDestination getDestination() {
		String destinationName = generateDestinationName(sapProperties.getClient());
		try {
			return JCoDestinationManager.getDestination(destinationName);
		} catch (JCoException e) {
			try {
				initDestination();
				return JCoDestinationManager.getDestination(destinationName);
			} catch (JCoException ex) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public void createDestinationDataFile(String destinationName, Properties connectProperties) {
		File destCfg = new File(destinationName+".jcoDestination");
		try {
			FileOutputStream fos = new FileOutputStream(destCfg, false);
			connectProperties.store(fos, "destination for sap connect!");
			fos.close();
		} catch (Exception e)  {
			throw new RuntimeException("Unable to create thedestination files", e);
		}
	 }
	
	public JCoFunction getFunction(String functionName) {
		try {
			return getDestination().getRepository().getFunction(functionName);
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * TODO: Set import parameters
	 * @param function - function call
	 * @param param - param as key-value
	 */
	public void setParam(JCoFunction function, Map<String, Object> params) {
		for (var param : params.entrySet()) {
			function.getImportParameterList().setValue(param.getKey(), param.getValue());
		}
	}
	
	public void funcExecute(JCoFunction func) {
		try {
			func.execute(getDestination());
		} catch (JCoException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public JCoTable getDataTable(JCoFunction function, String tableName) {
		try {
			function.execute(getDestination());
			return function.getTableParameterList().getTable(tableName);
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
