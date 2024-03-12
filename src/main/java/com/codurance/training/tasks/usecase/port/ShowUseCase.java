package com.codurance.training.tasks.usecase.port;

import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

public interface ShowUseCase {
    CqrsOutput execute(ShowInput input);
}
