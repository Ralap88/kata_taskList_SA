package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.TaskId;

import java.io.PrintWriter;

public class Execute {
    private ProjectList projectsList;
    private PrintWriter out;

    public Execute(ProjectList projectsList, PrintWriter out) {
        this.projectsList = projectsList;
        this.out = out;
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                new Show(projectsList, out).show();
                break;
            case "add":
                new Add(projectsList, out).add(commandRest[1]);
                break;
            case "check":
                new Check(projectsList, out).check(TaskId.of(commandRest[1]), true);
                break;
            case "uncheck":
                new Check(projectsList, out).check(TaskId.of(commandRest[1]), false);
                break;
            case "help":
                new Help(out).help();
                break;
            default:
                new Error(out).error(command);
                break;
        }
    }
}
