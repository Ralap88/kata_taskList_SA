package com.codurance.training.tasks;


import com.codurance.training.tasks.adpater.repository.InMemoryTaskListRepositoryPeer;
import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.adpater.TaskListController;
import com.codurance.training.tasks.adpater.repository.InMemoryTaskListRepository;
import com.codurance.training.tasks.usecase.port.in.check.CheckUseCase;
import com.codurance.training.tasks.usecase.port.in.error.ErrorUseCase;
import com.codurance.training.tasks.usecase.port.in.help.HelpUseCase;
import com.codurance.training.tasks.usecase.port.in.show.ShowUseCase;
import com.codurance.training.tasks.usecase.port.in.project.AddProjectUseCase;
import com.codurance.training.tasks.usecase.port.in.task.AddTaskUseCase;
import com.codurance.training.tasks.usecase.port.out.TaskListRepository;
import com.codurance.training.tasks.usecase.port.out.TaskListRepositoryPeer;
import com.codurance.training.tasks.usecase.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    public static final String DEFAULT_TASK_LIST_ID = "001";
    private final BufferedReader in;
    private final PrintWriter out;
    private ShowUseCase showUseCase;
    private AddProjectUseCase addProjectUseCase;
    private AddTaskUseCase addTaskUseCase;
    private CheckUseCase checkUseCase;
    private HelpUseCase helpUseCase;
    private ErrorUseCase errorUseCase;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        TaskListRepositoryPeer inMemoryTaskListRepositoryPeer = new InMemoryTaskListRepositoryPeer();
        TaskListRepository repository = new InMemoryTaskListRepository(inMemoryTaskListRepositoryPeer);

        if (repository.findById(ProjectId.of(DEFAULT_TASK_LIST_ID)).isEmpty()) {
            repository.save(new ProjectList(ProjectId.of(DEFAULT_TASK_LIST_ID)));
        }

        ShowUseCase showUseCase = new ShowService(repository);
        AddProjectUseCase addProjectUseCase = new AddProjectService(repository);
        AddTaskUseCase addTaskUseCase = new AddTaskService(repository);
        CheckUseCase checkUseCase = new CheckService(repository);
        HelpUseCase helpUseCase = new HelpService();
        ErrorUseCase errorUseCase = new ErrorService();

        new TaskList(in, out, showUseCase, addProjectUseCase, addTaskUseCase, checkUseCase, helpUseCase, errorUseCase).run();
    }

    public TaskList(BufferedReader reader,
                    PrintWriter writer,
                    ShowUseCase showUseCase,
                    AddProjectUseCase addProjectUseCase,
                    AddTaskUseCase addTaskUseCase,
                    CheckUseCase checkUseCase,
                    HelpUseCase helpUseCase,
                    ErrorUseCase errorUseCase) {
        this.in = reader;
        this.out = writer;
        this.showUseCase = showUseCase;
        this.addProjectUseCase = addProjectUseCase;
        this.addTaskUseCase = addTaskUseCase;
        this.checkUseCase = checkUseCase;
        this.helpUseCase = helpUseCase;
        this.errorUseCase = errorUseCase;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            new TaskListController(out,
                    showUseCase,
                    addProjectUseCase,
                    addTaskUseCase,
                    checkUseCase,
                    helpUseCase,
                    errorUseCase).execute(command);
        }
    }
}