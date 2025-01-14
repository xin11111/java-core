package com.mavin.SapConnectService.mapper;

import org.mapstruct.Mapper;

import com.mavin.SapConnectService.dto.ComponentDescDto;
import com.mavin.SapConnectService.entity.ComponentDesc;

@Mapper(componentModel = "spring")
public interface IComponentDescMapper {
	ComponentDescDto entityToDto(ComponentDesc entity);
	ComponentDesc dtoToEntity(ComponentDescDto dto);
}
