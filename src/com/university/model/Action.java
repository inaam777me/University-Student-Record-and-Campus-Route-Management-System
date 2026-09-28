package com.university.model;

import java.time.LocalDateTime;

/** Describes a completed operation recorded in the system history. */
public class Action {
    private final String actionType;
    private final String studentId;
    private final String description;
    private final LocalDateTime timestamp;

    public Action(String actionType, String studentId, String description) {
        if (actionType == null || actionType.trim().isEmpty()) {
            throw new IllegalArgumentException("Action type cannot be empty.");
        }
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Action description cannot be empty.");
        }
        this.actionType = actionType.trim();
        this.studentId = studentId.trim();
        this.description = description.trim();
        this.timestamp = LocalDateTime.now();
    }

    public String getActionType() {
        return actionType;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Student: %s | %s", timestamp, actionType, studentId, description);
    }
}