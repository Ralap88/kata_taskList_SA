package com.codurance.training.tasks;


import com.codurance.training.tasks.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final ProjectList projectsList = new ProjectList();
    private final BufferedReader in;
    private final PrintWriter out;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
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
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(TaskId.of(commandRest[1]));
                break;
            case "uncheck":
                uncheck(TaskId.of(commandRest[1]));
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() {
        for (Map.Entry<ProjectName, List<Task>> project : projectsList.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        projectsList.put(ProjectName.of(name), new ArrayList<Task>());
    }

    private void addTask(String projectName, String description) {
        Optional<Project> project = projectsList.getProject(ProjectName.of(projectName));
        if(project.isEmpty()) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectsList.addTask(projectName, description, false);
    }

    private void check(TaskId idString) {
        setDone(idString, true);
    }

    private void uncheck(TaskId idString) {
        setDone(idString, false);
    }

    private void setDone(TaskId idString, boolean done) {
        if(!projectsList.containTask(idString)) {
            out.printf("Could not find a task with an ID of %s.", idString);
            out.println();
            return;
        }
        projectsList.setDone(idString, done);
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}