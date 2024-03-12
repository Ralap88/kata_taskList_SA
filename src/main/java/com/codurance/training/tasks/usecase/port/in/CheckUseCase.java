package com.codurance.training.tasks.usecase.port.in;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface CheckUseCase {
    CqrsOutput execute(CheckInput input);
}
