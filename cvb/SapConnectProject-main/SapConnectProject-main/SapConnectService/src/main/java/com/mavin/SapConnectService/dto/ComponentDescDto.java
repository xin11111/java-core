package com.mavin.SapConnectService.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ComponentDescDto {
	private Long id;
	private String component;
	private String description;
}
