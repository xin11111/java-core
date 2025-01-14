package com.mavin.SapConnectService.common;

public enum DatePatternEnum {
	MM_DD_YYYY_1("MM/dd/yyyy"),
	MM_DD_YYYY_2("MM-dd-yyyy"),
	DD_MM_YYYY_1("dd/MM/yyyy"),
	DD_MM_YYYY_2("dd-MM-yyyy"),
	DD_MM_YYYY_3("ddMMyyyy"),
	DD_MM_YYYY_4("dd.MM.yyyy"),
	DD_MM_YYYY__HH_MM_SS("dd-MM-yyyy HH:mm:ss"),
	DD_MM_YYYY__HH_MM("dd-MM-yyyy HH:mm"),
	DD_MM_YYYY__00_00_00(" 00:00:00"),
	DD_MM_YYYY__23_59_59(" 23:59:59"),
	SECOND_START("00"),
	SECOND_END("59"),
	HH_MM("HH:mm"),
	YYYY("yyyy"),
	YYYY_MM("yyyy-MM"),
	YYYY_MM_DD("yyyy-MM-dd"),
	YYYY_MM_DD__HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
	YYYYMMDD("yyyyMMdd"),
	YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
	DDHHMMSS("ddHHmmss"),
	YYYY_MM_DD__HH_MM_SS_1("yyyy-MM-dd_HH-mm-ss"),
	YYYY_MM_DD_T_HH_MM_SS_SSSSSSXXX("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"),
	YYYY_MM_DD_HH_MM_SS_SSSSSSXXX("yyyy-MM-dd HH:mm:ss.SSSSSSXXX"),
	
	DD_MM_YYYY_00_00_00("dd/MM/yyyy 00:00:00"),
	DD_MM_YYYY_00_00_00_1("dd-MM-yyyy 00:00:00"),
	YYYY_MM_DD_00_00_00("yyyy-MM-dd 00:00:00"),
	DD_MM_YYYY_23_59_59("dd/MM/yyyy 23:59:59"),
	DD_MM_YYYY_23_59_59_1("dd-MM-yyyy 23:59:59"),
	YYYY_MM_DD_23_59_59("yyyy-MM-dd 23:59:59"),
	;

	public String pattern;

	private DatePatternEnum(String pattern) {
		this.pattern = pattern;
	}
}
