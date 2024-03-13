package com.codurance.training.tasks.usecase.port.in.help;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public class HelpOutput extends CqrsOutput<HelpOutput> {
    private StringBuilder result;

    public HelpOutput setResult(StringBuilder result) {
        this.result = result;
        return this;
    }

    public StringBuilder getResult() {
        return result;
    }
}
