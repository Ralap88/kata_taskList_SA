package com.codurance.training.tasks.adpater;

import com.codurance.training.tasks.entity.TaskId;
import com.codurance.training.tasks.usecase.port.*;
import com.codurance.training.tasks.usecase.port.project.AddProjectInput;
import com.codurance.training.tasks.usecase.port.project.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.task.AddTaskInput;
import com.codurance.training.tasks.usecase.port.task.AddTaskUseCase;

import java.io.PrintWriter;

import static com.codurance.training.tasks.TaskList.DEFAULT_TASK_LIST_ID;

public class TaskListController {
    private final PrintWriter out;
    private ShowUseCase showUseCase;
    private AddProjectUseCase addProjectUseCase;
    private AddTaskUseCase addTaskUseCase;
    private CheckUseCase checkUseCase;
    private HelpUseCase helpUseCase;
    private ErrorUseCase errorUseCase;

    public TaskListController(PrintWriter out,
                              ShowUseCase showUseCase,
                              AddProjectUseCase addProjectUseCase,
                              AddTaskUseCase addTaskUseCase,
                              CheckUseCase checkUseCase,
                              HelpUseCase helpUseCase,
                              ErrorUseCase errorUseCase) {
        this.out = out;
        this.showUseCase = showUseCase;
        this.addProjectUseCase = addProjectUseCase;
        this.addTaskUseCase = addTaskUseCase;
        this.checkUseCase = checkUseCase;
        this.helpUseCase = helpUseCase;
        this.errorUseCase = errorUseCase;
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                ShowInput showInput = new ShowInput();
                showInput.setProjectId(DEFAULT_TASK_LIST_ID);
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
                out.printf(helpUseCase.execute().getMessage());
                break;
            default:
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
            AddProjectInput input = new AddProjectInput();
            input.setProjectName(subcommandRest[1]);
            input.setId(DEFAULT_TASK_LIST_ID);
            addProjectUseCase.execute(input);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            AddTaskInput input = new AddTaskInput();
            input.setId(DEFAULT_TASK_LIST_ID);
            input.setProjectName(projectTask[0]);
            input.setDescription(projectTask[1]);
            out.printf(addTaskUseCase.execute(input).getMessage());
        }
    }

    private void check(TaskId idString, boolean done) {
        setDone(idString, done);
    }

    private void setDone(TaskId idString, boolean done) {
        CheckInput input = new CheckInput();
        input.setId(idString.value());
        input.setDone(done);
        input.setProjecetId(DEFAULT_TASK_LIST_ID);
        out.printf(checkUseCase.execute(input).getMessage());
    }
}
