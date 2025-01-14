package com.mavin.SapConnectService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "user_action")
@EqualsAndHashCode(callSuper=false)
public class UserAction extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "log_key", nullable = true)
    private String logKey;
	
	@Column(name = "user_name", nullable = true)
    private String user;
	
	@Column(name = "platform", nullable = true)
    private String platform;
	
	@Column(name = "model", nullable = true)
    private String model;
	
	@Column(name = "sys_ver", nullable = true)
    private String systemVersion;
	
	@Column(name = "method_name", nullable = true)
    private String methodName;
	
	@Column(name = "input", nullable = true, columnDefinition = "TEXT")
    private String input;
	
	@Column(name = "output", nullable = true, columnDefinition = "TEXT")
    private String output;
}
