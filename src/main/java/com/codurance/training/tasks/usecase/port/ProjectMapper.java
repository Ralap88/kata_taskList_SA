package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Project;
import com.codurance.training.tasks.entity.ProjectName;
import com.codurance.training.tasks.entity.Task;

import java.util.List;

public class ProjectMapper {
    public static ProjectDto toDto(Project project) {
        return new ProjectDto(project.getName().value(), project.getTasks().stream().map(TaskMapper::toDto).toList());
    }

    public Project toDomain(ProjectDto projectDto) {
        List<Task> tasks = projectDto.getTaskDtos().stream().map(TaskMapper::toDomain).toList();
        Project project = new Project(ProjectName.of(projectDto.getName()));
        for (Task task: tasks) {
            project.addTask(task);
        }
        return project;
    }
}
