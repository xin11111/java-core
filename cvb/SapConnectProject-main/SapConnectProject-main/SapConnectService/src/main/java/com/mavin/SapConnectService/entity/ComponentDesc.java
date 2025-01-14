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
@Table(name = "component_desc")
@EqualsAndHashCode(callSuper=false)
public class ComponentDesc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "component", nullable = false)
    private String component;
	
	@Column(name = "description", nullable = false)
    private String description;
}
