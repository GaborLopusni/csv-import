package com.challenge.csvimport;

import com.challenge.csvimport.utility.DateTimeFormatter;
import com.challenge.csvimport.utility.EntityFieldHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UtilitiesTests {

    @Test
    public void entityFieldHelperTest() {
        var expectedResult = new String[]{"testField1", "testField2", "testField3", "testField4"};
        var entityFieldHelper = new EntityFieldHelper();
        var result = entityFieldHelper.getFields(TestEntity.class);

        Assertions.assertArrayEquals(expectedResult, result);
    }

    @Test
    public void dateFormatterTest() {
        var dateTimeFormatter = new DateTimeFormatter();
        var result = dateTimeFormatter.formatDate("20230401", "yyyyMMdd");
        var expectedResult = LocalDate.of(2023, 4, 1);
        Assertions.assertEquals(expectedResult, result);

        result = dateTimeFormatter.formatDate("20230401000", "yyyyMMdd");

        Assertions.assertNull(result);
    }
}
