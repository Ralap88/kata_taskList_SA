package com.codurance.training.tasks.entity;

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