package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.Task;
import com.codurance.training.tasks.entity.TaskId;

public class TaskMapper {
    public static TaskDto toDto(Task task) {
        return new TaskDto(task.getId().value(), task.getDescription(), task.isDone());
    }

    public static Task toDomain(TaskDto taskDto) {
        return new Task(TaskId.of(taskDto.getId()), taskDto.getDescription(), taskDto.isDone());
    }
}
