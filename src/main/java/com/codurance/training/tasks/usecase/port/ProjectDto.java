package com.codurance.training.tasks.usecase.port;

import java.util.List;

public class ProjectDto {
    private String projectName;
    private List<TaskDto> tasks;

    public ProjectDto(String projectName, List<TaskDto> tasks) {
        this.projectName = projectName;
        this.tasks = tasks;
    }

    public String getName() {
        return projectName;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }
}
