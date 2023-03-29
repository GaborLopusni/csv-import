package com.challenge.csvimport.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Client {

    @Column(name = "Clntnum")
    @NotNull
    private String clntNum;

    @Column(name = "ClntName")
    private String clntName;

    @Column(name = "ClntAddress")
    private String clntAddress;

    public Client() {
    }

    public Client(String clntNum, String clntName, String clntAddress) {
        this.clntNum = clntNum;
        this.clntName = clntName;
        this.clntAddress = clntAddress;
    }
}
