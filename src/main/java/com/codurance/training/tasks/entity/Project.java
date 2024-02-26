package com.codurance.training.tasks.entity;

import com.codurance.training.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private ProjectName name;
    private List<Task> tasks;

    public Project(ProjectName projectName, ArrayList<Task> tasks) {
        this.name = projectName;
        this.tasks = tasks;
    }

    public ProjectName getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
