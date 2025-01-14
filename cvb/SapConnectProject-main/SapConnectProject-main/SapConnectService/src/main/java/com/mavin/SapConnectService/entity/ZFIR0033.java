package com.mavin.SapConnectService.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "ZFIR0033")
@EqualsAndHashCode(callSuper=false)
public class ZFIR0033{
	@Id
	private String id;
	
	@Column(name = "BUKRS", nullable = false, columnDefinition="VARCHAR(4)")
	private String BUKRS;
	
	@Column(name = "GJAHR", nullable = false)
	private Integer GJAHR;
	
	@Column(name = "MONAT", nullable = false)
	private Integer MONAT;
	
	@Column(name = "BELNR", nullable = false, columnDefinition="VARCHAR(10)")
	private String BELNR;
	
	@Column(name = "BUZEI", nullable = false, columnDefinition="VARCHAR(6)")
	private String BUZEI;
	
	@Column(name = "HKONT", columnDefinition="VARCHAR(10)")
	private String HKONT; 
	
	@Column(name = "RLDNR") 
	private String RLDNR; 
	
	@Column(name = "SHKZG") 
	private String SHKZG; 
	
	@Column(name = "BUZEI_9") 
	private String BUZEI_9; 
	
	@Column(name = "GSBER") 
	private String GSBER; 
	
	@Column(name = "XNEGPD") 
	private String XNEGPD; 
	
	@Column(name = "BUZEIK") 
	private String BUZEIK; 
	
	@Column(name = "NPDDI") 
	private String NPDDI; 
	
	@Column(name = "KOARTK") 
	private String KOARTK; 
	
	@Column(name = "KREDIT") 
	private String KREDIT; 
	
	@Column(name = "GSBERK") 
	private String GSBERK; 
	
	@Column(name = "BUZEID") 
	private String BUZEID; 
	
	@Column(name = "NPDKI") 
	private String NPDKI; 
	
	@Column(name = "KOARTD") 
	private String KOARTD; 
	
	@Column(name = "DEBET") 
	private String DEBET; 
	
	@Column(name = "GSBERD") 
	private String GSBERD; 
	
	@Column(name = "DMBTR") 
	private Double DMBTR; 
	
	@Column(name = "HWAER", columnDefinition="VARCHAR(5)") 
	private String HWAER; 
	
	@Column(name = "WRBTR") 
	private Double WRBTR; 
	
	@Column(name = "WAERS", columnDefinition="VARCHAR(5)") 
	private String WAERS; 
	
	@Column(name = "BLART") 
	private String BLART; 
	
	@Column(name = "BLDAT") 
	private LocalDate BLDAT; 
	
	@Column(name = "BUDAT") 
	private LocalDate BUDAT; 
	
	@Column(name = "USNAM") 
	private String USNAM; 
	
	@Column(name = "XBLNR") 
	private String XBLNR; 
	
	@Column(name = "STBLG") 
	private String STBLG; 
	
	@Column(name = "BKTXT") 
	private String BKTXT; 
	
	@Column(name = "KURSF") 
	private Double KURSF; 
	
	@Column(name = "AUGBL") 
	private String AUGBL; 
	
	@Column(name = "MWSKZ") 
	private String MWSKZ; 
	
	@Column(name = "VALUT") 
	private LocalDate VALUT; 
	
	@Column(name = "DZUONR") 
	private String DZUONR; 
	
	@Column(name = "SGTXT") 
	private String SGTXT; 
	
	@Column(name = "KOSTL") 
	private String KOSTL; 
	
	@Column(name = "AUFNR") 
	private String AUFNR; 
	
	@Column(name = "KUNNR") 
	private String KUNNR; 
	
	@Column(name = "LIFNR") 
	private String LIFNR; 
	
	@Column(name = "TXT50") 
	private String TXT50; 
	
	@Column(name = "NAME1") 
	private String NAME1; 
	
	@Column(name = "NAME2") 
	private String NAME2; 
	
	@Column(name = "NAME3") 
	private String NAME3; 
	
	@Column(name = "NAME4") 
	private String NAME4; 
	
	@Column(name = "AMOUNT_DE") 
	private Double AMOUNT_DE; 
	
	@Column(name = "AMOUNT_CR") 
	private Double AMOUNT_CR; 
	
	@Column(name = "XREVERSING") 
	private String XREVERSING; 
	
	@Column(name = "XREVERSED") 
	private String XREVERSED; 
	
	@Column(name = "REVERSE_DATE") 
	private String REVERSE_DATE; 
	
	@Column(name = "STJAH") 
	private Integer STJAH; 
	
	@Column(name = "AWREF_REV") 
	private String AWREF_REV; 
	
	@Column(name = "AWORG_REV") 
	private String AWORG_REV;
}
