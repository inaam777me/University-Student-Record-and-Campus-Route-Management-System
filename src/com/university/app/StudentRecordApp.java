package com.university.app;

import com.university.management.StudentManager;
import com.university.management.StudentServiceManager;
import com.university.model.ServiceRequest;
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
        StudentServiceManager services = new StudentServiceManager(manager);

        while (!exit) {
            printMenuHeader();
<<<<<<< HEAD
            System.out.print("Enter your choice (1-12): ");
=======
            System.out.print("Enter your choice (1-10): ");
>>>>>>> c9deb9d83f67fb0780d7f34c31810618521bc8e1
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
                    manager.displayStudentsInorder();
                    break;
                case "9":
                    handleHashSearch(scanner, manager);
                    break;
                case "10":
                    System.out.println("\n[Exit] Exiting Student Record Management System. Goodbye!");
                    exit = true;
                    break;
                case "9":
                    handleAddServiceRequest(scanner, services);
                    break;
                case "10":
                    handleProcessServiceRequest(services);
                    break;
                case "11":
                    System.out.println("\n--- Pending Service Requests (Oldest First) ---");
                    services.displayPendingRequests();
                    System.out.println();
                    break;
                case "12":
                    System.out.println("\n--- Recent Actions (Newest First) ---");
                    manager.getActionStack().display();
                    System.out.println();
                    break;
                default:
<<<<<<< HEAD
                    System.out.println("\n[Warning] Invalid option selected. Please enter a number between 1 and 12.\n");
=======
                    System.out.println("\n[Warning] Invalid option selected. Please enter a number between 1 and 10.\n");
>>>>>>> c9deb9d83f67fb0780d7f34c31810618521bc8e1
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
<<<<<<< HEAD
        System.out.println("  8. Return / Exit");
        System.out.println("  9. Add Service Request");
        System.out.println(" 10. Process Next Service Request");
        System.out.println(" 11. Display Pending Service Requests");
        System.out.println(" 12. Display Recent Actions");
=======
        System.out.println("  8. Display Students using BST (Student ID order)");
        System.out.println("  9. Search Student using Hashing");
        System.out.println(" 10. Return / Exit");
>>>>>>> c9deb9d83f67fb0780d7f34c31810618521bc8e1
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

    private static void handleHashSearch(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Search Student using Hash Table] ---");
        String studentId = promptNonEmptyString(scanner, "Enter Student ID to search: ");
        Student student = manager.searchStudentByHashing(studentId);
        if (student == null) {
            System.out.println("[Result] No record found matching ID '" + studentId + "'.\n");
            return;
        }

        System.out.println("[Hash Search Result] " + student);
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

    private static void handleAddServiceRequest(Scanner scanner, StudentServiceManager services) {
        System.out.println("\n--- [Add Service Request] ---");
        String requestId = promptNonEmptyString(scanner, "Enter Request ID: ");
        String studentId = promptNonEmptyString(scanner, "Enter Student ID: ");
        String description = promptNonEmptyString(scanner, "Enter Request Description: ");
        if (services.addServiceRequest(requestId, studentId, description)) {
            System.out.println("[Success] Request added to the pending queue.\n");
        } else {
            System.out.println("[Failure] Invalid request, duplicate pending ID, or student not found.\n");
        }
    }

    private static void handleProcessServiceRequest(StudentServiceManager services) {
        ServiceRequest request = services.processNextRequest();
        if (request == null) {
            System.out.println("\n[Info] No pending service requests.\n");
            return;
        }
        System.out.println("\n[Success] Processed: " + request + "\n");
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
