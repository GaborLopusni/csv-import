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
