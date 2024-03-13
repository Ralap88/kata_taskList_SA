package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.usecase.port.in.help.HelpOutput;
import com.codurance.training.tasks.usecase.port.in.help.HelpUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;

public class HelpService implements HelpUseCase {
    @Override
    public HelpOutput execute() {
        return HelpOutput.create(HelpOutput.class).setExitCode(ExitCode.SUCCESS).setResult(new StringBuilder());
    }
}
