package com.challenge.csvimport.test.entity;

import jakarta.persistence.Id;

public class TestEntity {

    @Id
    private Long id;

    private String testField1;

    private String testField2;

    private String testField3;

    private String testField4;
}
