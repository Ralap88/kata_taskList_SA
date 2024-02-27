package com.codurance.training.tasks.entity;

import java.util.*;
import java.util.stream.Collectors;

public class ProjectList {

    private final List<Project> projects = new ArrayList<>();
    private long lastId = 0;

    public Set<Map.Entry<ProjectName, List<Task>>> entrySet() {
        return projects.stream().map(x -> new AbstractMap.SimpleEntry<>(x.getName(), x.getTasks())).collect(Collectors.toSet());
    }

    public void put(ProjectName projectName, ArrayList<Task> tasks) {
        this.projects.add(new Project(projectName, tasks));
    }

    public Optional<Project> getProject(ProjectName projectName) {
        return projects.stream().filter(x -> x.getName().equals(projectName)).findFirst();
    }

    public void addTask(String projectName, String description, boolean done) {
        this.getProject(ProjectName.of(projectName)).get().addTask(new Task(TaskId.of(nextId()), description, false));
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
