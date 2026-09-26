package com.university.model;

import java.util.Objects;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 1 - Student Record Model
 *
 * Represents an individual student entity within the university system.
 * Holds essential student details: Student ID, Name, Programme, and Marks.
 */
public class Student {
    private String studentId;
    private String name;
    private String programme;
    private double marks;

    /**
     * Default constructor.
     */
    public Student() {
        this.studentId = "";
        this.name = "";
        this.programme = "";
        this.marks = 0.0;
    }

    /**
     * Parameterized constructor to initialize a student record.
     *
     * @param studentId Unique student identifier (e.g., "S101")
     * @param name      Full name of the student
     * @param programme Academic programme / degree (e.g., "BSc Computer Science")
     * @param marks     Academic marks (0.0 to 100.0)
     * @throws IllegalArgumentException if marks are out of range [0, 100] or fields are invalid
     */
    public Student(String studentId, String name, String programme, double marks) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student Name cannot be null or empty.");
        }
        if (programme == null || programme.trim().isEmpty()) {
            throw new IllegalArgumentException("Programme cannot be null or empty.");
        }
        if (!isValidMarks(marks)) {
            throw new IllegalArgumentException("Marks must be between 0.0 and 100.0. Provided: " + marks);
        }

        this.studentId = studentId.trim();
        this.name = name.trim();
        this.programme = programme.trim();
        this.marks = marks;
    }

    /**
     * Static utility method to validate marks within the standard academic range [0.0, 100.0].
     *
     * @param marks The mark to evaluate
     * @return true if valid, false otherwise
     */
    public static boolean isValidMarks(double marks) {
        return marks >= 0.0 && marks <= 100.0;
    }

    // --- Getters and Setters ---

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        this.studentId = studentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student Name cannot be null or empty.");
        }
        this.name = name.trim();
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        if (programme == null || programme.trim().isEmpty()) {
            throw new IllegalArgumentException("Programme cannot be null or empty.");
        }
        this.programme = programme.trim();
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        if (!isValidMarks(marks)) {
            throw new IllegalArgumentException("Marks must be between 0.0 and 100.0. Provided: " + marks);
        }
        this.marks = marks;
    }

    /**
     * Computes letter grade based on marks.
     * Useful for Member 3 (BST/AVL reports) and overall analytics.
     *
     * @return Letter grade as a String
     */
    public String getGrade() {
        if (marks >= 80.0) return "A";
        if (marks >= 70.0) return "B";
        if (marks >= 60.0) return "C";
        if (marks >= 50.0) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name=%s, Programme=%s, Marks=%.2f, Grade=%s]",
                studentId, name, programme, marks, getGrade());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equalsIgnoreCase(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId.toLowerCase());
    }
}
