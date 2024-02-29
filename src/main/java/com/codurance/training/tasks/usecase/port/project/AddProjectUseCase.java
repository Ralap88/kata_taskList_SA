package com.codurance.training.tasks.usecase.port.project;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface AddProjectUseCase {
    CqrsOutput execute(AddProjectInput input);
}
