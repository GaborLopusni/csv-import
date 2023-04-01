package com.challenge.csvimport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode
@Setter
@Entity
@Table(name = "OutPay_Header")
public class OutpayHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_outpay_header")
    @SequenceGenerator(name = "seq_outpay_header", sequenceName = "seq_outpay_header", allocationSize = 1)
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

    @Column(name = "BenPercent", columnDefinition = "NUMERIC", length = 6, precision = 2)
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

    public OutpayHeader(String clientNumber, String chdrNum, String letterType, LocalDate printDate, String dataId, String clientName, String clientAddress, String claimId, Double benPercent, String role1, String role2, String cownNum, String cownName, LocalDate regDate) {
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
}
