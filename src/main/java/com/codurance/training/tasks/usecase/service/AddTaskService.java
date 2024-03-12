package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.port.task.AddTaskInput;
import com.codurance.training.tasks.usecase.port.task.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.ProjectListRepository;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static java.lang.String.format;

public class AddTaskService implements AddTaskUseCase {
    private final ProjectListRepository repository;
    public AddTaskService(ProjectListRepository repository) {
        this.repository = repository;
    }

    @Override
    public CqrsOutput execute(AddTaskInput input) {
        ProjectList projectList = repository.findById(ProjectId.of(input.getId())).get();

        if(projectList.getProject(ProjectName.of(input.getProjectName())).isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(format("Could not find a project with the name \"%s\".", ProjectName.of(input.getProjectName())));
            sb.append("\n");
            return CqrsOutput.create().setExitCode(ExitCode.FAILURE).setMessage(sb.toString());
        }

        projectList.addTask(input.getProjectName(), input.getDescription(), false);
        repository.save(projectList);

        return CqrsOutput.create().setExitCode(ExitCode.SUCCESS).setMessage("");
    }
}
