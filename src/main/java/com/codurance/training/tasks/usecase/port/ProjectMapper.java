package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectName;

public class ProjectMapper {
    public static ProjectDto toDto(Project project) {
        return new ProjectDto(project.getName().value(), project.getTasks().stream().map(TaskMapper::toDto).toList());
    }

    public Project toDomain(ProjectDto projectDto) {
        return new Project(ProjectName.of(projectDto.getName()), projectDto.getTaskDtos().stream().map(TaskMapper::toDomain).toList());
    }
}
