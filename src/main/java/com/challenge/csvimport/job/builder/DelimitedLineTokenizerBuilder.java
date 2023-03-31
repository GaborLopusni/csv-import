package com.challenge.csvimport.job.builder;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class DelimitedLineTokenizerBuilder extends AbstractLineTokenizerBuilder<DelimitedLineTokenizer> {

    public DelimitedLineTokenizerBuilder() {
        super(new DelimitedLineTokenizer());
    }

    public DelimitedLineTokenizerBuilder withDelimiter(String delimiter) {
        ((DelimitedLineTokenizer)this.tokenizer).setDelimiter(delimiter);
        return this;
    }
}
