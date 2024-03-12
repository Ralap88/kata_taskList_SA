package com.codurance.training.tasks.usecase.port.project;

public class AddProjectInput {
    private String id;
    private String projectName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
