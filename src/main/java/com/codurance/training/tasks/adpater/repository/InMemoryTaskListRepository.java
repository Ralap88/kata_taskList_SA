package com.codurance.training.tasks.adpater.repository;

import com.codurance.training.tasks.entity.ProjectId;
import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.usecase.port.ProjectListMapper;
import com.codurance.training.tasks.usecase.port.out.TaskListRepository;
import com.codurance.training.tasks.usecase.port.out.TaskListRepositoryPeer;

import java.util.Optional;

public class InMemoryTaskListRepository implements TaskListRepository {
    private final TaskListRepositoryPeer repositoryPeer;

    public InMemoryTaskListRepository(TaskListRepositoryPeer repositoryPeer) {
        this.repositoryPeer = repositoryPeer;
    }

    @Override
    public Optional<ProjectList> findById(ProjectId id) {
        return repositoryPeer.findById(id.value()).map(ProjectListMapper::toDomain);
    }

    @Override
    public void save(ProjectList projectList) {
        repositoryPeer.save(ProjectListMapper.toDto(projectList));
    }

    @Override
    public void delete(ProjectList data) {
        repositoryPeer.delete(ProjectListMapper.toDto(data));
    }
}
