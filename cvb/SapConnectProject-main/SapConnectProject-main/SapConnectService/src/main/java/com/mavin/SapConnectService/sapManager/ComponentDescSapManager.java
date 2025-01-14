package com.mavin.SapConnectService.sapManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mavin.SapConnectService.dto.ComponentDescDto;
import com.mavin.SapConnectService.manager.BaseManager;
import com.mavin.SapConnectService.utils.SapConnectionUtils;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

@Service
public class ComponentDescSapManager extends BaseManager{
	@Autowired
	private SapConnectionUtils sapConnection;
	
	public List<ComponentDescDto> getData(String structureName) {
		JCoFunction function = sapConnection.getFunction("ZFM_COMP_GET");
		if(function == null)
			throw new RuntimeException(messageHelper.getText("error.sap.func.not-found"));
		
		Map<String,Object> params = new HashMap<>();
		params.put("STRUC_NAME", structureName);
		sapConnection.setParam(function, params);
		
		sapConnection.funcExecute(function);
		JCoTable table = sapConnection.getDataTable(function, "IT_TABLE");
		
		List<ComponentDescDto> listItem = new ArrayList<ComponentDescDto>();
		for (int i = 0; i < table.getNumRows(); i++) {
			table.setRow(i);
			ComponentDescDto model = new ComponentDescDto();
			
			model.setComponent(table.getString("COMP"));
			model.setDescription(table.getString("HEADING"));
			listItem.add(model);
		}
		
		return listItem;
	}
}
