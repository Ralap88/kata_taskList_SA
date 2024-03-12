package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.usecase.port.project.AddProjectInput;
import com.codurance.training.tasks.usecase.port.project.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.ProjectListRepository;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import java.util.ArrayList;

public class AddProjectService implements AddProjectUseCase {

    private final ProjectListRepository repository;

    public AddProjectService(ProjectListRepository repository) {
        this.repository = repository;
    }

    @Override
    public CqrsOutput execute(AddProjectInput input) {
        ProjectList projectList = repository.findById(ProjectId.of(input.getId())).get();
        projectList.put(ProjectName.of(input.getProjectName()), new ArrayList<Task>());
        repository.save(projectList);
        return CqrsOutput.create().setExitCode(ExitCode.SUCCESS).setMessage("");
    }
}
