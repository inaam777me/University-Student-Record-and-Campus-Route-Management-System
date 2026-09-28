package com.university.model;

/** A validated service request submitted by an enrolled student. */
public class ServiceRequest {
    private final String requestId;
    private final String studentId;
    private final String studentName;
    private final String description;

    public ServiceRequest(String requestId, String studentId, String studentName, String description) {
        this.requestId = requireText(requestId, "Request ID");
        this.studentId = requireText(studentId, "Student ID");
        this.studentName = requireText(studentName, "Student name");
        this.description = requireText(description, "Request description");
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    public String getRequestId() {
        return requestId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Request %s | Student: %s (%s) | %s",
                requestId, studentName, studentId, description);
    }
}