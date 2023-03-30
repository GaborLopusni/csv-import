package com.challenge.csvimport.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode
@Entity
@Table(schema = "dbo", name = "SurValues")
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbo.seq_SurValues")
    @Column(name = "ID")
    private Long id;

    @Column(name = "Company")
    private String company;

    @Column(name = "Chdrnum")
    private String chdrNum;

    @Column(name = "Survalue", columnDefinition = "NUMERIC", length = 15, precision = 2)
    private Double surrenderValue;

    @Column(name = "Currency")
    private String currency;

    @Column(name = "ValidDate")
    private Date validDate;

    public Redemption() {
    }

    public Redemption(String chdrNum, Double surrenderValue, String company, String currency, Date validDate) {
        this.chdrNum = chdrNum;
        this.surrenderValue = surrenderValue;
        this.company = company;
        this.currency = currency;
        this.validDate = validDate;
    }
}
