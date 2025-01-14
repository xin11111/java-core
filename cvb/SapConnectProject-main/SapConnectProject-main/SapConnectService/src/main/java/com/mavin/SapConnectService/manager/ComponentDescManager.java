package com.mavin.SapConnectService.manager;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavin.SapConnectService.dto.ComponentDescDto;
import com.mavin.SapConnectService.entity.ComponentDesc;
import com.mavin.SapConnectService.mapper.IComponentDescMapper;
import com.mavin.SapConnectService.repository.IComponentDescRepository;

@Service
@Transactional
public class ComponentDescManager extends BaseManager{
	@Autowired
	private IComponentDescRepository componentDescRepository;
	@Autowired
	private IComponentDescMapper componentDescMapper;
	
	public void saveALl(List<ComponentDescDto> list) {
		if(CollectionUtils.isEmpty(list)) return;
		List<String> componentNames = list.stream().map(ComponentDescDto::getComponent).collect(Collectors.toList());
		
		List<ComponentDesc> existComponentDescs = componentDescRepository.findByComponentIn(componentNames);
		
		List<ComponentDesc> listNewEntity = list.stream().map(componentDescMapper::dtoToEntity).collect(Collectors.toList());
		for(ComponentDesc entity : listNewEntity) {
			ComponentDesc existComp = existComponentDescs.stream().filter(exist -> entity.getComponent().equals(exist.getComponent())).findFirst().orElse(null);
			if(existComp == null) continue;
			
			entity.setId(existComp.getId());
		}
		
		componentDescRepository.saveAll(listNewEntity);
	}
}
