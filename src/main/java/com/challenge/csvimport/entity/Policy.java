package com.challenge.csvimport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(schema = "DBO", name = "POLICY")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Chdrnum")
    @NotNull
    private String chdrNum;

    @Column(name = "Cownnum")
    @NotNull
    private String cownNum;

    @Column(name = "OwnerName")
    private String ownerName;

    @Column(name = "LifcNum")
    private String lifcNum;

    @Column(name = "LifcName")
    private String lifcName;

    @Column(name = "Aracde")
    private String aaraCde;

    @Column(name = "Agntnum")
    private String agntNum;

    @Column(name = "MailAddress")
    private String mailAddress;

    public Policy() {
    }

    public Policy(String chdrNum, String cownNum, String ownerName, String lifcNum, String lifcName, String aaraCde, String agntNum, String mailAddress) {
        this.chdrNum = chdrNum;
        this.cownNum = cownNum;
        this.ownerName = ownerName;
        this.lifcNum = lifcNum;
        this.lifcName = lifcName;
        this.aaraCde = aaraCde;
        this.agntNum = agntNum;
        this.mailAddress = mailAddress;
    }
}
