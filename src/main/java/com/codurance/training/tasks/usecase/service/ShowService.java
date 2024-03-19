package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.usecase.port.ProjectMapper;
import com.codurance.training.tasks.usecase.port.in.show.ShowInput;
import com.codurance.training.tasks.usecase.port.in.show.ShowOutput;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;
import com.codurance.training.tasks.usecase.port.out.TaskListRepository;
import tw.teddysoft.ezddd.core.usecase.ExitCode;

public class ShowService implements ShowUseCase {
    private final TaskListRepository repository;

    public ShowService(TaskListRepository repository) {
        this.repository = repository;
    }
    @Override
    public ShowOutput execute(ShowInput input) {
        ProjectList projectList = repository.findById(ProjectId.of(input.getProjectId())).get();

        return ShowOutput
                .create(ShowOutput.class)
                .setExitCode(ExitCode.SUCCESS)
                .SetProjects(projectList.getProjects().stream().map(ProjectMapper::toDto).toList());
    }
}
