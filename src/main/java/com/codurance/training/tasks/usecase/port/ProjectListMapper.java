package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;

public class ProjectListMapper {
    public static ProjectListDto toDto(ProjectList projectList) {
        return new ProjectListDto(projectList.getProjects().stream().map(ProjectMapper::toDto).toList(), projectList.getId());
    }

    public static ProjectList toDomain(ProjectListDto projectListDto) {
        return new ProjectList(ProjectId.of(projectListDto.getId()));
    }
}
