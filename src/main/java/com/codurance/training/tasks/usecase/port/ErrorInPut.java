package com.codurance.training.tasks.usecase.port;

public class ErrorInPut {
    private String command;
    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
