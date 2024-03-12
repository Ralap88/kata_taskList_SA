package com.codurance.training.tasks.adpater;

import com.codurance.training.tasks.TaskList;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.TaskId;
import com.codurance.training.tasks.usecase.port.*;
import com.codurance.training.tasks.usecase.port.project.AddProjectInput;
import com.codurance.training.tasks.usecase.port.project.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.task.AddTaskInput;
import com.codurance.training.tasks.usecase.port.task.AddTaskUseCase;
import com.codurance.training.tasks.usecase.service.*;

import java.io.PrintWriter;

public class TaskListController {
    private final ProjectList projectsList;
    private final PrintWriter out;
    private final ProjectListRepository repository;

    public TaskListController(ProjectList projectsList, PrintWriter out, ProjectListRepository repository) {
        this.projectsList = projectsList;
        this.out = out;
        this.repository = repository;
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                ShowUseCase showUseCase = new ShowService();
                ShowInput showInput = new ShowInput();
                showInput.setProjectList(projectsList);
                out.printf(showUseCase.execute(showInput).getMessage());
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(TaskId.of(commandRest[1]), true);
                break;
            case "uncheck":
                check(TaskId.of(commandRest[1]), false);
                break;
            case "help":
                HelpUseCase helpUseCase = new HelpService();
                out.printf(helpUseCase.execute().getMessage());
                break;
            default:
                ErrorUseCase errorUseCase = new ErrorService();
                ErrorInPut errorInPut = new ErrorInPut();
                errorInPut.setCommand(command);
                out.printf(errorUseCase.execute(errorInPut).getMessage());
                break;
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];

        if (subcommand.equals("project")) {
            AddProjectUseCase addProjectUseCase = new AddProjectService(repository);
            AddProjectInput input = new AddProjectInput();
            input.setProjectName(subcommandRest[1]);
            input.setId(TaskList.DEFAULT_TASK_LIST_ID);
            addProjectUseCase.execute(input);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            AddTaskUseCase addTaskUseCase = new AddTaskService(repository);
            AddTaskInput input = new AddTaskInput();
            input.setId(TaskList.DEFAULT_TASK_LIST_ID);
            input.setProjectName(projectTask[0]);
            input.setDescription(projectTask[1]);
            out.printf(addTaskUseCase.execute(input).getMessage());
        }
    }

    private void check(TaskId idString, boolean done) {
        setDone(idString, done);
    }

    private void setDone(TaskId idString, boolean done) {
        CheckUseCase checkUseCase = new CheckService(repository);
        CheckInput input = new CheckInput();
        input.setId(idString.value());
        input.setDone(done);
        input.setProjecetId(projectsList.getId().value());
        out.printf(checkUseCase.execute(input).getMessage());
    }
}
