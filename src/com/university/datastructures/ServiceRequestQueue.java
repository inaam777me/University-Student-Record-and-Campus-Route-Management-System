package com.university.datastructures;

import com.university.model.ServiceRequest;

/** Custom linked-node FIFO queue for pending student service requests. */
public class ServiceRequestQueue {
    private static class Node {
        private final ServiceRequest request;
        private Node next;

        private Node(ServiceRequest request) {
            this.request = request;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    /** Adds to the rear; returns false for a duplicate ID still pending in the queue. */
    public boolean enqueue(ServiceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Service request cannot be null.");
        }
        if (containsRequestId(request.getRequestId())) {
            return false;
        }

        Node newNode = new Node(request);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
        return true;
    }

    /** Removes and returns the oldest request, or null when empty. */
    public ServiceRequest dequeue() {
        if (isEmpty()) {
            return null;
        }
        ServiceRequest request = front.request;
        front = front.next;
        size--;
        if (front == null) {
            rear = null;
        }
        return request;
    }

    /** Returns the oldest request without removing it, or null when empty. */
    public ServiceRequest peek() {
        return isEmpty() ? null : front.request;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }

    public boolean containsRequestId(String requestId) {
        if (requestId == null || requestId.trim().isEmpty()) {
            return false;
        }
        Node current = front;
        while (current != null) {
            if (current.request.getRequestId().equalsIgnoreCase(requestId.trim())) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Prints pending requests in arrival order. */
    public void display() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        Node current = front;
        while (current != null) {
            System.out.println(current.request);
            current = current.next;
        }
    }
}