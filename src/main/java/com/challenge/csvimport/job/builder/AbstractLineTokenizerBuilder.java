package com.challenge.csvimport.job.builder;

import org.springframework.batch.item.file.transform.AbstractLineTokenizer;

public abstract class AbstractLineTokenizerBuilder<T extends AbstractLineTokenizer> {

    protected final T tokenizer;

    public AbstractLineTokenizerBuilder(T tokenizer) {
        this.tokenizer = tokenizer;
    }

    public AbstractLineTokenizerBuilder<T> withEntityColumns(String... entityColumns) {
        this.tokenizer.setNames(entityColumns);
        return this;
    }

    public AbstractLineTokenizerBuilder<T> withStrict(boolean isStrict) {
        this.tokenizer.setStrict(isStrict);
        return this;
    }

    public T build() {
        return this.tokenizer;
    }
}
