package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.TaskId;

import java.io.PrintWriter;

public class Check {

    private ProjectList projectsList;
    private PrintWriter out;

    public Check(ProjectList projectsList, PrintWriter out) {
        this.projectsList = projectsList;
        this.out = out;
    }

    public void check(TaskId idString, boolean done) {
        setDone(idString, done);
    }

    private void setDone(TaskId idString, boolean done) {
        if(!projectsList.containTask(idString)) {
            out.printf("Could not find a task with an ID of %s.", idString);
            out.println();
            return;
        }
        projectsList.setDone(idString, done);
    }
}
