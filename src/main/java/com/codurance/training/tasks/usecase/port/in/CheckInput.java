package com.codurance.training.tasks.usecase.port.in;

public class CheckInput {
    private String id;
    private String projecetId;
    private boolean done;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjecetId() {
        return projecetId;
    }

    public void setProjecetId(String projecetId) {
        this.projecetId = projecetId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
