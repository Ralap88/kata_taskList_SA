package com.codurance.training.tasks.entity;

public record ProjectId(String value) {
    public static ProjectId of(String value) {
        return new ProjectId(value);
    }
}
