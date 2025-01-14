package com.mavin.SapConnectService.manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavin.SapConnectService.dto.ZFIR0033Dto;
import com.mavin.SapConnectService.entity.ZFIR0033;
import com.mavin.SapConnectService.mapper.IZFIR0033Mapper;
import com.mavin.SapConnectService.repository.IZFIR0033Repository;

@Service
@Transactional
public class ZFIR0033Manager extends BaseManager{
	@Autowired
	private IZFIR0033Repository repository;
	@Autowired
	private IZFIR0033Mapper mapper;
	
	public ZFIR0033Dto save(ZFIR0033Dto dto) {
		ZFIR0033 entity = Optional.of(dto).map(mapper::dtoToEntity).orElse(null);
		if(entity == null) 
            throw new RuntimeException(messageHelper.getText("error.mapper"));
        
        return Optional.of(repository.save(entity)).map(mapper::entityToDto).orElse(null);
	}
	
	public void saveAll(List<ZFIR0033Dto> list) {
		if(CollectionUtils.isEmpty(list)) return;
		
		List<ZFIR0033> listEntity = list.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
		repository.saveAll(listEntity);
	}
}

