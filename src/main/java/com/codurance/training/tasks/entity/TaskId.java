package com.codurance.training.tasks.entity;

public record TaskId(String value) {
    public static TaskId of(long value) {
        return new TaskId(String.valueOf(value));
    }

    public static TaskId of(String value) {
        return new TaskId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
