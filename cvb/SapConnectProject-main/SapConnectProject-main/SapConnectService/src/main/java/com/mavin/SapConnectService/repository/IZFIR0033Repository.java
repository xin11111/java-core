package com.mavin.SapConnectService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.mavin.SapConnectService.entity.ZFIR0033;


@Repository
public interface IZFIR0033Repository extends JpaRepository<ZFIR0033, String>, JpaSpecificationExecutor<ZFIR0033>{

}
