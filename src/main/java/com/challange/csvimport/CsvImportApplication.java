package com.challange.csvimport;

import com.challange.csvimport.configuration.ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CsvImportApplication {

    public static void main(String[] args) {
		try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {

		}
    }

}
