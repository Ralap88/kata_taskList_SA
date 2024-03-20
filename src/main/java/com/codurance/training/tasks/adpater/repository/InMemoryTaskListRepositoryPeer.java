package com.codurance.training.tasks.adpater.repository;

import com.codurance.training.tasks.usecase.port.ProjectListDto;
import com.codurance.training.tasks.usecase.port.out.TaskListRepositoryPeer;

import java.util.*;

public class InMemoryTaskListRepositoryPeer implements TaskListRepositoryPeer {
    private final Map<String, ProjectListDto> store;

    public InMemoryTaskListRepositoryPeer() {
        store = new HashMap<>();
    }

    @Override
    public Optional<ProjectListDto> findById(String id) {
        ProjectListDto checkListDto = store.getOrDefault(id, null);
        if (null == checkListDto) {
            return Optional.empty();
        }
        return Optional.of(checkListDto);
    }

    @Override
    public void save(ProjectListDto projectListDto) {
        store.put(projectListDto.getId(), projectListDto);
    }

    @Override
    public void delete(ProjectListDto projectListDto) {
        store.remove(projectListDto.getId());
    }
}
