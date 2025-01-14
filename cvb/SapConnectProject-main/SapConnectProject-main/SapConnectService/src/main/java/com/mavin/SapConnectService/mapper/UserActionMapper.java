package com.mavin.SapConnectService.mapper;

import org.mapstruct.Mapper;

import com.mavin.SapConnectService.dto.UserActionDto;
import com.mavin.SapConnectService.entity.UserAction;

@Mapper(componentModel = "spring")
public interface UserActionMapper {
	UserActionDto entityToDto(UserAction entity);
	UserAction dtoToEntity(UserActionDto dto);
}
