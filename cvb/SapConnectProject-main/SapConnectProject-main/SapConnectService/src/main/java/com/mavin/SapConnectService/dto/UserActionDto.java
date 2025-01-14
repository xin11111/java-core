package com.mavin.SapConnectService.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserActionDto extends BaseDto{
	private Long id;
    private String logKey;
    private String user;
    private String platform;
    private String model;
    private String systemVersion;
    private String methodName;
    private String input;
    private String output;
}
