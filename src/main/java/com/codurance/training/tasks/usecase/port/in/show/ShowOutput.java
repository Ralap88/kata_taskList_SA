package com.codurance.training.tasks.usecase.port.in.show;

import com.codurance.training.tasks.usecase.port.ProjectDto;
import tw.teddysoft.ezddd.cqrs.usecase.CqrsOutput;

import java.util.List;

public class ShowOutput extends CqrsOutput<ShowOutput> {
    private List<ProjectDto> projects;
    public ShowOutput SetProjects(List<ProjectDto> projects) {
        this.projects = projects;
        return this;
    }

    public List<ProjectDto> getProjects() {
        return projects;
    }
}
