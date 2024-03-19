package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.port.in.project.AddProjectInput;
import com.codurance.training.tasks.usecase.port.in.project.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.out.TaskListRepository;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public class AddProjectService implements AddProjectUseCase {

    private final TaskListRepository repository;

    public AddProjectService(TaskListRepository repository) {
        this.repository = repository;
    }

    @Override
    public CqrsOutput execute(AddProjectInput input) {
        ProjectList projectList = repository.findById(ProjectId.of(input.getId())).get();
        projectList.add(Project.of(ProjectName.of(input.getProjectName())));
        repository.save(projectList);
        return CqrsOutput.create().setExitCode(ExitCode.SUCCESS).setMessage("");
    }
}
