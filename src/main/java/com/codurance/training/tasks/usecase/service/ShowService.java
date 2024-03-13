package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.port.*;
import com.codurance.training.tasks.usecase.port.in.ProjectListRepository;
import com.codurance.training.tasks.usecase.port.in.show.ShowInput;
import com.codurance.training.tasks.usecase.port.in.show.ShowOutput;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;
import tw.teddysoft.ezddd.core.usecase.ExitCode;

import static java.lang.String.format;

public class ShowService implements ShowUseCase {

    private final ProjectListRepository repository;

    public ShowService(ProjectListRepository repository) {
        this.repository = repository;
    }
    @Override
    public ShowOutput execute(ShowInput input) {
        return show(input);
    }

    public ShowOutput show(ShowInput input) {
        ProjectList projectList = repository.findById(ProjectId.of(input.getProjectId())).get();
        StringBuilder sb = new StringBuilder();
        for (Project project : projectList.getProjects()) {
            sb.append(project.getName()).append(System.lineSeparator());
            for (Task task : project.getTasks()) {
                sb.append(format("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription()));
            }
            sb.append(System.lineSeparator());
        }
        return ShowOutput.create(ShowOutput.class).setExitCode(ExitCode.SUCCESS).SetProjects(projectList.getProjects().stream().map(ProjectMapper::toDto).toList());
    }
}
