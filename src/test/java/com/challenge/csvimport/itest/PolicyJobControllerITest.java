package com.challenge.csvimport.itest;

import com.challenge.csvimport.entity.Policy;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("integration")
public class PolicyJobControllerITest extends AbstractJobControllerITest<Policy> {

    @Autowired
    private MockMvc mvc;

    @Autowired
    public PolicyJobControllerITest(JpaRepository<Policy, Long> policyRepository) {
        super(policyRepository);
        uri = "/api/job/policy";
    }

    @Test
    @Rollback
    public void successfulImportTest() throws Exception {
        expectedList.add(new Policy(
                1L,
                "00000001",
                "00000001",
                "Test name",
                "00000001",
                "Test name",
                "12Y",
                "67891",
                "test address"
        ));

        expectedList.add(new Policy(
                2L,
                "00000002",
                "00000002",
                "Test name2",
                "00000002",
                "Test name2",
                "12Y",
                "62134",
                "test address2"
        ));

        var firstContent = "00000001|00000001|Test name |00000001|Test name |12Y|67891|test address |";
        var secondContent = "00000002|00000002|Test name2 |00000002|Test name2 |12Y|62134|test address2 |";
        var multipartFileFirst = new MockMultipartFile("files", "CUSTCOMP01.txt", MediaType.MULTIPART_FORM_DATA_VALUE, firstContent.getBytes(StandardCharsets.UTF_8));
        var multipartFileSecond = new MockMultipartFile("files", "CUSTCOMP02.txt", MediaType.MULTIPART_FORM_DATA_VALUE, secondContent.getBytes(StandardCharsets.UTF_8));


        mockMultipartFiles.add(multipartFileFirst);
        mockMultipartFiles.add(multipartFileSecond);
        successfulImport();
    }

    @Test
    public void dataIntegrityFailureTest() throws Exception {
        var content = "0000000100000001Test name |00000001|Test name |12Y|67891|test address |";
        var multipartFile = new MockMultipartFile("files", "CUSTCOMP01.txt", MediaType.MULTIPART_FORM_DATA_VALUE, content.getBytes(StandardCharsets.UTF_8));
        mockMultipartFiles.add(multipartFile);
        dataIntegrityFailure();
    }

    @Test
    public void invalidFileNameFailureTest() throws Exception {
        var content = "00000001|00000001|Test name |00000001|Test name |12Y|67891|test address |";
        var multipartFile = new MockMultipartFile("files", "CUSTCOMP01010.txt", MediaType.MULTIPART_FORM_DATA_VALUE, content.getBytes(StandardCharsets.UTF_8));
        mockMultipartFiles.add(multipartFile);
        invalidFileNameFailure();
    }
}
