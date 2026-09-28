package com.university.datastructures;

import com.university.model.Action;

/** Custom linked-node LIFO stack for recent system actions. */
public class ActionStack {
    private static class Node {
        private final Action action;
        private Node next;

        private Node(Action action, Node next) {
            this.action = action;
            this.next = next;
        }
    }

    private Node top;
    private int size;

    public void push(Action action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null.");
        }
        top = new Node(action, top);
        size++;
    }

    /** Removes and returns the latest action, or null when empty. */
    public Action pop() {
        if (isEmpty()) {
            return null;
        }
        Action action = top.action;
        top = top.next;
        size--;
        return action;
    }

    /** Returns the latest action without removing it, or null when empty. */
    public Action peek() {
        return isEmpty() ? null : top.action;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }

    /** Prints actions from newest to oldest. */
    public void display() {
        if (isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }
        Node current = top;
        while (current != null) {
            System.out.println(current.action);
            current = current.next;
        }
    }
}