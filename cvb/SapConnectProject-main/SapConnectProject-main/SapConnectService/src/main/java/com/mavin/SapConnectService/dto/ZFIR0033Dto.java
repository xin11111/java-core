package com.mavin.SapConnectService.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ZFIR0033Dto {
	private String id;
	private String HKONT;
    private String BUKRS;
    private String RLDNR;
    private Integer GJAHR;
    private String BELNR;
    private String SHKZG;
    private String BUZEI;
    private String BUZEI_9;
    private String GSBER;
    private String XNEGPD;
    private String BUZEIK;
    private String NPDDI;
    private String KOARTK;
    private String KREDIT;
    private String GSBERK;
    private String BUZEID;
    private String NPDKI;
    private String KOARTD;
    private String DEBET;
    private String GSBERD;
    private Double DMBTR; 
    private String HWAER;
    private Double WRBTR; 
    private String WAERS;
    private String BLART;
    private LocalDate BLDAT; 
    private LocalDate BUDAT;
    private Integer MONAT;
    private String USNAM;
    private String XBLNR;
    private String STBLG;
    private String BKTXT;
    private Double KURSF; 
    private String AUGBL;
    private String MWSKZ;
    private LocalDate VALUT;
    private String DZUONR;
    private String SGTXT;
    private String KOSTL;
    private String AUFNR;
    private String KUNNR;
    private String LIFNR;
    private String TXT50;
    private String NAME1;
    private String NAME2;
    private String NAME3;
    private String NAME4;
    private Double AMOUNT_DE;
    private Double AMOUNT_CR;
    private String XREVERSING;
    private String XREVERSED;
    private String REVERSE_DATE;
    private Integer STJAH;
    private String AWREF_REV;
    private String AWORG_REV;
}
