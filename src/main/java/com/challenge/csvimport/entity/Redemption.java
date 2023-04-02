package com.challenge.csvimport.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Entity
@Table(schema = "dbo", name = "SurValues")
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sur_values")
    @SequenceGenerator(name = "seq_sur_values", sequenceName = "dbo.seq_survalues", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Company")
    private String company;

    @Column(name = "Chdrnum")
    private String chdrNum;

    @Column(name = "Survalue", columnDefinition = "NUMERIC", length = 15, precision = 2)
    private Double surrenderValue;

    public Redemption() {
    }

    public Redemption(String chdrNum, Double surrenderValue, String company) {
        this.chdrNum = chdrNum;
        this.surrenderValue = surrenderValue;
        this.company = company;
    }
}
