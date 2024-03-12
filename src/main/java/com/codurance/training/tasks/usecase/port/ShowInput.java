package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.ProjectList;

public class ShowInput {
    private ProjectList projectList;

    public ProjectList getProjectList() {
        return projectList;
    }

    public void setProjectList(ProjectList projectList) {
        this.projectList = projectList;
    }
}
