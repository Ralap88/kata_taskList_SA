package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.entity.Task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;

public class Add {

    private ProjectList projectsList;
    private PrintWriter out;

    public Add(ProjectList projectsList, PrintWriter out) {
        this.projectsList = projectsList;
        this.out = out;
    }

    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        projectsList.put(ProjectName.of(name), new ArrayList<Task>());
    }

    private void addTask(String projectName, String description) {
        Optional<Project> project = projectsList.getProject(ProjectName.of(projectName));
        if(project.isEmpty()) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectsList.addTask(projectName, description, false);
    }
}
