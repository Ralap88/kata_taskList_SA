package com.codurance.training.tasks.adpater.presenter;

import com.codurance.training.tasks.usecase.port.ProjectDto;
import com.codurance.training.tasks.usecase.port.TaskDto;

import java.io.PrintWriter;
import java.util.List;

import static java.lang.String.format;

public class ShowPresenter {
    public static void present(PrintWriter out, List<ProjectDto> projectDtos) {
        for (ProjectDto projectDto : projectDtos) {
            out.println(projectDto.getName());
            for (TaskDto taskDto : projectDto.getTaskDtos()) {
                out.printf(format("    [%c] %s: %s%n", (taskDto.isDone() ? 'x' : ' '), taskDto.getId(), taskDto.getDescription()));
            }
            out.println();
        }
    }
}
