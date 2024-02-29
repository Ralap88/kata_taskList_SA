package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.usecase.port.HelpUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public class HelpService implements HelpUseCase {
    @Override
    public CqrsOutput execute() {
        StringBuilder sb = new StringBuilder();

        sb.append("Commands:");
        sb.append("\n");
        sb.append("  show");
        sb.append("\n");
        sb.append("  add project <project name>");
        sb.append("\n");
        sb.append("  add task <project name> <task description>");
        sb.append("\n");
        sb.append("  check <task ID>");
        sb.append("\n");
        sb.append("  uncheck <task ID>");
        sb.append("\n");
        return CqrsOutput.create().setExitCode(ExitCode.SUCCESS).setMessage("");
    }
}
