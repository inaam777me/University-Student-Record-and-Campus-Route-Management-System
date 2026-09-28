package graph;

import java.util.Scanner;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 4 - Standalone Campus Graph Component Test Entry Point
 *
 * GraphMain provides an isolated testing interface for the CampusGraph component.
 * Features an interactive menu system and automated unit tests.
 */
public class GraphMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CampusGraph graph = new CampusGraph();

        // Load sample campus network by default
        graph.populateSampleCampus();

        System.out.println("==========================================================================");
        System.out.println(" CIT300 DSA MEMBER 4 — CAMPUS GRAPH & ROUTE MANAGEMENT SYSTEM ");
        System.out.println("==========================================================================");
        System.out.println("Default sample campus network loaded (8 locations, 7 roads).\n");

        runGraphMenu(scanner, graph);
        scanner.close();
    }

    public static void runGraphMenu(Scanner scanner, CampusGraph graph) {
        boolean exit = false;

        while (!exit) {
            printMenu();
            System.out.print("Enter choice (1-10): ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    handleAddLocation(scanner, graph);
                    break;
                case "2":
                    handleRemoveLocation(scanner, graph);
                    break;
                case "3":
                    handleAddConnection(scanner, graph);
                    break;
                case "4":
                    handleRemoveConnection(scanner, graph);
                    break;
                case "5":
                    graph.displayConnections();
                    break;
                case "6":
                    handleBFS(scanner, graph);
                    break;
                case "7":
                    handleDFS(scanner, graph);
                    break;
                case "8":
                    graph.populateSampleCampus();
                    System.out.println("\n[Success] Sample campus network reloaded (8 locations, 7 roads).\n");
                    break;
                case "9":
                    runAutomatedTests();
                    break;
                case "10":
                    System.out.println("\n[Exit] Exiting Campus Graph Module. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("\n[Warning] Invalid option! Please enter a number between 1 and 10.\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("==========================================================================");
        System.out.println("                     CAMPUS GRAPH MANAGEMENT MENU                         ");
        System.out.println("==========================================================================");
        System.out.println("  1. Add Campus Location (Vertex)");
        System.out.println("  2. Remove Campus Location (Vertex)");
        System.out.println("  3. Add Campus Connection / Road (Edge)");
        System.out.println("  4. Remove Campus Connection / Road (Edge)");
        System.out.println("  5. Display Campus Network (Adjacency List)");
        System.out.println("  6. Traverse Campus using BFS (Breadth-First Search)");
        System.out.println("  7. Traverse Campus using DFS (Depth-First Search)");
        System.out.println("  8. Load / Reset Sample Campus Network");
        System.out.println("  9. Run Automated Test Suite");
        System.out.println(" 10. Exit");
        System.out.println("==========================================================================");
    }

    private static void handleAddLocation(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Add Campus Location] ---");
        String name = promptNonEmpty(scanner, "Enter Location Name: ");
        if (graph.addLocation(name)) {
            System.out.println("[Success] Location '" + name.trim() + "' added.\n");
        }
    }

    private static void handleRemoveLocation(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Remove Campus Location] ---");
        if (graph.isEmpty()) {
            System.out.println("[Error] Campus Graph is empty.\n");
            return;
        }
        String name = promptNonEmpty(scanner, "Enter Location Name to Remove: ");
        if (graph.removeLocation(name)) {
            System.out.println("[Success] Location '" + name.trim() + "' and all connected roads removed.\n");
        }
    }

    private static void handleAddConnection(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Add Campus Connection/Road] ---");
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

    private static void handleRemoveConnection(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Remove Campus Connection/Road] ---");
        if (graph.isEmpty() || graph.getEdgeCount() == 0) {
            System.out.println("[Error] No active road connections exist.\n");
            return;
        }
        String src = promptNonEmpty(scanner, "Enter First Location Name: ");
        String dest = promptNonEmpty(scanner, "Enter Second Location Name: ");
        if (graph.removeConnection(src, dest)) {
            System.out.println("[Success] Road between '" + src.trim() + "' and '" + dest.trim() + "' removed.\n");
        }
    }

    private static void handleBFS(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Breadth-First Search (BFS)] ---");
        if (graph.isEmpty()) {
            System.out.println("[Error] Campus Graph is empty.\n");
            return;
        }
        String startLoc = promptNonEmpty(scanner, "Enter Starting Location for BFS: ");
        graph.bfs(startLoc);
    }

    private static void handleDFS(Scanner scanner, CampusGraph graph) {
        System.out.println("\n--- [Depth-First Search (DFS)] ---");
        if (graph.isEmpty()) {
            System.out.println("[Error] Campus Graph is empty.\n");
            return;
        }
        String startLoc = promptNonEmpty(scanner, "Enter Starting Location for DFS: ");
        graph.dfs(startLoc);
    }

    private static String promptNonEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("[Validation Error] Input cannot be blank. Please try again.");
        }
    }

    // --- Automated Unit Tests ---

    public static void runAutomatedTests() {
        System.out.println("\n==========================================================================");
        System.out.println("          RUNNING STANDALONE CAMPUS GRAPH AUTOMATED TESTS               ");
        System.out.println("==========================================================================");

        int total = 0;
        int passed = 0;

        CampusGraph graph = new CampusGraph();

        // 1. Initial State
        total++;
        if (graph.isEmpty() && graph.getLocationCount() == 0 && graph.getEdgeCount() == 0) {
            passed++;
            System.out.println("  [PASS] Test 1: Initial empty graph state");
        } else {
            System.err.println("  [FAIL] Test 1: Initial empty graph state");
        }

        // 2. Add Locations & Duplicate Checks
        graph.addLocation("Library");
        graph.addLocation("Main Hall");
        total++;
        if (graph.getLocationCount() == 2 && graph.locationExists("Library")) {
            passed++;
            System.out.println("  [PASS] Test 2: Add locations and existence check");
        } else {
            System.err.println("  [FAIL] Test 2: Add locations");
        }

        total++;
        if (!graph.addLocation("Library")) {
            passed++;
            System.out.println("  [PASS] Test 3: Duplicate location addition rejected");
        } else {
            System.err.println("  [FAIL] Test 3: Duplicate location addition");
        }

        // 3. Add Connections & Edge Constraints
        graph.addConnection("Library", "Main Hall");
        total++;
        if (graph.getEdgeCount() == 1 && graph.hasConnection("Library", "Main Hall") && graph.hasConnection("Main Hall", "Library")) {
            passed++;
            System.out.println("  [PASS] Test 4: Add connection (Undirected check)");
        } else {
            System.err.println("  [FAIL] Test 4: Add connection");
        }

        total++;
        if (!graph.addConnection("Library", "Main Hall")) {
            passed++;
            System.out.println("  [PASS] Test 5: Duplicate connection rejected");
        } else {
            System.err.println("  [FAIL] Test 5: Duplicate connection");
        }

        total++;
        if (!graph.addConnection("Library", "Library")) {
            passed++;
            System.out.println("  [PASS] Test 6: Self-loop connection rejected");
        } else {
            System.err.println("  [FAIL] Test 6: Self-loop connection");
        }

        // 4. Sample Campus Traversals
        graph.populateSampleCampus();
        total++;
        if (graph.getLocationCount() == 8 && graph.getEdgeCount() == 7) {
            passed++;
            System.out.println("  [PASS] Test 7: Sample campus population (8 locations, 7 edges)");
        } else {
            System.err.println("  [FAIL] Test 7: Sample campus population");
        }

        total++;
        String[] bfsRes = graph.bfs("Library");
        if (bfsRes.length == 8 && bfsRes[0].equalsIgnoreCase("Library")) {
            passed++;
            System.out.println("  [PASS] Test 8: BFS traversal starting from Library");
        } else {
            System.err.println("  [FAIL] Test 8: BFS traversal");
        }

        total++;
        String[] dfsRes = graph.dfs("Library");
        if (dfsRes.length == 8 && dfsRes[0].equalsIgnoreCase("Library")) {
            passed++;
            System.out.println("  [PASS] Test 9: DFS traversal starting from Library");
        } else {
            System.err.println("  [FAIL] Test 9: DFS traversal");
        }

        total++;
        String[] invalidStart = graph.bfs("NonExistentLocation");
        if (invalidStart.length == 0) {
            passed++;
            System.out.println("  [PASS] Test 10: Invalid start location traversal rejected");
        } else {
            System.err.println("  [FAIL] Test 10: Invalid start location traversal");
        }

        System.out.println("==========================================================================");
        System.out.println(" STANDALONE TEST SUMMARY: " + passed + " / " + total + " PASSED");
        System.out.println("==========================================================================\n");
    }
}
