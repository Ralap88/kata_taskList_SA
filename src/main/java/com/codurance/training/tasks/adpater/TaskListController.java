package com.codurance.training.tasks.adpater;

import com.codurance.training.tasks.adpater.presenter.HelpPresenter;
import com.codurance.training.tasks.adpater.presenter.ShowPresenter;
import com.codurance.training.tasks.entity.TaskId;
import com.codurance.training.tasks.usecase.port.in.check.CheckInput;
import com.codurance.training.tasks.usecase.port.in.check.CheckUseCase;
import com.codurance.training.tasks.usecase.port.in.error.ErrorInPut;
import com.codurance.training.tasks.usecase.port.in.error.ErrorUseCase;
import com.codurance.training.tasks.usecase.port.in.help.HelpUseCase;
import com.codurance.training.tasks.usecase.port.in.project.AddProjectInput;
import com.codurance.training.tasks.usecase.port.in.project.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.show.ShowInput;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;
import com.codurance.training.tasks.usecase.port.in.task.AddTaskInput;
import com.codurance.training.tasks.usecase.port.in.task.AddTaskUseCase;

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
                var showOutput = showUseCase.execute(showInput);
                ShowPresenter.present(out, showOutput.getProjects());
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
                var helpOutPut = helpUseCase.execute();
                HelpPresenter.present(out, helpOutPut.getResult());
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
