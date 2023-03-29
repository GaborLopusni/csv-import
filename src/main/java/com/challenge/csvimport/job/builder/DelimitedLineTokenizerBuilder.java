package com.challenge.csvimport.job.builder;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class DelimitedLineTokenizerBuilder {

    private final DelimitedLineTokenizer delimitedLineTokenizer;

    public DelimitedLineTokenizerBuilder() {
        this.delimitedLineTokenizer = new DelimitedLineTokenizer();
    }

    public DelimitedLineTokenizerBuilder withDelimiter(String delimiter) {
        this.delimitedLineTokenizer.setDelimiter(delimiter);
        return this;
    }

    public DelimitedLineTokenizerBuilder withEntityColumns(String... entityColumns) {
        this.delimitedLineTokenizer.setNames(entityColumns);
        return this;
    }

    public DelimitedLineTokenizerBuilder withStrict(boolean isStrict) {
        this.delimitedLineTokenizer.setStrict(isStrict);
        return this;
    }

    public DelimitedLineTokenizer build() {
        return this.delimitedLineTokenizer;
    }
}
