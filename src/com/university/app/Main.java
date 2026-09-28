package com.university.app;

import com.university.datastructures.CampusGraph;
import com.university.management.StudentManager;
import com.university.management.StudentServiceManager;
import com.university.model.ServiceRequest;
import com.university.model.Student;

import java.util.Scanner;

/**
 * CIT300 Data Structures and Algorithms - Master Group Application
 * Project: University Student Record and Campus Route Management System
 *
 * This master entry point integrates all project components:
 * - Member 1: Singly Linked List (Student Records CRUD)
 * - Member 2: Action History Stack & Service Request Queue
 * - Member 3: Binary Search Tree (ID-sorted view) & Separate Chaining Hash Table
 * - Member 4: Campus Graph Representation (Adjacency List, BFS, DFS)
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        StudentServiceManager serviceManager = new StudentServiceManager(studentManager);
        CampusGraph campusGraph = new CampusGraph();

        // Populate initial sample graph network for seamless testing
        campusGraph.populateSampleCampus();

        System.out.println("\nWelcome to the University Student Record & Campus Route Management System!");
        System.out.println("Default sample campus network loaded (8 locations, 7 roads).\n");

        runMasterMenu(scanner, studentManager, serviceManager, campusGraph);
        scanner.close();
    }

    /**
     * Master interactive console menu loop.
     *
     * @param scanner        Scanner instance
     * @param studentManager StudentManager instance
     * @param serviceManager StudentServiceManager instance
     * @param campusGraph    CampusGraph instance
     */
    public static void runMasterMenu(Scanner scanner,
                                    StudentManager studentManager,
                                    StudentServiceManager serviceManager,
                                    CampusGraph campusGraph) {
        boolean exit = false;

        while (!exit) {
            printMasterMenuHeader();
            System.out.print("Enter your choice (1-17): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                // --- Student Record Management (Options 1-9) ---
                case "1":
                    handleAddStudent(scanner, studentManager);
                    break;
                case "2":
                    handleUpdateStudent(scanner, studentManager);
                    break;
                case "3":
                    handleDeleteStudent(scanner, studentManager);
                    break;
                case "4":
                    handleSearchStudent(scanner, studentManager);
                    break;
                case "5":
                    studentManager.displayStudents();
                    break;
                case "6":
                    handleCheckStudentExistence(scanner, studentManager);
                    break;
                case "7":
                    studentManager.populateSampleData();
                    System.out.println("\n[Success] Sample student records loaded successfully.\n");
                    break;
                case "8":
                    studentManager.displayStudentsInorder();
                    break;
                case "9":
                    handleHashSearch(scanner, studentManager);
                    break;

                // --- Campus Graph Route Management (Options 10-16) ---
                case "10":
                    handleAddCampusLocation(scanner, campusGraph);
                    break;
                case "11":
                    handleRemoveCampusLocation(scanner, campusGraph);
                    break;
                case "12":
                    handleAddCampusConnection(scanner, campusGraph);
                    break;
                case "13":
                    handleRemoveCampusConnection(scanner, campusGraph);
                    break;
                case "14":
                    campusGraph.displayConnections();
                    break;
                case "15":
                    handleTraverseCampus(scanner, campusGraph);
                    break;
                case "16":
                    campusGraph.populateSampleCampus();
                    System.out.println("\n[Success] Sample campus network loaded (Library, Main Hall, Canteen, etc.).\n");
                    break;
                case "17":
                    System.out.println("\n[Exit] Exiting University Management System. Thank you!");
                    exit = true;
                    break;
                default:
                    System.out.println("\n[Warning] Invalid option! Please enter a number between 1 and 17.\n");
            }
        }
    }

    private static void printMasterMenuHeader() {
        System.out.println("=================================================================");
        System.out.println("  UNIVERSITY STUDENT RECORD AND CAMPUS ROUTE MANAGEMENT SYSTEM  ");
        System.out.println("                CIT300 Data Structures and Algorithms            ");
        System.out.println("=================================================================");
        System.out.println("  --- STUDENT RECORD MANAGEMENT (Members 1, 2 & 3) ---");
        System.out.println("   1. Add New Student Record");
        System.out.println("   2. Update Existing Student Record");
        System.out.println("   3. Delete Student Record");
        System.out.println("   4. Search Student Record by ID");
        System.out.println("   5. Display All Registered Students");
        System.out.println("   6. Check Student Existence");
        System.out.println("   7. Load Sample Student Records");
        System.out.println("   8. Display Students using BST (Sorted Student ID Order)");
        System.out.println("   9. Search Student using Hash Table");
        System.out.println("  ---------------------------------------------------------------");
        System.out.println("  --- CAMPUS ROUTE MANAGEMENT (Member 4 - Campus Graph) ---");
        System.out.println("  10. Add Campus Location");
        System.out.println("  11. Remove Campus Location");
        System.out.println("  12. Add Campus Connection/Road");
        System.out.println("  13. Remove Campus Connection/Road");
        System.out.println("  14. Display Campus Connections");
        System.out.println("  15. Traverse Campus Locations using BFS/DFS");
        System.out.println("  16. Reset / Load Sample Campus Network");
        System.out.println("  17. Exit Application");
        System.out.println("=================================================================");
    }

    // --- Student Helper Handlers ---

    private static void handleAddStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Add New Student] ---");
        String id = promptNonEmpty(scanner, "Enter Student ID (e.g., S101): ");
        if (manager.studentExists(id)) {
            System.out.println("[Error] Student ID '" + id + "' already exists!\n");
            return;
        }
        String name = promptNonEmpty(scanner, "Enter Full Name: ");
        String prog = promptNonEmpty(scanner, "Enter Programme of Study: ");
        double marks = promptMarks(scanner);
        if (manager.addStudent(id, name, prog, marks)) {
            System.out.println("[Success] Added student: " + name + " (" + id + ")\n");
        }
    }

    private static void handleUpdateStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Update Student Record] ---");
        String id = promptNonEmpty(scanner, "Enter Student ID to update: ");
        Student s = manager.searchStudent(id);
        if (s == null) {
            System.out.println("[Error] Student ID '" + id + "' not found.\n");
            return;
        }
        String name = promptNonEmpty(scanner, "Enter New Full Name: ");
        String prog = promptNonEmpty(scanner, "Enter New Programme: ");
        double marks = promptMarks(scanner);
        if (manager.updateStudent(id, name, prog, marks)) {
            System.out.println("[Success] Record updated for ID " + id + "\n");
        }
    }

    private static void handleDeleteStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Delete Student Record] ---");
        String id = promptNonEmpty(scanner, "Enter Student ID to delete: ");
        if (manager.deleteStudent(id)) {
            System.out.println("[Success] Student record '" + id + "' deleted.\n");
        } else {
            System.out.println("[Error] Student ID '" + id + "' not found.\n");
        }
    }

    private static void handleSearchStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Search Student Record] ---");
        String id = promptNonEmpty(scanner, "Enter Student ID: ");
        Student s = manager.searchStudent(id);
        if (s != null) {
            System.out.println("\n[Found] " + s + "\n");
        } else {
            System.out.println("[Not Found] Student ID '" + id + "' does not exist.\n");
        }
    }

    private static void handleCheckStudentExistence(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Check Student ID Existence] ---");
        String id = promptNonEmpty(scanner, "Enter Student ID: ");
        if (manager.studentExists(id)) {
            System.out.println("[Verified] Student ID '" + id + "' EXISTS.\n");
        } else {
            System.out.println("[Verified] Student ID '" + id + "' DOES NOT exist.\n");
        }
    }

    private static void handleHashSearch(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Search Student via Hash Table] ---");
        String id = promptNonEmpty(scanner, "Enter Student ID: ");
        Student s = manager.searchStudentByHashing(id);
        if (s != null) {
            System.out.println("[Hash Search Result] " + s + "\n");
        } else {
            System.out.println("[Not Found] Student ID '" + id + "' was not found in Hash Table.\n");
        }
    }

    // --- Campus Graph Helper Handlers (Member 4) ---

    private static void handleAddCampusLocation(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 10: Add Campus Location] ---");
        String name = promptNonEmpty(scanner, "Enter Location Name (e.g., Science Building): ");
        if (graph.addLocation(name)) {
            System.out.println("[Success] Campus location '" + name.trim() + "' added to the graph.\n");
        }
    }

    private static void handleRemoveCampusLocation(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 11: Remove Campus Location] ---");
        if (graph.isEmpty()) {
            System.out.println("[Error] Campus Graph is empty. No location to remove.\n");
            return;
        }
        String name = promptNonEmpty(scanner, "Enter Location Name to remove: ");
        if (graph.removeLocation(name)) {
            System.out.println("[Success] Location '" + name.trim() + "' and all connected roads removed.\n");
        }
    }

    private static void handleAddCampusConnection(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 12: Add Campus Connection/Road] ---");
        if (graph.getLocationCount() < 2) {
            System.out.println("[Error] At least 2 campus locations are required to create a connection.\n");
            return;
        }
        String src = promptNonEmpty(scanner, "Enter First Location Name: ");
        String dest = promptNonEmpty(scanner, "Enter Second Location Name: ");
        if (graph.addConnection(src, dest)) {
            System.out.println("[Success] Road added between '" + src.trim() + "' and '" + dest.trim() + "'.\n");
        }
    }

    private static void handleRemoveCampusConnection(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 13: Remove Campus Connection/Road] ---");
        if (graph.isEmpty() || graph.getEdgeCount() == 0) {
            System.out.println("[Error] No active road connections exist to remove.\n");
            return;
        }
        String src = promptNonEmpty(scanner, "Enter First Location Name: ");
        String dest = promptNonEmpty(scanner, "Enter Second Location Name: ");
        if (graph.removeConnection(src, dest)) {
            System.out.println("[Success] Road between '" + src.trim() + "' and '" + dest.trim() + "' removed.\n");
        }
    }

    private static void handleTraverseCampus(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 15: Traverse Campus Locations (BFS/DFS)] ---");
        if (graph.isEmpty()) {
            System.out.println("[Error] Cannot perform traversal: Campus Graph is empty.\n");
            return;
        }

        String startLoc = promptNonEmpty(scanner, "Enter Starting Campus Location for Traversal: ");
        if (!graph.locationExists(startLoc)) {
            System.out.println("[Error] Starting location '" + startLoc.trim() + "' does not exist in graph.\n");
            return;
        }

        System.out.println("\nSelect Traversal Algorithm:");
        System.out.println("  1. Breadth-First Search (BFS)");
        System.out.println("  2. Depth-First Search (DFS)");
        System.out.println("  3. Run BOTH BFS and DFS");
        System.out.print("Choice (1-3): ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                graph.bfs(startLoc);
                break;
            case "2":
                graph.dfs(startLoc);
                break;
            case "3":
                System.out.println("\n=== COMPARATIVE GRAPH TRAVERSALS ===");
                graph.bfs(startLoc);
                graph.dfs(startLoc);
                break;
            default:
                System.out.println("[Warning] Invalid selection. Defaulting to BFS.");
                graph.bfs(startLoc);
        }
    }

    // --- Input Validation Helpers ---

    private static String promptNonEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = scanner.nextLine().trim();
            if (!val.isEmpty()) {
                return val;
            }
            System.out.println("[Validation Error] Input cannot be empty. Please try again.");
        }
    }

    private static double promptMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter Marks (0.0 to 100.0): ");
            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (Student.isValidMarks(marks)) {
                    return marks;
                }
                System.out.println("[Validation Error] Marks must be between 0.0 and 100.0.");
            } catch (NumberFormatException e) {
                System.out.println("[Format Error] Please enter a valid decimal number (e.g., 85.0).");
            }
        }
    }
}
