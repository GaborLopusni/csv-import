package com.challenge.csvimport.itest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AbstractJobControllerITest<T> {

    private final JpaRepository<T, Long> repository;

    protected String uri;

    protected List<T> expectedList;

    protected List<MockMultipartFile> mockMultipartFiles;

    @Autowired
    private MockMvc mvc;

    public AbstractJobControllerITest(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @BeforeEach
    public void reset() {
        expectedList = new ArrayList<>();
        mockMultipartFiles = new ArrayList<>();
    }

    protected void successfulImport() throws Exception {
        mvc.perform(prepareMockRequestBuilder())
                .andExpect(status().is(HttpStatus.OK.value()));

        var result = repository.findAll();

        Assertions.assertEquals(mockMultipartFiles.size(), result.size());

        var expectedIterator = expectedList.iterator();
        var resultIterator = result.iterator();

        while (expectedIterator.hasNext() && resultIterator.hasNext()) {
            Assertions.assertEquals(expectedIterator.next(), resultIterator.next());
        }
    }

    protected void expectFailure() throws Exception {
        mvc.perform(prepareMockRequestBuilder())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error").exists());
    }

    private RequestBuilder prepareMockRequestBuilder() {
        var multipartBuilder = multipart(uri);
        mockMultipartFiles.forEach(multipartBuilder::file);
        multipartBuilder.contentType(MediaType.MULTIPART_FORM_DATA_VALUE);
        return multipartBuilder;
    }
}
