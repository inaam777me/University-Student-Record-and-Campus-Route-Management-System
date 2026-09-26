package com.university.datastructures;

import com.university.model.Student;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 1 - Custom Singly Linked List Implementation
 *
 * This class implements a custom Singly Linked List data structure from scratch.
 * Built-in collections (e.g., ArrayList, java.util.LinkedList) are intentionally avoided
 * to satisfy CIT300 requirements.
 *
 * Provides core operations:
 * - Insertion (addStudent) with duplicate check
 * - Search (searchStudent, studentExists)
 * - Updation (updateStudent)
 * - Deletion (deleteStudent)
 * - Traversal and Display (displayStudents, getAllStudents)
 */
public class StudentLinkedList {
    private StudentNode head;
    private int size;

    /**
     * Initializes an empty Student Linked List.
     */
    public StudentLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Checks whether the linked list contains any elements.
     *
     * @return true if the list has zero students, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the total count of students currently stored in the linked list.
     *
     * @return integer size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the head reference of the linked list.
     * Essential for external traversals, iterator patterns, or group integration.
     *
     * @return the head StudentNode
     */
    public StudentNode getHead() {
        return head;
    }

    /**
     * Checks if a student record with the specified ID already exists in the list.
     * Time Complexity: O(n)
     *
     * @param studentId The student ID to verify
     * @return true if a match is found, false otherwise
     */
    public boolean studentExists(String studentId) {
        if (studentId == null || studentId.trim().isEmpty() || isEmpty()) {
            return false;
        }

        String searchId = studentId.trim();
        StudentNode current = head;
        while (current != null) {
            if (current.getData().getStudentId().equalsIgnoreCase(searchId)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Adds a new student to the end (tail) of the linked list.
     * Verifies that the student object is valid and that no duplicate ID exists.
     * Time Complexity: O(n) due to duplicate check and tail traversal.
     *
     * @param student The Student object to add
     * @return true if added successfully, false if duplicate or invalid
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            System.err.println("[Error] Cannot add a null student record.");
            return false;
        }

        // Duplicate check to enforce primary key uniqueness
        if (studentExists(student.getStudentId())) {
            System.err.println("[Duplicate Error] Student with ID '" + student.getStudentId() + "' already exists.");
            return false;
        }

        StudentNode newNode = new StudentNode(student);

        // Case 1: List is currently empty
        if (head == null) {
            head = newNode;
        } else {
            // Case 2: Traverse to the last node and append
            StudentNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        size++;
        return true;
    }

    /**
     * Searches for a student record by unique Student ID.
     * Time Complexity: O(n)
     *
     * @param studentId The unique ID of the student to locate
     * @return Student object if found, or null if not found
     */
    public Student searchStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty() || isEmpty()) {
            return null;
        }

        String targetId = studentId.trim();
        StudentNode current = head;
        while (current != null) {
            if (current.getData().getStudentId().equalsIgnoreCase(targetId)) {
                return current.getData();
            }
            current = current.getNext();
        }

        return null; // Not found
    }

    /**
     * Updates an existing student's name, programme, and marks.
     * Performs validation on marks before applying updates.
     *
     * @param studentId    The ID of the student to update
     * @param newName      The updated student name
     * @param newProgramme The updated programme
     * @param newMarks     The updated marks (0.0 to 100.0)
     * @return true if updated successfully, false if student not found or invalid marks
     */
    public boolean updateStudent(String studentId, String newName, String newProgramme, double newMarks) {
        if (!Student.isValidMarks(newMarks)) {
            System.err.println("[Validation Error] Marks must be between 0.0 and 100.0.");
            return false;
        }

        Student student = searchStudent(studentId);
        if (student == null) {
            System.err.println("[Not Found Error] Student with ID '" + studentId + "' does not exist.");
            return false;
        }

        // Apply updates
        student.setName(newName);
        student.setProgramme(newProgramme);
        student.setMarks(newMarks);
        return true;
    }

    /**
     * Deletes a student record by Student ID from the linked list.
     * Handles head removal, middle removal, and tail removal gracefully.
     * Time Complexity: O(n)
     *
     * @param studentId The ID of the student to remove
     * @return true if deleted successfully, false if not found
     */
    public boolean deleteStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty() || isEmpty()) {
            return false;
        }

        String targetId = studentId.trim();

        // Case 1: The head node is the node to delete
        if (head.getData().getStudentId().equalsIgnoreCase(targetId)) {
            head = head.getNext();
            size--;
            return true;
        }

        // Case 2: Intermediate or tail node
        StudentNode previous = head;
        StudentNode current = head.getNext();

        while (current != null) {
            if (current.getData().getStudentId().equalsIgnoreCase(targetId)) {
                previous.setNext(current.getNext());
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }

        return false; // Student ID was not found in the list
    }

    /**
     * Displays all student records in a clean tabular console format.
     * Traverses the linked list from head to tail.
     */
    public void displayStudents() {
        if (isEmpty()) {
            System.out.println("\n[Info] No student records available in the system.");
            return;
        }

        String border = "+------------+---------------------------+--------------------------------+--------+-------+";
        System.out.println("\n" + border);
        System.out.printf("| %-10s | %-25s | %-30s | %-6s | %-5s |\n",
                "Student ID", "Full Name", "Programme", "Marks", "Grade");
        System.out.println(border);

        StudentNode current = head;
        while (current != null) {
            Student s = current.getData();
            System.out.printf("| %-10s | %-25s | %-30s | %6.2f | %-5s |\n",
                    s.getStudentId(),
                    s.getName(),
                    s.getProgramme(),
                    s.getMarks(),
                    s.getGrade());
            current = current.getNext();
        }
        System.out.println(border);
        System.out.println("Total Registered Students: " + size + "\n");
    }

    /**
     * Converts all student records in the linked list into a standard native array.
     * This method is an INTEGRATION BRIDGE for other team members:
     * - Member 2 (Stack/Queue) can push/enqueue all students
     * - Member 3 (BST/AVL) can populate tree nodes
     * - Member 4 (Hashing) can insert into hash tables
     *
     * @return Array of Student objects, or empty array if list is empty
     */
    public Student[] getAllStudents() {
        Student[] studentArray = new Student[size];
        StudentNode current = head;
        int index = 0;
        while (current != null) {
            studentArray[index++] = current.getData();
            current = current.getNext();
        }
        return studentArray;
    }

    /**
     * Clears all elements from the linked list.
     */
    public void clear() {
        head = null;
        size = 0;
    }
}
