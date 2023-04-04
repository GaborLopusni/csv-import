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

import static com.challenge.csvimport.controller.exception.Constants.*;

public class JobControllerTestArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        "CUSTCOMP[0-9]{2}\\.(TXT|txt)",
                        new MockMultipartFile[]{new MockMultipartFile("CUSTCOMP01.txt", "CUSTCOMP01.txt", null, (byte[]) null)},
                        new FlatFileParseException("parse failed", "CUSTCOMP01.txt"),
                        String.format(PARSE_EXCEPTION_MESSAGE_TEMPLATE, "CUSTCOMP01.txt")
                ),
                Arguments.of(
                        "CUSTCOMP[0-9]{2}\\.(TXT|txt)",
                        new MockMultipartFile[]{new MockMultipartFile("CUSTCOMP01.txt", "CUSTCOMP01.txt", null, (byte[]) null)},
                        new DataIntegrityViolationException("import failed"),
                        String.format(DATA_ACCESS_EXCEPTION_MESSAGE_TEMPLATE, "CUSTCOMP01.txt")
                ),
                Arguments.of(
                        "CUSTCOMP[0-9]{2}\\.(TXT|txt)",
                        new MockMultipartFile[]{new MockMultipartFile("CUSTCOMP01.txt", "CUSTCOMP01.txt", null, (byte[]) null)},
                        new Exception("generic failure"),
                        String.format(GENERIC_EXCEPTION_MESSAGE_TEMPLATE, "CUSTCOMP01.txt")
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
