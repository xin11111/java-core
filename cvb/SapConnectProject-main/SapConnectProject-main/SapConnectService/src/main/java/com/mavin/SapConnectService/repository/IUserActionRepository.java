package com.mavin.SapConnectService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.mavin.SapConnectService.entity.UserAction;


@Repository
public interface IUserActionRepository extends JpaRepository<UserAction, Long>, JpaSpecificationExecutor<UserAction>{

}
