package com.challenge.csvimport.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(schema = "DBO", name = "SUR_VALUES")
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Outpay_Header_ID")
    private Long id;

    @Column(name = "Chdrnum")
    private String chdrNum;

    @Column(name = "Survalue")
    private double surValue;

    @Column(name = "Company")
    private String company;

    @Column(name = "Currency")
    private String currency;

    @Column(name = "ValidDate")
    private Date validDate;

    public Redemption() {
    }

    public Redemption(String chdrNum, double surValue, String company, String currency, Date validDate) {
        this.chdrNum = chdrNum;
        this.surValue = surValue;
        this.company = company;
        this.currency = currency;
        this.validDate = validDate;
    }
}
