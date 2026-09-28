package com.university.app;

import com.university.datastructures.CampusGraph;
import com.university.management.StudentManager;
import com.university.management.StudentServiceManager;
import com.university.model.ServiceRequest;
import com.university.model.Student;

import java.util.Scanner;

/**
 * CIT300 Data Structures and Algorithms - Master Integration Application
 * Project: University Student Record and Campus Route Management System
 *
 * Integrated by Lead Developer combining:
 * - MEMBER 1: Student Entity + Custom Singly Linked List + Student Record CRUD
 * - MEMBER 2: Action History Stack (LIFO) + Service Request Queue (FIFO)
 * - MEMBER 3: Binary Search Tree (ID-Sorted View) + Separate Chaining Hash Table
 * - MEMBER 4: Campus Graph Representation (Adjacency List, BFS, DFS)
 *
 * Features full data structure synchronization across Linked List, BST, Hash Table, and Action Stack.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        StudentServiceManager serviceManager = new StudentServiceManager(studentManager);
        CampusGraph campusGraph = new CampusGraph();

        // Pre-populate initial sample records across all structures for seamless immediate demonstration
        studentManager.populateSampleData();
        campusGraph.populateSampleCampus();

        System.out.println("\n=================================================================");
        System.out.println("  Welcome to University Student Record & Campus Route System");
        System.out.println("            CIT300 Data Structures and Algorithms");
        System.out.println("=================================================================");
        System.out.println("  Default demo datasets loaded successfully:");
        System.out.println("  * 5 Student Records synchronized across Linked List, BST, Hash Table & Stack");
        System.out.println("  * 8 Campus Locations & 7 Undirected Roads loaded into Campus Graph\n");

        runMasterMenu(scanner, studentManager, serviceManager, campusGraph);
        scanner.close();
    }

    /**
     * Master interactive console menu loop supporting the exact 16 required options.
     *
     * @param scanner        Scanner instance for user input
     * @param studentManager Core manager controlling Linked List, BST, Hash Table, and Stack
     * @param serviceManager Service queue manager integrating FIFO queue and action history
     * @param campusGraph    Campus graph controller for locations, roads, BFS, and DFS
     */
    public static void runMasterMenu(Scanner scanner,
                                    StudentManager studentManager,
                                    StudentServiceManager serviceManager,
                                    CampusGraph campusGraph) {
        boolean exit = false;

        while (!exit) {
            printMasterMenuHeader();
            System.out.print("Enter your choice (1-16): ");
            String input = scanner.nextLine().trim();

            switch (input) {
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
                    handleDisplayAllLinkedList(studentManager);
                    break;
                case "5":
                    handleAddServiceRequest(scanner, serviceManager);
                    break;
                case "6":
                    handleProcessServiceRequest(serviceManager);
                    break;
                case "7":
                    handleDisplayRecentActions(studentManager);
                    break;
                case "8":
                    handleDisplayStudentsBST(studentManager);
                    break;
                case "9":
                    handleSearchStudentHashing(scanner, studentManager);
                    break;
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
                    handleDisplayCampusConnections(campusGraph);
                    break;
                case "15":
                    handleTraverseCampus(scanner, campusGraph);
                    break;
                case "16":
                    System.out.println("\n[Exit] Exiting University Student Record & Campus Route Management System. Goodbye!\n");
                    exit = true;
                    break;
                default:
                    System.out.println("\n[Warning] Invalid option selected! Please enter a number between 1 and 16.\n");
            }
        }
    }

    private static void printMasterMenuHeader() {
        System.out.println("=================================================================");
        System.out.println("  UNIVERSITY STUDENT RECORD AND CAMPUS ROUTE MANAGEMENT SYSTEM  ");
        System.out.println("                CIT300 Data Structures and Algorithms            ");
        System.out.println("=================================================================");
        System.out.println("   1. Add Student Record");
        System.out.println("   2. Update Student Record");
        System.out.println("   3. Delete Student Record");
        System.out.println("   4. Display All Records using Linked List");
        System.out.println("   5. Add Service Request to Queue");
        System.out.println("   6. Process Next Service Request");
        System.out.println("   7. Display Recent Actions using Stack");
        System.out.println("   8. Display Students using BST");
        System.out.println("   9. Search Student using Hashing");
        System.out.println("  10. Add Campus Location");
        System.out.println("  11. Remove Campus Location");
        System.out.println("  12. Add Campus Connection/Road");
        System.out.println("  13. Remove Campus Connection/Road");
        System.out.println("  14. Display Campus Connections");
        System.out.println("  15. Traverse Campus Locations using BFS/DFS");
        System.out.println("  16. Exit");
        System.out.println("=================================================================");
    }

    // =========================================================================
    // Handlers: Student Records (Members 1, 2 & 3)
    // =========================================================================

    /** Option 1: Add Student Record with synchronization across List, BST, Hash Table, Stack. */
    private static void handleAddStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Option 1: Add Student Record] ---");
        String id = promptNonEmpty(scanner, "Enter Student ID (e.g., S101): ");
        if (manager.studentExists(id)) {
            System.out.println("[Duplicate Error] Student ID '" + id + "' already exists in the system!\n");
            return;
        }
        String name = promptNonEmpty(scanner, "Enter Full Name: ");
        String prog = promptNonEmpty(scanner, "Enter Programme of Study: ");
        double marks = promptMarks(scanner);

        if (manager.addStudent(id, name, prog, marks)) {
            System.out.println("[Success] Student '" + name + "' (" + id + ") registered and synchronized across List, BST, Hash Table & Action Stack.\n");
        } else {
            System.out.println("[Failure] Could not add student record. Please verify input data.\n");
        }
    }

    /** Option 2: Update Student Record with synchronization across all views. */
    private static void handleUpdateStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Option 2: Update Student Record] ---");
        if (manager.getStudentList().isEmpty()) {
            System.out.println("[Error] Student record list is empty. No records available to update.\n");
            return;
        }
        String id = promptNonEmpty(scanner, "Enter Student ID to update: ");
        Student existing = manager.searchStudent(id);
        if (existing == null) {
            System.out.println("[Not Found Error] Student with ID '" + id + "' does not exist.\n");
            return;
        }
        System.out.println("Current Record: " + existing);
        String name = promptNonEmpty(scanner, "Enter New Full Name: ");
        String prog = promptNonEmpty(scanner, "Enter New Programme: ");
        double marks = promptMarks(scanner);

        if (manager.updateStudent(id, name, prog, marks)) {
            System.out.println("[Success] Record for ID '" + id + "' successfully updated across all structures.\n");
        } else {
            System.out.println("[Failure] Could not update student. Validation failed.\n");
        }
    }

    /** Option 3: Delete Student Record with synchronization across List, BST, Hash Table, Stack. */
    private static void handleDeleteStudent(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Option 3: Delete Student Record] ---");
        if (manager.getStudentList().isEmpty()) {
            System.out.println("[Error] Student record list is empty. No records available to delete.\n");
            return;
        }
        String id = promptNonEmpty(scanner, "Enter Student ID to delete: ");
        if (manager.deleteStudent(id)) {
            System.out.println("[Success] Student record '" + id + "' deleted from Linked List, BST, and Hash Table.\n");
        } else {
            System.out.println("[Not Found Error] Student ID '" + id + "' was not found in the system.\n");
        }
    }

    /** Option 4: Display All Records using Linked List. */
    private static void handleDisplayAllLinkedList(StudentManager manager) {
        System.out.println("\n--- [Option 4: Display All Records using Linked List] ---");
        if (manager.getStudentList().isEmpty()) {
            System.out.println("[Info] No student records available in the linked list.\n");
            return;
        }
        manager.displayStudents();
    }

    /** Option 5: Add Service Request to Queue. */
    private static void handleAddServiceRequest(Scanner scanner, StudentServiceManager serviceManager) {
        System.out.println("\n--- [Option 5: Add Service Request to Queue] ---");
        String requestId = promptNonEmpty(scanner, "Enter Request ID (e.g., R101): ");
        String studentId = promptNonEmpty(scanner, "Enter Student ID: ");
        String description = promptNonEmpty(scanner, "Enter Service Request Description: ");

        if (serviceManager.addServiceRequest(requestId, studentId, description)) {
            System.out.println("[Success] Service request '" + requestId.trim() + "' successfully enqueued (FIFO Queue).\n");
        } else {
            System.out.println("[Failure] Could not enqueue request: Duplicate Request ID, missing student record, or blank input.\n");
        }
    }

    /** Option 6: Process Next Service Request from Queue. */
    private static void handleProcessServiceRequest(StudentServiceManager serviceManager) {
        System.out.println("\n--- [Option 6: Process Next Service Request] ---");
        if (!serviceManager.hasPendingRequests()) {
            System.out.println("[Info] Service request queue is empty. No pending requests to process.\n");
            return;
        }
        ServiceRequest processed = serviceManager.processNextRequest();
        if (processed != null) {
            System.out.println("[Success] Processed Next Request (FIFO): " + processed);
            System.out.println("Remaining Pending Requests: " + serviceManager.getPendingRequestCount() + "\n");
        }
    }

    /** Option 7: Display Recent Actions using Stack. */
    private static void handleDisplayRecentActions(StudentManager manager) {
        System.out.println("\n--- [Option 7: Display Recent Actions using Stack (LIFO)] ---");
        if (manager.getActionStack().isEmpty()) {
            System.out.println("[Info] Action history stack is empty. No recent operations recorded.\n");
            return;
        }
        System.out.println("Total Recent Actions in Stack: " + manager.getActionStack().getSize());
        manager.getActionStack().display();
        System.out.println();
    }

    /** Option 8: Display Students using BST (sorted by Student ID). */
    private static void handleDisplayStudentsBST(StudentManager manager) {
        System.out.println("\n--- [Option 8: Display Students using BST (Inorder Traversal)] ---");
        if (manager.getStudentTree().isEmpty()) {
            System.out.println("[Info] The Binary Search Tree is empty; there are no students to display.\n");
            return;
        }
        System.out.println("Students ordered naturally by Student ID:");
        manager.displayStudentsInorder();
        System.out.println();
    }

    /** Option 9: Search Student using Hashing. */
    private static void handleSearchStudentHashing(Scanner scanner, StudentManager manager) {
        System.out.println("\n--- [Option 9: Search Student using Hashing (Separate Chaining)] ---");
        if (manager.getStudentHashTable().isEmpty()) {
            System.out.println("[Info] Hash Table is empty. No student records registered.\n");
            return;
        }
        String id = promptNonEmpty(scanner, "Enter Student ID to search: ");
        Student found = manager.searchStudentByHashing(id);
        if (found != null) {
            System.out.println("[Hash Search Result] Record Found: " + found + "\n");
        } else {
            System.out.println("[Not Found] Student ID '" + id + "' was not found in Hash Table.\n");
        }
    }

    // =========================================================================
    // Handlers: Campus Graph Route Management (Member 4)
    // =========================================================================

    /** Option 10: Add Campus Location. */
    private static void handleAddCampusLocation(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 10: Add Campus Location] ---");
        String name = promptNonEmpty(scanner, "Enter Campus Location Name (e.g., Science Complex): ");
        if (graph.addLocation(name)) {
            System.out.println("[Success] Location '" + name.trim() + "' added to campus graph.\n");
        }
    }

    /** Option 11: Remove Campus Location. */
    private static void handleRemoveCampusLocation(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 11: Remove Campus Location] ---");
        if (graph.isEmpty()) {
            System.out.println("[Error] Campus Graph is empty. No locations available to remove.\n");
            return;
        }
        String name = promptNonEmpty(scanner, "Enter Location Name to remove: ");
        if (graph.removeLocation(name)) {
            System.out.println("[Success] Location '" + name.trim() + "' and its associated roads removed.\n");
        }
    }

    /** Option 12: Add Campus Connection/Road. */
    private static void handleAddCampusConnection(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 12: Add Campus Connection/Road] ---");
        if (graph.getLocationCount() < 2) {
            System.out.println("[Error] At least 2 campus locations are required to create a road.\n");
            return;
        }
        String src = promptNonEmpty(scanner, "Enter First Location Name: ");
        String dest = promptNonEmpty(scanner, "Enter Second Location Name: ");
        if (graph.addConnection(src, dest)) {
            System.out.println("[Success] Road created between '" + src.trim() + "' and '" + dest.trim() + "'.\n");
        }
    }

    /** Option 13: Remove Campus Connection/Road. */
    private static void handleRemoveCampusConnection(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 13: Remove Campus Connection/Road] ---");
        if (graph.isEmpty() || graph.getEdgeCount() == 0) {
            System.out.println("[Error] No active road connections exist in the campus graph.\n");
            return;
        }
        String src = promptNonEmpty(scanner, "Enter First Location Name: ");
        String dest = promptNonEmpty(scanner, "Enter Second Location Name: ");
        if (graph.removeConnection(src, dest)) {
            System.out.println("[Success] Road between '" + src.trim() + "' and '" + dest.trim() + "' removed.\n");
        }
    }

    /** Option 14: Display Campus Connections. */
    private static void handleDisplayCampusConnections(CampusGraph graph) {
        System.out.println("\n--- [Option 14: Display Campus Connections] ---");
        if (graph.isEmpty()) {
            System.out.println("[Info] Campus Graph is currently empty. No locations or roads defined.\n");
            return;
        }
        graph.displayConnections();
    }

    /** Option 15: Traverse Campus Locations using BFS/DFS. */
    private static void handleTraverseCampus(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Option 15: Traverse Campus Locations using BFS/DFS] ---");
        if (graph.isEmpty()) {
            System.out.println("[Error] Cannot perform traversal: Campus Graph is empty.\n");
            return;
        }

        String startLoc = promptNonEmpty(scanner, "Enter Starting Campus Location for Traversal: ");
        if (!graph.locationExists(startLoc)) {
            System.out.println("[Error] Starting location '" + startLoc.trim() + "' does not exist in the campus graph.\n");
            return;
        }

        System.out.println("\nSelect Traversal Algorithm:");
        System.out.println("  1. Breadth-First Search (BFS) - Level-by-level shortest reach");
        System.out.println("  2. Depth-First Search (DFS) - Deep-branch exploration");
        System.out.println("  3. Run BOTH BFS and DFS (Comparison)");
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
        System.out.println();
    }

    // =========================================================================
    // Helper Input Methods with Strict Validation
    // =========================================================================

    private static String promptNonEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = scanner.nextLine().trim();
            if (!val.isEmpty()) {
                return val;
            }
            System.out.println("[Validation Error] Input cannot be blank or whitespace-only. Please try again.");
        }
    }

    private static double promptMarks(Scanner scanner) {
        while (true) {
            System.out.print("Enter Academic Marks (0.0 to 100.0): ");
            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (Student.isValidMarks(marks)) {
                    return marks;
                }
                System.out.println("[Validation Error] Marks must be between 0.0 and 100.0. Provided: " + marks);
            } catch (NumberFormatException e) {
                System.out.println("[Format Error] Invalid numeric input. Please enter a valid decimal number (e.g., 85.5).");
            }
        }
    }
}
