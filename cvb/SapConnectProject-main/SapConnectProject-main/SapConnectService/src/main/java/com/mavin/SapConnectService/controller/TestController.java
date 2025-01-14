package com.mavin.SapConnectService.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.mavin.SapConnectService.dto.ComponentDescDto;
import com.mavin.SapConnectService.dto.ZFIR0033Dto;
import com.mavin.SapConnectService.manager.ComponentDescManager;
import com.mavin.SapConnectService.manager.ZFIR0033Manager;
import com.mavin.SapConnectService.sapManager.ComponentDescSapManager;
import com.mavin.SapConnectService.sapManager.ZFIR0033SapManager;

@RestController
@RequestMapping("/api")
public class TestController extends BaseController {
	@Autowired
	private ZFIR0033SapManager sapManager;
	@Autowired
	private ZFIR0033Manager zfir0033Manager;
	@Autowired
	private ComponentDescManager componentDescManager;
	@Autowired
	private ComponentDescSapManager componentDescSapManager;
	
	
	@GetMapping("/v1/test")
	public ResponseEntity<?> test(){
		LocalDate fromDate = LocalDate.of(2021, 1, 1);
		LocalDate to = LocalDate.of(2021,12,31);
		List<ZFIR0033Dto> list = sapManager.getData("8010", fromDate, to);
		for(List<ZFIR0033Dto> subList : Lists.partition(list, 500)) {
			zfir0033Manager.saveAll(subList);
		}
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("/v1/component")
	public ResponseEntity<?> component(){
		List<ComponentDescDto> list = componentDescSapManager.getData("ZST_ZFIR0033");
		componentDescManager.saveALl(list);
		return ResponseEntity.ok("success");
	}
}
