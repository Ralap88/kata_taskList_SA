package com.codurance.training.tasks.entity;

import java.util.*;

public class ProjectList {

    private final List<Project> projects = new ArrayList<>();
    private long lastId = 0;

    public void put(ProjectName projectName, ArrayList<Task> tasks) {
        this.projects.add(new Project(projectName, tasks));
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Optional<Project> getProject(ProjectName projectName) {
        return projects.stream().filter(x -> x.getName().equals(projectName)).findFirst();
    }

    public void addTask(String projectName, String description, boolean done) {
        this.getProject(ProjectName.of(projectName)).get().addTask(new Task(TaskId.of(nextId()), description, done));
    }

    private long nextId() {
        return ++lastId;
    }

    public boolean containTask(TaskId idString) {
        return projects.stream().anyMatch(p -> p.containTask(idString));
    }

    public void setDone(TaskId idString, boolean done) {
        projects.stream().filter(p -> p.containTask(idString))
                .findFirst()
                .ifPresent(p -> p.setTaskDone(idString, done));
    }
}
