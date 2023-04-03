package com.challenge.csvimport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Entity class for outpay data
 */
@EqualsAndHashCode
@Entity
@Table(name = "OutPay_Header")
public class OutpayHeader {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_outpay_header")
    @SequenceGenerator(name = "seq_outpay_header", sequenceName = "\"seq_outpay_header\"", allocationSize = 1)
    @Column(name = "Outpay_Header_ID")
    private Long id;

    @Column(name = "Clntnum")
    @NotNull
    private String clientNumber;

    @Column(name = "Chdrnum")
    @NotNull
    private String chdrNum;

    @Column(name = "LetterType")
    @NotNull
    private String letterType;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "PrintDate")
    private LocalDate printDate;

    @Column(name = "DataID")
    private String dataId;

    @Column(name = "ClntName")
    private String clientName;

    @Column(name = "ClntAddress")
    private String clientAddress;

    @Column(name = "Claim_ID")
    private String claimId;

    @Column(name = "BenPercent")
    private Double benPercent;

    @Column(name = "Role1")
    private String role1;

    @Column(name = "Role2")
    private String role2;

    @Column(name = "CownNum")
    private String cownNum;

    @Column(name = "CownName")
    private String cownName;

    @Temporal(TemporalType.DATE)
    @Column(name = "RegDate")
    private LocalDate regDate;

    public OutpayHeader() {
    }

    public OutpayHeader(Long id, String clientNumber, String chdrNum, String letterType, LocalDate printDate, String dataId, String clientName, String clientAddress, String claimId, Double benPercent, String role1, String role2, String cownNum, String cownName, LocalDate regDate) {
        this.id = id;
        this.clientNumber = clientNumber;
        this.chdrNum = chdrNum;
        this.letterType = letterType;
        this.printDate = printDate;
        this.dataId = dataId;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.claimId = claimId;
        this.benPercent = benPercent;
        this.role1 = role1;
        this.role2 = role2;
        this.cownNum = cownNum;
        this.cownName = cownName;
        this.regDate = regDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setChdrNum(String chdrNum) {
        this.chdrNum = chdrNum;
    }

    public void setLetterType(String letterType) {
        this.letterType = letterType;
    }

    public void setPrintDate(LocalDate printDate) {
        this.printDate = printDate;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId.isEmpty() ? null : claimId;
    }

    public void setBenPercent(Double benPercent) {
        this.benPercent = benPercent;
    }

    public void setRole1(String role1) {
        this.role1 = role1.isEmpty() ? null : role1;
    }

    public void setRole2(String role2) {
        this.role2 = role2.isEmpty() ? null : role2;
    }

    public void setCownNum(String cownNum) {
        this.cownNum = cownNum;
    }

    public void setCownName(String cownName) {
        this.cownName = cownName;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
}
