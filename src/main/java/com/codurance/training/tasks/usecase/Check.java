package com.codurance.training.tasks.usecase;

import com.codurance.training.tasks.entity.ProjectList;
import com.codurance.training.tasks.entity.TaskId;
import com.codurance.training.tasks.usecase.port.CheckInput;
import com.codurance.training.tasks.usecase.port.CheckUseCase;
import com.codurance.training.tasks.usecase.port.ProjectListRepository;
import com.codurance.training.tasks.usecase.service.CheckService;

import java.io.PrintWriter;

public class Check {

    private ProjectList projectsList;
    private final ProjectListRepository repository;
    private PrintWriter out;

    public Check(ProjectList projectsList, ProjectListRepository repository, PrintWriter out) {
        this.projectsList = projectsList;
        this.repository = repository;
        this.out = out;
    }

    public void check(TaskId idString, boolean done) {
        setDone(idString, done);
    }

    private void setDone(TaskId idString, boolean done) {
            CheckUseCase checkUseCase = new CheckService(repository);
            CheckInput input = new CheckInput();
            input.id = idString.value();
            input.done = done;
            input.projecetId = projectsList.getId().value();
            out.printf(checkUseCase.execute(input).getMessage());
        }
    }
