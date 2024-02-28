package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;

import java.util.Optional;

public interface ProjectListRepository {

    Optional<ProjectList> findById(ProjectId id);

    void save(ProjectList projectList);
}
