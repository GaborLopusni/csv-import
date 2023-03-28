package com.challenge.csvimport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(schema = "DBO", name = "OUTPAY_HEADER")
public class OutpayHeader {

    public OutpayHeader() {
    }

    public OutpayHeader(String chdrNum, String letterType, Date printDate, String dataId, Client client, String regDate, double benPercent, String role1, String role2, String cownNum, String cownName, String notice01, String notice02, String notice03, String notice04, String notice05, String notice06, String claimId, Date tpToProcessDate) {
        this.chdrNum = chdrNum;
        this.letterType = letterType;
        this.printDate = printDate;
        this.dataId = dataId;
        this.client = client;
        this.regDate = regDate;
        this.benPercent = benPercent;
        this.role1 = role1;
        this.role2 = role2;
        this.cownNum = cownNum;
        this.cownName = cownName;
        this.notice01 = notice01;
        this.notice02 = notice02;
        this.notice03 = notice03;
        this.notice04 = notice04;
        this.notice05 = notice05;
        this.notice06 = notice06;
        this.claimId = claimId;
        TpToProcessDate = tpToProcessDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Outpay_Header_ID")
    private Long id;

    @Column(name = "Chdrnum")
    @NotNull
    private String chdrNum;

    @Column(name = "LetterType")
    @NotNull
    private String letterType;

    @Column(name = "PrintDate")
    @NotNull
    private Date printDate;

    @Column(name = "DataID")
    private String dataId;

    @Embedded
    private Client client;

    @Column(name = "RegDate")
    private String regDate;

    @Column(name = "BenPercent")
    private double benPercent;

    @Column(name = "Role1")
    private String role1;

    @Column(name = "Role2")
    private String role2;

    @Column(name = "CownNum")
    private String cownNum;

    @Column(name = "CownName")
    private String cownName;

    @Column(name = "Notice01")
    private String notice01;

    @Column(name = "Notice02")
    private String notice02;

    @Column(name = "Notice03")
    private String notice03;

    @Column(name = "Notice04")
    private String notice04;

    @Column(name = "Notice05")
    private String notice05;

    @Column(name = "Notice06")
    private String notice06;

    @Column(name = "Claim_ID")
    private String claimId;

    @Column(name = "TP2ProcessDate")
    private Date TpToProcessDate;
}
