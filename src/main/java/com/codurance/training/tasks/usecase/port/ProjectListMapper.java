package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;

import java.util.List;

public class ProjectListMapper {
    public static ProjectListDto toDto(ProjectList projectList) {
        return new ProjectListDto(projectList.getProjects().stream().map(ProjectMapper::toDto).toList(), projectList.getId().value());
    }

    public static ProjectList toDomain(ProjectListDto projectListDto) {
        List<Project> projects = projectListDto.getProjectDtos().stream().map(ProjectMapper::toDomain).toList();
        ProjectList projectList = new ProjectList(ProjectId.of(projectListDto.getId()));
        for (Project project: projects) {
            projectList.addProject(project);
        }
        return projectList;
    }
}
