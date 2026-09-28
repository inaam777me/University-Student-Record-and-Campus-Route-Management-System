package com.university.management;

import com.university.datastructures.ActionStack;
import com.university.datastructures.ServiceRequestQueue;
import com.university.model.Action;
import com.university.model.ServiceRequest;
import com.university.model.Student;

/** Coordinates student validation, FIFO service requests, and action history. */
public class StudentServiceManager {
    private final StudentManager studentManager;
    private final ServiceRequestQueue requestQueue;
    private final ActionStack actionStack;

    public StudentServiceManager(StudentManager studentManager) {
        if (studentManager == null) {
            throw new IllegalArgumentException("StudentManager cannot be null.");
        }
        this.studentManager = studentManager;
        this.requestQueue = new ServiceRequestQueue();
        this.actionStack = studentManager.getActionStack();
    }

    /** Adds a request only when all fields are valid and the student is registered. */
    public boolean addServiceRequest(String requestId, String studentId, String description) {
        if (isBlank(requestId) || isBlank(studentId) || isBlank(description)) {
            return false;
        }
        if (requestQueue.containsRequestId(requestId)) {
            return false;
        }
        Student student = studentManager.getStudentList().searchStudent(studentId);
        if (student == null) {
            return false;
        }
        ServiceRequest request = new ServiceRequest(requestId, student.getStudentId(),
                student.getName(), description);
        return requestQueue.enqueue(request);
    }

    /** Processes the oldest pending request and records its completion in action history. */
    public ServiceRequest processNextRequest() {
        ServiceRequest request = requestQueue.dequeue();
        if (request != null) {
            actionStack.push(new Action("SERVICE_REQUEST_PROCESSED", request.getStudentId(),
                    "Processed request " + request.getRequestId() + ": " + request.getDescription()));
        }
        return request;
    }

    public ServiceRequest peekNextRequest() {
        return requestQueue.peek();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }

    public int getPendingRequestCount() {
        return requestQueue.getSize();
    }

    public void displayPendingRequests() {
        requestQueue.display();
    }

    public ActionStack getActionStack() {
        return actionStack;
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}