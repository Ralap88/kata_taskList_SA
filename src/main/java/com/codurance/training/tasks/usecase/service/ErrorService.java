package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.usecase.port.ErrorInPut;
import com.codurance.training.tasks.usecase.port.ErrorUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static java.lang.String.format;

public class ErrorService implements ErrorUseCase {

    @Override
    public CqrsOutput execute(ErrorInPut input) {
        StringBuilder sb = new StringBuilder();
        sb.append(format("I don't know what the command \"%s\" is.", input.getCommand()));
        sb.append("\n");
        return CqrsOutput.create().setExitCode(ExitCode.FAILURE).setMessage(sb.toString());
    }
}
