package com.challenge.csvimport.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * Entity class for redemption data
 */
@EqualsAndHashCode
@Setter
@Entity
@Table(schema = "dbo", name = "SurValues")
public class Redemption {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sur_values")
    @SequenceGenerator(name = "seq_sur_values", sequenceName = "dbo.\"seq_survalues\"", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Company")
    private String company;

    @Column(name = "Chdrnum")
    private String chdrNum;

    @Column(name = "Survalue")
    private Double surrenderValue;

    public Redemption() {
    }

    public Redemption(Long id, String company, String chdrNum, Double surrenderValue) {
        this.id = id;
        this.company = company;
        this.chdrNum = chdrNum;
        this.surrenderValue = surrenderValue;
    }
}
