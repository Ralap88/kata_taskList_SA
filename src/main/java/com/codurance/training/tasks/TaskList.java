package com.codurance.training.tasks;


import com.codurance.training.tasks.entity.*;
import com.codurance.training.tasks.usecase.Execute;
import com.codurance.training.tasks.adpater.InMemoryToDoListRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    public static final String DEFAULT_TASK_LIST_ID = "001";
    private final ProjectList projectsList = new ProjectList(ProjectId.of(DEFAULT_TASK_LIST_ID));
    private final BufferedReader in;
    private final PrintWriter out;

    private final InMemoryToDoListRepository repository;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        repository = new InMemoryToDoListRepository();
        if (repository.findById(ProjectId.of(DEFAULT_TASK_LIST_ID)).isEmpty()) {
            repository.save(projectsList);
        }
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
            new Execute(projectsList, out, repository).execute(command);
        }
    }
}