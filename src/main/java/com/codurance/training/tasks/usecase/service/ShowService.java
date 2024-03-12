package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.port.ShowInput;
import com.codurance.training.tasks.usecase.port.ShowUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static java.lang.String.format;

public class ShowService implements ShowUseCase {
    @Override
    public CqrsOutput execute(ShowInput input) {
        return show(input);
    }

    public CqrsOutput show(ShowInput input) {
        StringBuilder sb = new StringBuilder();
        for (Project project : input.getProjectList().getProjects()) {
            sb.append(project.getName());
            for (Task task : project.getTasks()) {
                sb.append(format("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription()));
            }
            sb.append("\n");
        }
        return CqrsOutput.create().setExitCode(ExitCode.SUCCESS).setMessage(sb.toString());
    }
}
