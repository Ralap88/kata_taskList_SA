package com.codurance.training.tasks.usecase.port.in.error;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface ErrorUseCase {
    CqrsOutput execute(ErrorInPut inPut);

}
