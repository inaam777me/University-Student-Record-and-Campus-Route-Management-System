package com.university.management;

import com.university.datastructures.ActionStack;
import com.university.datastructures.StudentBinarySearchTree;
import com.university.datastructures.StudentHashTable;
import com.university.datastructures.StudentLinkedList;
import com.university.datastructures.StudentNode;
import com.university.model.Action;
import com.university.model.Student;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 1 - Student Record Management Service
 *
 * This class acts as the business logic / service controller for all student record
 * management operations. It encapsulates the underlying StudentLinkedList and exposes
 * high-level, validated methods for consumption by the console UI and peer modules
 * (Member 2's Stack/Queue, Member 3's BST/AVL, Member 4's Hashing).
 */
public class StudentManager {
    private final StudentLinkedList studentList;
    private final StudentBinarySearchTree studentTree;
    private final StudentHashTable studentHashTable;
    private final ActionStack actionStack;

    /**
     * Constructs a new StudentManager with its custom linked list, tree, hash table, and action stack.
     */
    public StudentManager() {
        this(new StudentLinkedList(), new ActionStack());
    }

    /**
     * Constructs a StudentManager that operates on an existing StudentLinkedList instance.
     * Useful when sharing a linked list across group modules.
     *
     * @param studentList Existing linked list instance
     */
    public StudentManager(StudentLinkedList studentList) {
        this(studentList, new ActionStack());
    }

    /** Constructs a manager using shared data structures supplied by the integrating application. */
    public StudentManager(StudentLinkedList studentList, ActionStack actionStack) {
        if (studentList == null) {
            throw new IllegalArgumentException("StudentLinkedList cannot be null.");
        }
        if (actionStack == null) {
            throw new IllegalArgumentException("ActionStack cannot be null.");
        }

        this.studentList = studentList;
        this.studentTree = new StudentBinarySearchTree();
        this.studentHashTable = new StudentHashTable();
        this.actionStack = actionStack;

        StudentNode current = studentList.getHead();
        while (current != null) {
            studentTree.insert(current.getData());
            studentHashTable.insert(current.getData());
            current = current.getNext();
        }
    }

    /**
     * Exposes the underlying linked list for peer group members to integrate with.
     *
     * @return the StudentLinkedList instance
     */
    public StudentLinkedList getStudentList() {
        return studentList;
    }

    public StudentBinarySearchTree getStudentTree() {
        return studentTree;
    }

    public StudentHashTable getStudentHashTable() {
        return studentHashTable;
    }

    /** Returns the recent-action stack shared with peer service modules. */
    public ActionStack getActionStack() {
        return actionStack;
    }

    /**
     * Validates and adds a new student record to the system.
     * Enforces non-empty strings, mark range [0, 100], and unique Student ID.
     *
     * @param studentId Unique student identifier
     * @param name      Full name of student
     * @param programme Programme of study
     * @param marks     Academic score (0.0 to 100.0)
     * @return true if student was successfully registered, false otherwise
     */
    public boolean addStudent(String studentId, String name, String programme, double marks) {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.err.println("[Error] Student ID cannot be empty.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.err.println("[Error] Student Name cannot be empty.");
            return false;
        }
        if (programme == null || programme.trim().isEmpty()) {
            System.err.println("[Error] Programme cannot be empty.");
            return false;
        }
        if (!Student.isValidMarks(marks)) {
            System.err.println("[Error] Invalid marks: " + marks + ". Must be between 0.0 and 100.0.");
            return false;
        }

        if (studentList.studentExists(studentId)) {
            System.err.println("[Error] Duplicate record: Student with ID '" + studentId.trim() + "' already exists.");
            return false;
        }

        Student student = new Student(studentId, name, programme, marks);
        boolean added = studentList.addStudent(student);
        if (added) {
            studentTree.insert(student);
            studentHashTable.insert(student);
            actionStack.push(new Action("STUDENT_ADDED", student.getStudentId(),
                    "Added student " + student.getName()));
        }
        return added;
    }

    /**
     * Overloaded method to add an existing Student object.
     *
     * @param student Pre-constructed Student object
     * @return true if added, false if duplicate or invalid
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            System.err.println("[Error] Student object cannot be null.");
            return false;
        }
        return addStudent(student.getStudentId(), student.getName(), student.getProgramme(), student.getMarks());
    }

    /**
     * Updates an existing student record with new details.
     *
     * @param studentId    ID of student to update
     * @param newName      Updated name
     * @param newProgramme Updated programme
     * @param newMarks     Updated marks (0.0 to 100.0)
     * @return true if update succeeded, false if validation failed or student not found
     */
    public boolean updateStudent(String studentId, String newName, String newProgramme, double newMarks) {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.err.println("[Error] Student ID cannot be empty.");
            return false;
        }
        if (newName == null || newName.trim().isEmpty()) {
            System.err.println("[Error] Updated Name cannot be empty.");
            return false;
        }
        if (newProgramme == null || newProgramme.trim().isEmpty()) {
            System.err.println("[Error] Updated Programme cannot be empty.");
            return false;
        }
        if (!Student.isValidMarks(newMarks)) {
            System.err.println("[Error] Invalid marks: " + newMarks + ". Must be between 0.0 and 100.0.");
            return false;
        }

        Student existing = studentList.searchStudent(studentId);
        if (existing == null) {
            System.err.println("[Not Found Error] Student with ID '" + studentId + "' does not exist.");
            return false;
        }

        boolean updated = studentList.updateStudent(studentId, newName.trim(), newProgramme.trim(), newMarks);
        if (updated) {
            actionStack.push(new Action("STUDENT_UPDATED", existing.getStudentId(),
                    "Updated student record for " + existing.getName()));
        }
        return updated;
    }

    /**
     * Deletes a student record by ID.
     *
     * @param studentId ID of student to remove
     * @return true if deleted, false if record was not found
     */
    public boolean deleteStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.err.println("[Error] Student ID cannot be empty.");
            return false;
        }

        Student existing = studentList.searchStudent(studentId);
        if (existing == null) {
            System.err.println("[Error] Cannot delete: Student with ID '" + studentId.trim() + "' does not exist.");
            return false;
        }

        boolean removed = studentList.deleteStudent(studentId);
        if (!removed) {
            System.err.println("[Error] Cannot delete: Student with ID '" + studentId.trim() + "' does not exist.");
            return false;
        }

        studentTree.delete(studentId);
        studentHashTable.delete(studentId);
        actionStack.push(new Action("STUDENT_DELETED", existing.getStudentId(),
                "Deleted student " + existing.getName()));
        return true;
    }

    /** Searches for a student by ID. */
    public Student searchStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.err.println("[Error] Search ID cannot be empty.");
            return null;
        }

        Student found = studentList.searchStudent(studentId);
        if (found == null) {
            System.out.println("[Search Result] No student found with ID: " + studentId.trim());
        }
        return found;
    }

    /** Searches the custom hash table for a student by ID. */
    public Student searchStudentByHashing(String studentId) {
        return studentHashTable.search(studentId);
    }

    /** Displays students in ascending Student ID order using the BST. */
    public void displayStudentsInorder() {
        studentTree.inorderTraversal();
    }

    /**
     * Checks if a student with the given ID exists in the system.
     *
     * @param studentId ID to check
     * @return true if exists, false otherwise
     */
    public boolean studentExists(String studentId) {
        return studentList.studentExists(studentId);
    }

    /**
     * Displays all student records in a clean tabular view.
     */
    public void displayStudents() {
        studentList.displayStudents();
    }

    /**
     * Returns total number of registered students.
     *
     * @return current count
     */
    public int getTotalStudents() {
        return studentList.getSize();
    }

    /**
     * Pre-loads demo data into the linked list for quick testing and peer verification.
     */
    public void populateSampleData() {
        addStudent("S101", "Alice Johnson", "BSc Computer Science", 88.50);
        addStudent("S102", "Bob Smith", "BEng Software Engineering", 74.00);
        addStudent("S103", "Charlie Davis", "BSc Information Technology", 92.00);
        addStudent("S104", "Diana Prince", "BSc Data Science", 65.50);
        addStudent("S105", "Evan Wright", "BSc Computer Science", 48.00);
    }
}

