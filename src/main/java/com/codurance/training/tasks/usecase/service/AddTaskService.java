package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.usecase.port.in.task.AddTaskInput;
import com.codurance.training.tasks.usecase.port.in.task.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.out.TaskListRepository;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static java.lang.String.format;

public class AddTaskService implements AddTaskUseCase {
    private final TaskListRepository repository;
    public AddTaskService(TaskListRepository repository) {
        this.repository = repository;
    }

    @Override
    public CqrsOutput execute(AddTaskInput input) {
        ProjectList projectList = repository.findById(ProjectId.of(input.getId())).orElse(null);

        if(projectList == null) {
            return CqrsOutput.create().setExitCode(ExitCode.FAILURE);
        }

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
