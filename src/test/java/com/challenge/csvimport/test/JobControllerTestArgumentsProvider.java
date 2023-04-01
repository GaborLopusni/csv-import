package com.challenge.csvimport.test;

import com.challenge.csvimport.controller.exception.InvalidFileNamesException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Collections;
import java.util.stream.Stream;

public class JobControllerTestArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        "CUSTCOMP[0-9]{2}\\.(TXT|txt)",
                        new MockMultipartFile[]{new MockMultipartFile("CUSTCOMP01.txt", "CUSTCOMP01.txt", null, (byte[]) null)},
                        new FlatFileParseException("parse failed", "CUSTCOMP01.txt"),
                        "Import for resources has failed, file could not be parsed."
                ),
                Arguments.of(
                        "CUSTCOMP[0-9]{2}\\.(TXT|txt)",
                        new MockMultipartFile[]{new MockMultipartFile("CUSTCOMP01.txt", "CUSTCOMP01.txt", null, (byte[]) null)},
                        new DataIntegrityViolationException("import failed"),
                        "Import has failed due a database related error, please check the integrity of the data."
                ),
                Arguments.of(
                        "CUSTCOMP[0-9]{2}\\.(TXT|txt)",
                        new MockMultipartFile[]{new MockMultipartFile("CUSTCOMP01.txt", "CUSTCOMP01.txt", null, (byte[]) null)},
                        new Exception("generic failure"),
                        "Import has failed."
                ),
                Arguments.of(
                        "CUSTCOMP[0-9]{2}\\.(TXT|txt)",
                        new MockMultipartFile[]{new MockMultipartFile("CUSTCO.txt", "CUSTCO1.txt", null, (byte[]) null)},
                        new InvalidFileNamesException("invalid filenames", Collections.singletonList("CUSTCO.txt")),
                        "Some of the filenames are invalid."
                )
        );
    }
}
