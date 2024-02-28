package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.TaskList;
import com.codurance.training.tasks.usecase.port.project.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.task.AddTaskInput;
import com.codurance.training.tasks.usecase.port.task.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.project.AddProjectInput;
import com.codurance.training.tasks.usecase.service.AddProjectService;
import com.codurance.training.tasks.usecase.service.AddTaskService;
import com.codurance.training.tasks.usecase.port.ProjectListRepository;

import java.io.PrintWriter;

public class Add {
    private final PrintWriter out;
    private final ProjectListRepository repository;

    public Add(PrintWriter out, ProjectListRepository repository) {
        this.out = out;
        this.repository = repository;
    }

    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            AddProjectUseCase addProjectUseCase = new AddProjectService(repository);
            AddProjectInput input = new AddProjectInput();
            input.projectName = subcommandRest[1];
            input.id = TaskList.DEFAULT_TASK_LIST_ID;
            addProjectUseCase.execute(input);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);

            AddTaskUseCase addTaskUseCase = new AddTaskService(repository);
            AddTaskInput input = new AddTaskInput();
            input.id = TaskList.DEFAULT_TASK_LIST_ID;
            input.projectName = projectTask[0];
            input.description = projectTask[1];
            out.printf(addTaskUseCase.execute(input).getMessage());
        }
    }
}
