package com.challenge.csvimport.job.builder;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RangeArrayPropertyEditor;

public class FixedLengthLineTokenizerBuilder extends AbstractLineTokenizerBuilder<FixedLengthTokenizer> {

    public FixedLengthLineTokenizerBuilder() {
        super(new FixedLengthTokenizer());
    }

    public FixedLengthLineTokenizerBuilder withColumnRanges(String columnRanges) {
        RangeArrayPropertyEditor editor = new RangeArrayPropertyEditor();
        editor.setAsText(columnRanges);
        ((FixedLengthTokenizer) this.tokenizer).setColumns((Range[]) editor.getValue());

        return this;
    }
}
