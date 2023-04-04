package com.challenge.csvimport.utility;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Component
public class EntityFieldHelper {

    /**
     * Getting the fields of a class
     *
     * @param clazz input class to iterate
     * @return array of field names
     */
    public String[] getFields(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Id.class) == null)
                .map(Field::getName).toArray(String[]::new);
    }
}
