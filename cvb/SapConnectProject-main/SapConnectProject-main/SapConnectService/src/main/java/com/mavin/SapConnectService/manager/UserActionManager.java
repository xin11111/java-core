package com.mavin.SapConnectService.manager;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.mavin.SapConnectService.dto.UserActionDto;
import com.mavin.SapConnectService.entity.UserAction;
import com.mavin.SapConnectService.mapper.UserActionMapper;
import com.mavin.SapConnectService.repository.IUserActionRepository;

@Service
@Transactional
public class UserActionManager {
	@Autowired
	private IUserActionRepository userActionRepository;
	@Autowired
	private UserActionMapper userActionMapper;
	
	@Async
	public void save(UserActionDto criteria) {
		UserAction userAction = Optional.of(criteria).map(userActionMapper::dtoToEntity).orElse(null);
		if(userAction != null) {
			userActionRepository.save(userAction);
		}
	}
}
