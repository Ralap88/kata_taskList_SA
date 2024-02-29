package com.codurance.training.tasks.usecase.port;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface ErrorUseCase {
    CqrsOutput execute(ErrorInPut inPut);

}
