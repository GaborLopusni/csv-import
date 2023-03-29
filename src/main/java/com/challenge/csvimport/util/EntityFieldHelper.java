package com.challenge.csvimport.util;

import jakarta.persistence.Id;

import java.lang.reflect.Field;
import java.util.Arrays;

public class EntityFieldHelper {

    public static String[] getFields(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Id.class) == null)
                .map(Field::getName).toArray(String[]::new);
    }
}
