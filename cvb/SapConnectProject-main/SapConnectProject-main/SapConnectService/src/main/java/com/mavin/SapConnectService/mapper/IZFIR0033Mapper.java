package com.mavin.SapConnectService.mapper;

import org.mapstruct.Mapper;

import com.mavin.SapConnectService.dto.ZFIR0033Dto;
import com.mavin.SapConnectService.entity.ZFIR0033;

@Mapper(componentModel = "spring")
public interface IZFIR0033Mapper {
	ZFIR0033Dto entityToDto(ZFIR0033 entity);
	ZFIR0033 dtoToEntity(ZFIR0033Dto dto);
}
