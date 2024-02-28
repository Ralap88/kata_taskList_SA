package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.TaskId;
import com.codurance.training.tasks.usecase.port.*;
import com.codurance.training.tasks.usecase.service.ErrorService;
import com.codurance.training.tasks.usecase.service.HelpService;

import java.io.PrintWriter;

public class Execute {
    private final ProjectList projectsList;
    private final PrintWriter out;
    private final ProjectListRepository repository;

    public Execute(ProjectList projectsList, PrintWriter out, ProjectListRepository repository) {
        this.projectsList = projectsList;
        this.out = out;
        this.repository = repository;
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                new Show(projectsList, out).show();
                break;
            case "add":
                new Add(out, repository).add(commandRest[1]);
                break;
            case "check":
                new Check(projectsList, repository, out).check(TaskId.of(commandRest[1]), true);
                break;
            case "uncheck":
                new Check(projectsList, repository, out).check(TaskId.of(commandRest[1]), false);
                break;
            case "help":
                HelpUseCase helpUseCase = new HelpService();
                out.printf(helpUseCase.execute().getMessage());
                break;
            default:
                ErrorUseCase errorUseCase = new ErrorService();
                ErrorInPut input = new ErrorInPut();
                input.command = command;
                out.printf(errorUseCase.execute(input).getMessage());
                break;
        }
    }
}
