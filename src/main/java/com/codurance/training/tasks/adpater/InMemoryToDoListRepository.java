package com.codurance.training.tasks.adpater;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.usecase.port.in.ProjectListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryToDoListRepository implements ProjectListRepository {
    private final List<ProjectList> store = new ArrayList<>();
    @Override
    public Optional<ProjectList> findById(ProjectId id) {
        return store.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public void save(ProjectList projectList) {
        store.add(projectList);
    }
}
