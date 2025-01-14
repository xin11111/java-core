package com.mavin.SapConnectService.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BaseDto {
	@JsonProperty("is_deleted")
	private Boolean isDeleted;
	
	@JsonProperty("created_by")
	private String createdBy;
	
	@JsonProperty("create_at")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime createdAt;
	
	@JsonProperty("modified_by")
	private String modifiedBy;
	
	@JsonProperty("modified_at")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime modifiedAt;

	private String userRequest;
	
	@JsonProperty("page")
	private Integer page;
	
	@JsonProperty("size")
	private Integer size;
	
	private Integer displayOrder;
	
}
