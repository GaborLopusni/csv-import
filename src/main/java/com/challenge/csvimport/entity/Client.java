package com.challenge.csvimport.entity;

import jakarta.persistence.*;
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
}
