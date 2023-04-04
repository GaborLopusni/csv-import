package com.challenge.csvimport.itest;

import com.challenge.csvimport.entity.Redemption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

public class RedemptionJobControllerITest extends AbstractJobControllerITest<Redemption> {

    @Autowired
    public RedemptionJobControllerITest(JpaRepository<Redemption, Long> redemptionRepository) {
        super(redemptionRepository);
        uri = "/api/job/redemption";
    }

    @Test
    public void successfulImportTest() throws Exception {
        expectedList.add(new Redemption(
                1L,
                "1",
                "30003195",
                3450978.00
        ));

        expectedList.add(new Redemption(
                2L,
                "1",
                "30004871",
                0.00
        ));

        var firstContent = "130003195     3450978.00K5003MT   WEEKEND1  2020-02-15-08.19.59.017770";
        var secondContent = "130004871           0.00K5003MT   WEEKEND1  2020-02-15-08.19.59.017770";
        var multipartFileFirst = new MockMultipartFile("files", "ZTPSPF.txt", MediaType.MULTIPART_FORM_DATA_VALUE, firstContent.getBytes(StandardCharsets.UTF_8));
        var multipartFileSecond = new MockMultipartFile("files", "ZTPSPF.txt", MediaType.MULTIPART_FORM_DATA_VALUE, secondContent.getBytes(StandardCharsets.UTF_8));

        mockMultipartFiles.add(multipartFileFirst);
        mockMultipartFiles.add(multipartFileSecond);
        successfulImport();
    }

    @Test
    public void dataIntegrityFailureTest() throws Exception {
        var content = "130004871     408908A.00K5003MT   WEEKEND1  2020-02-15-08.19.59.017770";
        var multipartFile = new MockMultipartFile("files", "ZTPSPF.txt", MediaType.MULTIPART_FORM_DATA_VALUE, content.getBytes(StandardCharsets.UTF_8));
        mockMultipartFiles.add(multipartFile);
        expectFailure();
    }

    @Test
    public void invalidFileNameFailureTest() throws Exception {
        var content = "130004871           0.00K5003MT   WEEKEND1  2020-02-15-08.19.59.017770\"";
        var multipartFile = new MockMultipartFile("files", "ZTPSPF1101.txt", MediaType.MULTIPART_FORM_DATA_VALUE, content.getBytes(StandardCharsets.UTF_8));
        mockMultipartFiles.add(multipartFile);
        expectFailure();
    }
}
