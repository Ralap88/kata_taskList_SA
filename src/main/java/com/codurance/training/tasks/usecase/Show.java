package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.Task;

import java.io.PrintWriter;

public class Show {
    private ProjectList projectsList;
    private PrintWriter out;

    public Show(ProjectList projectsList, PrintWriter out) {
        this.projectsList = projectsList;
        this.out = out;
    }

    public void show() {
        for (Project project : projectsList.getProjects()) {
            out.println(project.getName());
            for (Task task : project.getTasks()) {
                out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}
