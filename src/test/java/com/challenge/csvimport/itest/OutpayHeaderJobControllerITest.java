package com.challenge.csvimport.itest;

import com.challenge.csvimport.entity.OutpayHeader;
import com.challenge.csvimport.utility.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

public class OutpayHeaderJobControllerITest extends AbstractJobControllerITest<OutpayHeader> {

    @Autowired
    DateTimeFormatter dateTimeFormatter;

    @Autowired
    public OutpayHeaderJobControllerITest(JpaRepository<OutpayHeader, Long> outpayHeaderRepository) {
        super(outpayHeaderRepository);
        uri = "/api/job/outpayHeader";
    }

    @Test
    public void successfulImportTest() throws Exception {
        expectedList.add(new OutpayHeader(
                1L,
                "00000001",
                "00000002",
                "TES         ",
                dateTimeFormatter.formatDate("20230403", "yyyyMMdd"),
                "OUTPAY",
                "Test Name",
                "test address",
                null,
                100.00,
                "OW",
                null,
                "00000010",
                "Test Name",
                null
        ));

        expectedList.add(new OutpayHeader(
                2L,
                "00000020",
                "00000021",
                "TES         ",
                dateTimeFormatter.formatDate("20230403", "yyyyMMdd"),
                "OUTPAY",
                "Test Name2",
                "test address2",
                "91-978663",
                100.00,
                null,
                null,
                "00000023",
                "Test Name2",
                null
        ));

        var firstContent = "00000001;00000002;TES;20230403;OUTPAY;Test Name;test address;;100.00;OW;  ;00000010;Test Name; ";
        var secondContent = "00000020;00000021;TES;20230403;OUTPAY;Test Name2;test address2;91-978663;100.00;;;00000023;Test Name2; ";
        var multipartFileFirst = new MockMultipartFile("files", "OUTPH_CUP_20230404_1829.TXT", MediaType.MULTIPART_FORM_DATA_VALUE, firstContent.getBytes(StandardCharsets.UTF_8));
        var multipartFileSecond = new MockMultipartFile("files", "OUTPH_CUP_20230405_1829.TXT", MediaType.MULTIPART_FORM_DATA_VALUE, secondContent.getBytes(StandardCharsets.UTF_8));


        mockMultipartFiles.add(multipartFileFirst);
        mockMultipartFiles.add(multipartFileSecond);
        successfulImport();
    }

    @Test
    public void dataIntegrityFailureTest() throws Exception {
        var content = "00000001;00000002;TES;20230403;OUTPAY;Test Name;test address;;100.00OW;  ;00000010;Test Name; ";
        var multipartFile = new MockMultipartFile("files", "OUTPH_CUP_20230404_1829.TXT", MediaType.MULTIPART_FORM_DATA_VALUE, content.getBytes(StandardCharsets.UTF_8));
        mockMultipartFiles.add(multipartFile);
        dataIntegrityFailure();
    }

    @Test
    public void invalidFileNameFailureTest() throws Exception {
        var content = "00000001;00000002;TES;20230403;OUTPAY;Test Name;test address;;100.00;OW;  ;00000010;Test Name; ";
        var multipartFile = new MockMultipartFile("files", "OUTPH_CUP_202304041829.TXT", MediaType.MULTIPART_FORM_DATA_VALUE, content.getBytes(StandardCharsets.UTF_8));
        mockMultipartFiles.add(multipartFile);
        invalidFileNameFailure();
    }
}
