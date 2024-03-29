package com.codurance.training.tasks.usecase.service;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.TaskId;
import com.codurance.training.tasks.usecase.port.in.check.CheckInput;
import com.codurance.training.tasks.usecase.port.in.check.CheckUseCase;
import com.codurance.training.tasks.usecase.port.out.TaskListRepository;
import tw.teddysoft.ezddd.core.usecase.ExitCode;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import static java.lang.String.format;

public class CheckService implements CheckUseCase {
    private final TaskListRepository repository;

    public CheckService(TaskListRepository repository) {
        this.repository = repository;
    }

    @Override
    public CqrsOutput execute(CheckInput input) {
        return setDone(ProjectId.of(input.getProjecetId()), TaskId.of(input.getId()), input.isDone());
    }

    private CqrsOutput setDone(ProjectId projectId, TaskId idString, boolean done) {
        ProjectList projectList = repository.findById(projectId).get();

        if(!projectList.containTask(idString)) {
            StringBuilder sb = new StringBuilder();
            sb.append(format("Could not find a task with an ID of %s.", idString));
            sb.append("\n");
            return CqrsOutput.create().setExitCode(ExitCode.FAILURE).setMessage(sb.toString());
        }

        projectList.setDone(idString, done);
        repository.save(projectList);

        return CqrsOutput.create().setExitCode(ExitCode.SUCCESS).setMessage("");
    }
}
