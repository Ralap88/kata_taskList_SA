package com.codurance.training.tasks.usecase.port;

import com.codurance.training.tasks.entity.ProjectId;
import tw.teddysoft.ezddd.core.usecase.StoreData;
import tw.teddysoft.ezddd.core.usecase.domainevent.DomainEventData;

import java.util.List;

public class ProjectListDto implements StoreData {
    private final List<ProjectDto> projectDtos;
    private final String id;

    public ProjectListDto(List<ProjectDto> projectDtos, String id) {
        this.projectDtos = projectDtos;
        this.id = id;
    }

    public List<ProjectDto> getProjectDtos() {
        return projectDtos;
    }

    @Override
    public long getVersion() {
        return 0;
    }

    @Override
    public void setVersion(long version) {

    }

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public List<DomainEventData> getDomainEventDatas() {
        return null;
    }

    @Override
    public void setDomainEventDatas(List<DomainEventData> domainEventDatas) {

    }

    @Override
    public String getStreamName() {
        return null;
    }

    @Override
    public void setStreamName(String streamName) {

    }
}
