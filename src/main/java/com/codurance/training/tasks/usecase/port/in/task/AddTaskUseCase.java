package com.codurance.training.tasks.usecase.port.in.task;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface AddTaskUseCase {
    CqrsOutput execute(AddTaskInput input);
}
