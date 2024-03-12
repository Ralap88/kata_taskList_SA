package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.ProjectList;

public class ProjectListMapper {
    public ProjectListDto toDto(ProjectList projectList) {
        return new ProjectListDto(projectList.getProjects().stream().map(ProjectMapper::toDto).toList(), projectList.getId());
    }

    public ProjectList toDomain(ProjectListDto projectListDto) {
        return new ProjectList(projectListDto.getId());
    }
}
