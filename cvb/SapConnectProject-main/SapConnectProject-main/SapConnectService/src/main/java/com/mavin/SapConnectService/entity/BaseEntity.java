package com.mavin.SapConnectService.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
	@Column(name = "is_deleted", nullable = true)
	private Boolean isDeleted;
	
	@Column(name = "created_by", nullable = true)
	private String createdBy;
	
	@Column(name = "created_at", nullable = true)
	private LocalDateTime createdAt;
	
	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;
	
	@Column(name = "modified_at", nullable = true)
	private LocalDateTime modifiedAt;
	
	@PrePersist
    public void prePersist() {
    	setCreatedAt(LocalDateTime.now());
		setModifiedAt(LocalDateTime.now());
		
		if(isDeleted == null) {
			setIsDeleted(false);
		}
    }
	
	@PreUpdate
    public void preUpdate() {
    	setModifiedAt(LocalDateTime.now());
    }
}
