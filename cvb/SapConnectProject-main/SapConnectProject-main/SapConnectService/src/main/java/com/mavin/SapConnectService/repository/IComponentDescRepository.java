package com.mavin.SapConnectService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mavin.SapConnectService.entity.ComponentDesc;


public interface IComponentDescRepository extends JpaRepository<ComponentDesc, Long>, JpaSpecificationExecutor<ComponentDesc>{
	List<ComponentDesc> findByComponentIn(List<String> components);
}
