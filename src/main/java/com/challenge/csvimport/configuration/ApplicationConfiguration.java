package com.challenge.csvimport.configuration;

import com.challenge.csvimport.entity.OutpayHeader;
import com.challenge.csvimport.entity.Policy;
import com.challenge.csvimport.entity.Redemption;
import com.challenge.csvimport.job.builder.DelimitedLineTokenizerBuilder;
import com.challenge.csvimport.job.builder.FixedLengthLineTokenizerBuilder;
import com.challenge.csvimport.job.mapper.LineMapper;
import com.challenge.csvimport.job.mapper.StringToLocalDateConverter;
import com.challenge.csvimport.job.mapper.StringToDoubleConverter;
import com.challenge.csvimport.job.reader.CustomFlatFileItemReader;
import com.challenge.csvimport.job.writer.JpaItemWriter;
import com.challenge.csvimport.repository.OutpayHeaderRepository;
import com.challenge.csvimport.repository.PolicyRepository;
import com.challenge.csvimport.repository.RedemptionRepository;
import com.challenge.csvimport.utility.DateTimeFormatter;
import com.challenge.csvimport.utility.EntityFieldHelper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.nio.charset.StandardCharsets;

@Configuration
@ComponentScan("com.challenge.csvimport")
public class ApplicationConfiguration {

    @Value("${import.policy.tokenizer.delimiter}")
    private String policyDelimiter;

    @Value("${import.outpay.tokenizer.delimiter}")
    private String outpayHeaderDelimiter;

    @Value("${import.redemption.tokenizer.ranges}")
    private String redemptionDelimiter;

    @Value("${import.policy.file.pattern.regexp}")
    private String policyFileNamePattern;

    @Value("${import.outpay.file.pattern.regexp}")
    private String outpayHeaderFileNamePattern;

    @Value("${import.redemption.file.pattern.regexp}")
    private String redemptionFileNamePattern;

    @Bean
    public String policyFileNamePattern() {
        return policyFileNamePattern;
    }

    @Bean
    public String outpayHeaderFileNamePattern() {
        return outpayHeaderFileNamePattern;
    }

    @Bean
    public String redemptionFileNamePattern() {
        return redemptionFileNamePattern;
    }

    @Bean
    public ConversionService conversionService(DateTimeFormatter dateTimeFormatter) {
        DefaultConversionService conversionService = new DefaultConversionService();
        StringToLocalDateConverter dateConverter = source -> dateTimeFormatter.formatDate(source, "yyyyMMdd");
        StringToDoubleConverter doubleConverter = Double::parseDouble;
        conversionService.addConverter(dateConverter);
        conversionService.addConverter(doubleConverter);
        DefaultConversionService.addDefaultConverters(conversionService);

        return conversionService;
    }

    @Bean
    public DelimitedLineTokenizer policyDelimitedLineTokenizer(EntityFieldHelper entityFieldHelper) {
        return new DelimitedLineTokenizerBuilder()
                .withDelimiter(policyDelimiter)
                .withEntityColumns(entityFieldHelper.getFields(Policy.class))
                .withStrict(false)
                .build();
    }

    @Bean
    public DelimitedLineTokenizer outpayHeaderDelimitedLineTokenizer(EntityFieldHelper entityFieldHelper) {
        return new DelimitedLineTokenizerBuilder()
                .withDelimiter(outpayHeaderDelimiter)
                .withEntityColumns(entityFieldHelper.getFields(OutpayHeader.class))
                .withStrict(true)
                .build();
    }

    @Bean
    public FixedLengthTokenizer redemptionFixedLengthLineTokenizer(EntityFieldHelper entityFieldHelper) {
        return new FixedLengthLineTokenizerBuilder()
                .withColumnRanges(redemptionDelimiter)
                .withEntityColumns(entityFieldHelper.getFields(Redemption.class))
                .withStrict(false)
                .build();
    }

    @Bean
    public LineMapper<Policy> policyLineMapper(DelimitedLineTokenizer policyDelimitedLineTokenizer, ConversionService conversionService) {
        return new LineMapper<>(Policy.class, policyDelimitedLineTokenizer, conversionService);
    }

    @Bean
    public LineMapper<OutpayHeader> outpayHeaderLineMapper(DelimitedLineTokenizer outpayHeaderDelimitedLineTokenizer, ConversionService conversionService) {
        return new LineMapper<>(OutpayHeader.class, outpayHeaderDelimitedLineTokenizer, conversionService);
    }

    @Bean
    public LineMapper<Redemption> redemptionLineMapper(FixedLengthTokenizer redemptionFixedLengthLineTokenizer, ConversionService conversionService) {
        return new LineMapper<>(Redemption.class, redemptionFixedLengthLineTokenizer, conversionService);
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
}
