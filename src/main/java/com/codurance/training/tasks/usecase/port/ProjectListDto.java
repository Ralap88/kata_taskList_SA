package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.ProjectId;

import java.util.List;

public class ProjectListDto {
    private final List<ProjectDto> projectDtos;
    private final ProjectId id;

    public ProjectListDto(List<ProjectDto> projectDtos, ProjectId id) {
        this.projectDtos = projectDtos;
        this.id = id;
    }

    public List<ProjectDto> getProjectDtos() {
        return projectDtos;
    }

    public ProjectId getId() {
        return id;
    }
}
