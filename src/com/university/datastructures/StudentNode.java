package com.university.datastructures;

import com.university.model.Student;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 1 - Singly Linked List Node
 *
 * Represents an individual node in the custom singly linked list.
 * Each node wraps a Student object (data payload) and maintains a reference
 * to the next node in the sequence.
 */
public class StudentNode {
    private Student data;
    private StudentNode next;

    /**
     * Constructs a new StudentNode holding the given Student record.
     * The next pointer is initialized to null.
     *
     * @param data The Student object to store in this node
     */
    public StudentNode(Student data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Constructs a new StudentNode holding the given Student record and next node reference.
     *
     * @param data The Student object to store in this node
     * @param next Pointer to the subsequent node in the linked list
     */
    public StudentNode(Student data, StudentNode next) {
        this.data = data;
        this.next = next;
    }

    // --- Getters and Setters ---

    public Student getData() {
        return data;
    }

    public void setData(Student data) {
        this.data = data;
    }

    public StudentNode getNext() {
        return next;
    }

    public void setNext(StudentNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StudentNode{" + "data=" + data + '}';
    }
}
