package com.challenge.csvimport.job.builder;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

/**
 * Builder class for setting delimiter for the tokenizer
 */
public class DelimitedLineTokenizerBuilder extends AbstractLineTokenizerBuilder<DelimitedLineTokenizer> {

    public DelimitedLineTokenizerBuilder() {
        super(new DelimitedLineTokenizer());
    }

    public DelimitedLineTokenizerBuilder withDelimiter(String delimiter) {
        this.tokenizer.setDelimiter(delimiter);
        return this;
    }
}
