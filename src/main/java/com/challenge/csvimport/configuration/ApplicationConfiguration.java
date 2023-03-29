package com.challenge.csvimport.configuration;

import com.challenge.csvimport.entity.OutpayHeader;
import com.challenge.csvimport.entity.Policy;
import com.challenge.csvimport.entity.Redemption;
import com.challenge.csvimport.job.ImportJobRunner;
import com.challenge.csvimport.job.JobRunner;
import com.challenge.csvimport.job.builder.DelimitedLineTokenizerBuilder;
import com.challenge.csvimport.job.mapper.LineMapper;
import com.challenge.csvimport.job.reader.CustomFlatFileItemReader;
import com.challenge.csvimport.job.writer.JpaItemWriter;
import com.challenge.csvimport.repository.OutpayHeaderRepository;
import com.challenge.csvimport.repository.PolicyRepository;
import com.challenge.csvimport.repository.RedemptionRepository;
import com.challenge.csvimport.service.ImportJobService;
import com.challenge.csvimport.service.ImportService;
import com.challenge.csvimport.util.EntityFieldHelper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@ComponentScan("com.challenge.csvimport")
public class ApplicationConfiguration {

    @Value("${import.policy.delimiter}")
    private String policyDelimiter;

    @Value("${import.outpay.delimiter}")
    private String outpayHeaderDelimiter;

    @Value("${import.redemption.delimiter}")
    private String redemptionDelimiter;

    @Bean
    public DelimitedLineTokenizer policyDelimitedLineTokenizer() {
        return new DelimitedLineTokenizerBuilder()
                .withDelimiter(policyDelimiter)
                .withEntityColumns(EntityFieldHelper.getFields(Policy.class))
                .withStrict(false)
                .build();
    }

    @Bean
    public DelimitedLineTokenizer outpayHeaderDelimitedLineTokenizer() {
        return new DelimitedLineTokenizerBuilder()
                .withDelimiter(outpayHeaderDelimiter)
                .withEntityColumns(EntityFieldHelper.getFields(OutpayHeader.class))
                .withStrict(false)
                .build();
    }

    @Bean
    public DelimitedLineTokenizer redemptionDelimitedLineTokenizer() {
        return new DelimitedLineTokenizerBuilder()
                .withDelimiter(redemptionDelimiter)
                .withEntityColumns(EntityFieldHelper.getFields(Redemption.class))
                .withStrict(false)
                .build();
    }

    @Bean
    public LineMapper<Policy> policyLineMapper(DelimitedLineTokenizer policyDelimitedLineTokenizer) {
        return new LineMapper<>(Policy.class, policyDelimitedLineTokenizer);
    }

    @Bean
    public LineMapper<Redemption> redemptionLineMapper(DelimitedLineTokenizer redemptionDelimitedLineTokenizer) {
        return new LineMapper<>(Redemption.class, redemptionDelimitedLineTokenizer);
    }

    @Bean
    public LineMapper<OutpayHeader> outpayHeaderLineMapper(DelimitedLineTokenizer outpayHeaderDelimitedLineTokenizer) {
        return new LineMapper<>(OutpayHeader.class, outpayHeaderDelimitedLineTokenizer);
    }

    @Bean
    public CustomFlatFileItemReader<Policy> policyFlatFileItemReader(DefaultLineMapper<Policy> policyLineMapper) {
        return new CustomFlatFileItemReader<>(policyLineMapper, StandardCharsets.ISO_8859_1);
    }

    @Bean
    public CustomFlatFileItemReader<Redemption> redemptionFlatFileItemReader(DefaultLineMapper<Redemption> redemptionLineMapper) {
        return new CustomFlatFileItemReader<>(redemptionLineMapper, StandardCharsets.ISO_8859_1);
    }

    @Bean
    public CustomFlatFileItemReader<OutpayHeader> outpayHeaderFlatFileItemReader(DefaultLineMapper<OutpayHeader> outpayHeaderLineMapper) {
        return new CustomFlatFileItemReader<>(outpayHeaderLineMapper, StandardCharsets.ISO_8859_1);
    }

    @Bean
    public JpaItemWriter<Policy> policyJpaItemWriter(PolicyRepository policyRepository) {
        return new JpaItemWriter<>(policyRepository);
    }

    @Bean
    public JpaItemWriter<Redemption> redemptionJpaItemWriter(RedemptionRepository redemptionRepository) {
        return new JpaItemWriter<>(redemptionRepository);
    }

    @Bean
    public JpaItemWriter<OutpayHeader> outpayHeaderJpaItemWriter(OutpayHeaderRepository outpayHeaderRepository) {
        return new JpaItemWriter<>(outpayHeaderRepository);
    }

    @Bean
    public ImportJobRunner<Policy> policyImportJobRunner(
            CustomFlatFileItemReader<Policy> policyCustomFlatFileItemReader,
            JpaItemWriter<Policy> policyJpaItemWriter) {
        return new ImportJobRunner<>(policyCustomFlatFileItemReader, policyJpaItemWriter);
    }

    @Bean
    public ImportJobRunner<Redemption> redemptionImportJobRunner(
            CustomFlatFileItemReader<Redemption> redemptionCustomFlatFileItemReader,
            JpaItemWriter<Redemption> redemptionJpaItemWriter) {
        return new ImportJobRunner<>(redemptionCustomFlatFileItemReader, redemptionJpaItemWriter);
    }

    @Bean
    public ImportJobRunner<OutpayHeader> outpayHeaderImportJobRunner(
            CustomFlatFileItemReader<OutpayHeader> outpayHeaderCustomFlatFileItemReader,
            JpaItemWriter<OutpayHeader> outpayHeaderJpaItemWriter) {
        return new ImportJobRunner<>(outpayHeaderCustomFlatFileItemReader, outpayHeaderJpaItemWriter);
    }

    @Bean
    public ImportService policyImportService(JobRunner policyImportJobRunner) {
        return new ImportJobService(policyImportJobRunner);
    }

    @Bean
    public ImportService redemptionImportService(JobRunner redemptionImportJobRunner) {
        return new ImportJobService(redemptionImportJobRunner);
    }

    @Bean
    public ImportService outpayHeaderImportService(JobRunner outpayHeaderImportJobRunner) {
        return new ImportJobService(outpayHeaderImportJobRunner);
    }
}
