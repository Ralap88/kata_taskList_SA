package com.codurance.training.tasks.usecase.port.out;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;
import tw.teddysoft.ezddd.core.usecase.Repository;

public interface TaskListRepository extends Repository<ProjectList, ProjectId> {
}
