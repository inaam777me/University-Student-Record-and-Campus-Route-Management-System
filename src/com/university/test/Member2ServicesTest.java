package com.university.test;

import com.university.datastructures.ActionStack;
import com.university.datastructures.ServiceRequestQueue;
import com.university.management.StudentManager;
import com.university.management.StudentServiceManager;
import com.university.model.Action;
import com.university.model.ServiceRequest;

/** Focused executable tests for Member 2's custom stack, queue, and service operations. */
public class Member2ServicesTest {
    private static int passed;
    private static int total;

    public static void main(String[] args) {
        testActionStack();
        testRequestQueue();
        testStudentServiceIntegration();
        System.out.printf("Member 2 tests: %d/%d passed.%n", passed, total);
        if (passed != total) {
            throw new AssertionError("One or more Member 2 tests failed.");
        }
    }

    private static void testActionStack() {
        ActionStack stack = new ActionStack();
        check("Empty stack reports empty", stack.isEmpty());
        check("Empty stack pop returns null", stack.pop() == null);
        check("Empty stack peek returns null", stack.peek() == null);

        Action add = new Action("STUDENT_ADDED", "S201", "Added student");
        Action update = new Action("STUDENT_UPDATED", "S201", "Updated student");
        stack.push(add);
        stack.push(update);
        check("Stack peek returns latest action", stack.peek() == update);
        check("Stack pop is LIFO", stack.pop() == update && stack.pop() == add);
        check("Stack is empty after final pop", stack.isEmpty());
    }

    private static void testRequestQueue() {
        ServiceRequestQueue queue = new ServiceRequestQueue();
        check("Empty queue reports empty", queue.isEmpty());
        check("Empty queue dequeue returns null", queue.dequeue() == null);

        ServiceRequest first = new ServiceRequest("R1", "S201", "Ada Student", "Transcript");
        ServiceRequest second = new ServiceRequest("R2", "S202", "Ben Student", "ID card");
        check("First request enqueues", queue.enqueue(first));
        check("Duplicate pending request ID is rejected",
                !queue.enqueue(new ServiceRequest("r1", "S202", "Ben Student", "Another request")));
        check("Second request enqueues", queue.enqueue(second));
        check("Queue peek returns oldest request", queue.peek() == first);
        check("Queue dequeue is FIFO", queue.dequeue() == first && queue.dequeue() == second);
        check("Queue is empty after final dequeue", queue.isEmpty());

        boolean invalidRejected = false;
        try {
            new ServiceRequest("R3", "S203", "   ", "Library access");
        } catch (IllegalArgumentException exception) {
            invalidRejected = true;
        }
        check("Invalid request information is rejected", invalidRejected);
    }

    private static void testStudentServiceIntegration() {
        StudentManager students = new StudentManager();
        StudentServiceManager services = new StudentServiceManager(students);
        check("Request for missing student is rejected",
                !services.addServiceRequest("R1", "S999", "Transcript"));
        check("Blank request information is rejected",
                !services.addServiceRequest("R1", "S201", " "));

        check("Student can be added", students.addStudent("S201", "Ada Student", "BSc Computing", 85.0));
        check("Successful add is recorded", "STUDENT_ADDED".equals(students.getActionStack().peek().getActionType()));
        check("Service request is accepted", services.addServiceRequest("R1", "S201", "Transcript"));
        check("Duplicate pending request is rejected",
                !services.addServiceRequest("r1", "S201", "Second transcript"));
        check("Pending request can be peeked", "R1".equals(services.peekNextRequest().getRequestId()));
        check("Request is processed", "R1".equals(services.processNextRequest().getRequestId()));
        check("Processed service is recorded",
                "SERVICE_REQUEST_PROCESSED".equals(students.getActionStack().peek().getActionType()));
        check("Missing request process returns null", services.processNextRequest() == null);

        check("Student update is recorded", students.updateStudent("S201", "Ada A.", "BSc Computing", 87.0));
        check("Student delete is recorded", students.deleteStudent("S201"));
        check("Delete is the newest action", "STUDENT_DELETED".equals(students.getActionStack().peek().getActionType()));
    }

    private static void check(String name, boolean condition) {
        total++;
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            System.err.println("[FAIL] " + name);
        }
    }
}