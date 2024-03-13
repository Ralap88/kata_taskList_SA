package com.codurance.training.tasks.usecase.port.in.project;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface AddProjectUseCase {
    CqrsOutput execute(AddProjectInput input);
}
