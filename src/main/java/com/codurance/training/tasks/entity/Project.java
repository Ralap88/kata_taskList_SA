package com.codurance.training.tasks.entity;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private ProjectName name;
    private List<Task> tasks;

    public Project(ProjectName projectName) {
        this.name = projectName;
        this.tasks = new ArrayList<>();
    }

    public static Project of(ProjectName name) {
        return new Project(name);
    }

    public ProjectName getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean containTask(TaskId idString) {
        return tasks.stream().anyMatch(t -> t.getId().equals(idString));
    }

    public void setTaskDone(TaskId id, boolean done) {
        tasks.stream().filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(t -> t.setDone(done));
    }
}