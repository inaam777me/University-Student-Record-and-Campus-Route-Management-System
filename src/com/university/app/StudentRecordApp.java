package com.university.app;

import com.university.management.StudentManager;
import com.university.model.Student;

import java.util.Scanner;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 1 - Console-based Student Record Application
 *
 * This class provides an interactive, beginner-friendly console user interface.
 * It contains comprehensive input validation, error handling (e.g., non-numeric marks,
 * duplicate IDs, missing records), and displays clear formatted feedback.
 *
 * It can be run independently as Member 1's module or called directly by the master
 * group application through the public {@link #runStudentRecordMenu(Scanner, StudentManager)} method.
 */
public class StudentRecordApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        runStudentRecordMenu(scanner, manager);
        scanner.close();
    }

    /**
     * Reusable menu loop designed for seamless integration with the master project.
     *
     * @param scanner Shared Scanner instance for console reading
     * @param manager Shared or standalone StudentManager instance
     */
    public static void runStudentRecordMenu(Scanner scanner, StudentManager manager) {
        boolean exit = false;

        while (!exit) {
            printMenuHeader();
            System.out.print("Enter your choice (1-8): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    handleAddStudent(scanner, manager);
                    break;
                case "2":
                    handleUpdateStudent(scanner, manager);
                    break;
                case "3":
                    handleDeleteStudent(scanner, manager);
                    break;
                case "4":
                    handleSearchStudent(scanner, manager);
                    break;
                case "5":
                    handleDisplayAll(manager);
                    break;
                case "6":
                    handleCheckExistence(scanner, manager);
                    break;
                case "7":
                    handleLoadDemoData(manager);
                    break;
                case "8":
                    System.out.println("\n[Exit] Exiting Student Record Management System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("\n[Warning] Invalid option selected. Please enter a number between 1 and 8.\n");
            }
        }
    }

    private static void printMenuHeader() {
        System.out.println("=================================================================");
        System.out.println("  UNIVERSITY STUDENT RECORD AND CAMPUS ROUTE MANAGEMENT SYSTEM  ");
        System.out.println("  Module: Student Record Management (CIT300 - Member 1 Component) ");
        System.out.println("=================================================================");
        System.out.println("  1. Add New Student Record");
        System.out.println("  2. Update Existing Student Record");
        System.out.println("  3. Delete Student Record");
        System.out.println("  4. Search Student Record by ID");
        System.out.println("  5. Display All Registered Students");
        System.out.println("  6. Check if Student ID Exists");
        System.out.println("  7. Load Sample / Demo Records");
        System.out.println("  8. Return / Exit");
        System.out.println("=================================================================");
    }

    private static void handleAddStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Add New Student] ---");
        String studentId = promptNonEmptyString(scanner, "Enter Student ID (e.g., S101): ");

        if (manager.studentExists(studentId)) {
            System.out.println("[Error] A student with ID '" + studentId + "' already exists! Insertion aborted.\n");
            return;
        }

        String name = promptNonEmptyString(scanner, "Enter Full Name: ");
        String programme = promptNonEmptyString(scanner, "Enter Programme of Study: ");
        double marks = promptValidMarks(scanner);

        boolean success = manager.addStudent(studentId, name, programme, marks);
        if (success) {
            System.out.println("\n[Success] Student '" + name + "' (ID: " + studentId + ") was added successfully!\n");
        } else {
            System.out.println("\n[Failure] Unable to add student. Please check input constraints.\n");
        }
    }

    private static void handleUpdateStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Update Student Record] ---");
        String studentId = promptNonEmptyString(scanner, "Enter Student ID to update: ");

        Student existing = manager.searchStudent(studentId);
        if (existing == null) {
            System.out.println("[Error] Update failed: Student ID '" + studentId + "' was not found.\n");
            return;
        }

        System.out.println("Current details found: " + existing);
        String newName = promptNonEmptyString(scanner, "Enter Updated Full Name: ");
        String newProgramme = promptNonEmptyString(scanner, "Enter Updated Programme: ");
        double newMarks = promptValidMarks(scanner);

        boolean success = manager.updateStudent(studentId, newName, newProgramme, newMarks);
        if (success) {
            System.out.println("\n[Success] Student record for ID '" + studentId + "' updated successfully!\n");
        } else {
            System.out.println("\n[Failure] Update failed.\n");
        }
    }

    private static void handleDeleteStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Delete Student Record] ---");
        String studentId = promptNonEmptyString(scanner, "Enter Student ID to delete: ");

        boolean removed = manager.deleteStudent(studentId);
        if (removed) {
            System.out.println("\n[Success] Student record with ID '" + studentId + "' has been deleted.\n");
        } else {
            System.out.println("\n[Failure] Cannot delete. Student ID '" + studentId + "' does not exist.\n");
        }
    }

    private static void handleSearchStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Search Student Record] ---");
        String studentId = promptNonEmptyString(scanner, "Enter Student ID to search: ");

        Student student = manager.searchStudent(studentId);
        if (student != null) {
            System.out.println("\n--- Student Found ---");
            System.out.println("Student ID : " + student.getStudentId());
            System.out.println("Name       : " + student.getName());
            System.out.println("Programme  : " + student.getProgramme());
            System.out.printf("Marks      : %.2f\n", student.getMarks());
            System.out.println("Grade      : " + student.getGrade());
            System.out.println("---------------------\n");
        } else {
            System.out.println("[Result] No record found matching ID '" + studentId + "'.\n");
        }
    }

    private static void handleDisplayAll(StudentManager manager) {
        manager.displayStudents();
    }

    private static void handleCheckExistence(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Check Student ID Existence] ---");
        String studentId = promptNonEmptyString(scanner, "Enter Student ID to check: ");

        boolean exists = manager.studentExists(studentId);
        if (exists) {
            System.out.println("[Verified] Student ID '" + studentId + "' EXISTS in the system.\n");
        } else {
            System.out.println("[Verified] Student ID '" + studentId + "' DOES NOT exist in the system.\n");
        }
    }

    private static void handleLoadDemoData(StudentManager manager) {
        System.out.println("\nLoading 5 sample student records...");
        manager.populateSampleData();
        System.out.println("[Success] Demo data loaded successfully. Use Option 5 to view.\n");
    }

    // --- Helper Input Methods with Validation ---

    private static String promptNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("[Validation Error] Input cannot be blank. Please try again.");
        }
    }

    private static double promptValidMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter Marks (0.0 to 100.0): ");
            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (Student.isValidMarks(marks)) {
                    return marks;
                } else {
                    System.out.println("[Validation Error] Marks must be between 0.0 and 100.0. You entered: " + marks);
                }
            } catch (NumberFormatException e) {
                System.out.println("[Format Error] Invalid number format. Please enter a valid decimal number (e.g., 85.5).");
            }
        }
    }
}
