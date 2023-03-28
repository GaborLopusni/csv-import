package com.challenge.csvimport.configuration;

import com.challenge.csvimport.entity.Policy;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.challenge.csvimport")
public class ApplicationConfiguration {

    @Value("${import.policy.delimiter}")
    private String policyDelimiter;

    @Bean
    public DelimitedLineTokenizer policyDelimitedLineTokenizer() {
        return new DelimitedLineTokenizer() {
            {
                setDelimiter(policyDelimiter);
                setNames("chdrNum", "cownNum", "ownerName", "lifcNum", "lifcName", "aaraCde", "agntNum", "mailAddress");
                setStrict(false);
            }
        };
    }

    @Bean
    public DefaultLineMapper<Policy> policyLineMapper(DelimitedLineTokenizer policyDelimitedLineTokenizer) throws Exception {
        return new DefaultLineMapper<>() {
            {
                setLineTokenizer(policyDelimitedLineTokenizer);
                setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    {
                        setTargetType(Policy.class);
                        afterPropertiesSet();
                    }
                });
            }
        };
    }

    @Bean
    public FlatFileItemReader<Policy> policyFlatFileItemReader(DefaultLineMapper<Policy> policyLineMapper) {
        FlatFileItemReader<Policy> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLineMapper(policyLineMapper);

        return flatFileItemReader;
    }
}
