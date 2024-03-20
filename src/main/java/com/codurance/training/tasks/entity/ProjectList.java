package com.codurance.training.tasks.entity;

import tw.teddysoft.ezddd.core.entity.AggregateRoot;
import tw.teddysoft.ezddd.core.entity.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectList extends AggregateRoot<ProjectId, DomainEvent> {

    private final List<Project> projects;
    private final ProjectId id;
    private static int lastId = 0;

    public ProjectList(ProjectId id) {
        projects = new ArrayList<>();
        this.id = id;
    }

    public void add(Project project) {
        projects.add(project);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public Optional<Project> getProject(ProjectName projectName) {
        return projects.stream().filter(x -> x.getName().equals(projectName)).findFirst();
    }

    public void addTask(String projectName, String description, boolean done) {
        getProject(ProjectName.of(projectName)).get().addTask(new Task(TaskId.of(nextId()), description, done));
    }

    public void addProject(Project project) {
        projects.add(project);
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

    public ProjectId getId() {
        return id;
    }
}
