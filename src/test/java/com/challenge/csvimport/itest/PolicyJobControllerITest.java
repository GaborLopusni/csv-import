package com.challenge.csvimport.itest;

import com.challenge.csvimport.entity.Policy;
import com.challenge.csvimport.repository.PolicyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("integration")
public class PolicyJobControllerITest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PolicyRepository policyRepository;

    private final static String URI = "/api/job/policy";

    @Test
    public void successfulImport() throws Exception {
        var expectedPolicyFirst = new Policy(
                1L,
                "00000001",
                "00000001",
                "Test name",
                "00000001",
                "Test name",
                "12Y",
                "67891",
                "test address"
        );

        var expectedPolicySecond = new Policy(
                2L,
                "00000002",
                "00000002",
                "Test name2",
                "00000002",
                "Test name2",
                "12Y",
                "62134",
                "test address2"
        );

        var contentFirst = "00000001|00000001|Test name |00000001|Test name |12Y|67891|test address |";
        var contentSecond = "00000002|00000002|Test name2 |00000002|Test name2 |12Y|62134|test address2 |";
        var multipartFileFirst = new MockMultipartFile("files", "CUSTCOMP01.txt", MediaType.MULTIPART_FORM_DATA_VALUE, contentFirst.getBytes(StandardCharsets.UTF_8));
        var multipartFileSecond = new MockMultipartFile("files", "CUSTCOMP02.txt", MediaType.MULTIPART_FORM_DATA_VALUE, contentSecond.getBytes(StandardCharsets.UTF_8));

        mvc.perform(multipart(URI)
                        .file(multipartFileFirst)
                        .file(multipartFileSecond)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());

        var result = policyRepository.findAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(expectedPolicyFirst.equals(result.get(0)));
        Assertions.assertTrue(expectedPolicySecond.equals(result.get(1)));
    }

    @Test
    public void dataIntegrityFailure() throws Exception {
        var content = "0000000100000001Test name |00000001|Test name |12Y|67891|test address |";
        var multipartFile = new MockMultipartFile("files", "CUSTCOMP01.txt", MediaType.MULTIPART_FORM_DATA_VALUE, content.getBytes(StandardCharsets.UTF_8));

        mvc.perform(multipart(URI)
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void invalidFileNameFailure() throws Exception {
        var content = "00000001|00000001|Test name |00000001|Test name |12Y|67891|test address |";
        var multipartFile = new MockMultipartFile("files", "CUSTCOMP01010.txt", MediaType.MULTIPART_FORM_DATA_VALUE, content.getBytes(StandardCharsets.UTF_8));

        mvc.perform(multipart(URI)
                        .file(multipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }
}
