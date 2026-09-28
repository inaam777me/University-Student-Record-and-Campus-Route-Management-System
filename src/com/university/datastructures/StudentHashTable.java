package com.university.datastructures;

import com.university.model.Student;

/** Custom hash table using separate chaining to store students by Student ID. */
public class StudentHashTable {
    private static final int DEFAULT_CAPACITY = 11;

    private static class Entry {
        private final Student student;
        private Entry next;

        private Entry(Student student, Entry next) {
            this.student = student;
            this.next = next;
        }
    }

    private final Entry[] buckets;
    private int size;

    public StudentHashTable() {
        buckets = new Entry[DEFAULT_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public boolean insert(Student student) {
        if (student == null || !isValidId(student.getStudentId())) {
            return false;
        }

        int index = bucketIndex(student.getStudentId());
        for (Entry entry = buckets[index]; entry != null; entry = entry.next) {
            if (sameId(entry.student.getStudentId(), student.getStudentId())) {
                return false;
            }
        }

        buckets[index] = new Entry(student, buckets[index]);
        size++;
        return true;
    }

    public Student search(String studentId) {
        if (!isValidId(studentId)) {
            return null;
        }

        for (Entry entry = buckets[bucketIndex(studentId)]; entry != null; entry = entry.next) {
            if (sameId(entry.student.getStudentId(), studentId)) {
                return entry.student;
            }
        }
        return null;
    }

    public boolean delete(String studentId) {
        if (!isValidId(studentId)) {
            return false;
        }

        int index = bucketIndex(studentId);
        Entry previous = null;
        Entry current = buckets[index];
        while (current != null) {
            if (sameId(current.student.getStudentId(), studentId)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /** Prints each bucket and its chain; an empty table is reported explicitly. */
    public void display() {
        if (isEmpty()) {
            System.out.println("[Info] The hash table is empty; there are no students to display.");
            return;
        }

        for (int index = 0; index < buckets.length; index++) {
            System.out.print("Bucket " + index + ":");
            for (Entry entry = buckets[index]; entry != null; entry = entry.next) {
                System.out.print(" -> " + entry.student.getStudentId());
            }
            System.out.println();
        }
    }

    private int bucketIndex(String studentId) {
        return Math.floorMod(studentId.trim().toLowerCase().hashCode(), buckets.length);
    }

    private static boolean sameId(String first, String second) {
        return first.trim().equalsIgnoreCase(second.trim());
    }

    private static boolean isValidId(String studentId) {
        return studentId != null && !studentId.trim().isEmpty();
    }
}