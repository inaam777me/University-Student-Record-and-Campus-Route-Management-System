package com.university.test;

import com.university.datastructures.CampusGraph;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 4 - Campus Graph Automated Test Suite
 *
 * This class provides automated unit testing for the CampusGraph component.
 * It verifies all required graph operations, input validation, edge cases,
 * error handling, and traversal results.
 */
public class CampusGraphTest {

    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("     RUNNING CIT300 MEMBER 4: CAMPUS GRAPH AUTOMATED TEST SUITE          ");
        System.out.println("==========================================================================");

        testEmptyGraphState();
        testAddLocationAndDuplicates();
        testRemoveLocationAndMissing();
        testAddConnectionAndValidation();
        testRemoveConnectionAndMissing();
        testSampleCampusNetwork();
        testBFSTraversal();
        testDFSTraversal();
        testInvalidStartTraversal();

        System.out.println("==========================================================================");
        System.out.println(" TEST SUMMARY: " + passedTests + " / " + totalTests + " TESTS PASSED");
        if (passedTests == totalTests) {
            System.out.println(" RESULT: ALL CAMPUS GRAPH TESTS PASSED SUCCESSFULLY! [SUCCESS]");
        } else {
            System.out.println(" RESULT: SOME TESTS FAILED. PLEASE REVIEW LOGS. [FAILURE]");
        }
        System.out.println("==========================================================================\n");
    }

    private static void assertTrue(String testName, boolean condition) {
        totalTests++;
        if (condition) {
            passedTests++;
            System.out.println("  [PASS] " + testName);
        } else {
            System.err.println("  [FAIL] " + testName);
        }
    }

    private static void assertEquals(String testName, int expected, int actual) {
        totalTests++;
        if (expected == actual) {
            passedTests++;
            System.out.println("  [PASS] " + testName + " (Expected: " + expected + ", Actual: " + actual + ")");
        } else {
            System.err.println("  [FAIL] " + testName + " (Expected: " + expected + ", Actual: " + actual + ")");
        }
    }

    private static void testEmptyGraphState() {
        System.out.println("\n--- Test 1: Empty Graph State & Guard Checks ---");
        CampusGraph graph = new CampusGraph();
        assertTrue("Graph should be initially empty", graph.isEmpty());
        assertEquals("Initial location count should be 0", 0, graph.getLocationCount());
        assertEquals("Initial edge count should be 0", 0, graph.getEdgeCount());
        assertTrue("locationExists should return false for empty graph", !graph.locationExists("Library"));
        assertTrue("removeLocation on empty graph should return false", !graph.removeLocation("Library"));
        assertTrue("removeConnection on empty graph should return false", !graph.removeConnection("Library", "Main Hall"));
    }

    private static void testAddLocationAndDuplicates() {
        System.out.println("\n--- Test 2: Add Location & Duplicate Validation ---");
        CampusGraph graph = new CampusGraph();

        assertTrue("Add location 'Library'", graph.addLocation("Library"));
        assertTrue("Add location 'Main Hall'", graph.addLocation("Main Hall"));
        assertEquals("Location count should be 2", 2, graph.getLocationCount());

        assertTrue("Library exists", graph.locationExists("Library"));
        assertTrue("Case-insensitive exist check 'library'", graph.locationExists("library"));
        assertTrue("Main Hall exists", graph.locationExists("Main Hall"));

        // Duplicate checks
        assertTrue("Duplicate add 'Library' should return false", !graph.addLocation("Library"));
        assertTrue("Duplicate add case-insensitive 'LIBRARY' should return false", !graph.addLocation("LIBRARY"));
        assertEquals("Location count remains 2 after duplicate attempts", 2, graph.getLocationCount());

        // Blank/Null checks
        assertTrue("Null location add returns false", !graph.addLocation(null));
        assertTrue("Blank location add returns false", !graph.addLocation("   "));
    }

    private static void testRemoveLocationAndMissing() {
        System.out.println("\n--- Test 3: Remove Location & Incident Edge Cleanup ---");
        CampusGraph graph = new CampusGraph();

        graph.addLocation("Library");
        graph.addLocation("Main Hall");
        graph.addLocation("Canteen");
        graph.addConnection("Library", "Main Hall");
        graph.addConnection("Library", "Canteen");

        assertEquals("Edge count should be 2", 2, graph.getEdgeCount());

        // Remove non-existent location
        assertTrue("Removing non-existent location 'Gym' returns false", !graph.removeLocation("Gym"));

        // Remove 'Main Hall'
        assertTrue("Removing existing location 'Main Hall'", graph.removeLocation("Main Hall"));
        assertEquals("Location count becomes 2", 2, graph.getLocationCount());
        assertEquals("Edge count decreases to 1", 1, graph.getEdgeCount());
        assertTrue("Main Hall no longer exists", !graph.locationExists("Main Hall"));
        assertTrue("Library no longer has connection to Main Hall", !graph.hasConnection("Library", "Main Hall"));
        assertTrue("Library still connected to Canteen", graph.hasConnection("Library", "Canteen"));
    }

    private static void testAddConnectionAndValidation() {
        System.out.println("\n--- Test 4: Add Connection & Validation Constraints ---");
        CampusGraph graph = new CampusGraph();

        graph.addLocation("Library");
        graph.addLocation("Main Hall");

        // Normal addition
        assertTrue("Add road Library - Main Hall", graph.addConnection("Library", "Main Hall"));
        assertEquals("Edge count is 1", 1, graph.getEdgeCount());
        assertTrue("Connection exists Library -> Main Hall", graph.hasConnection("Library", "Main Hall"));
        assertTrue("Connection exists Main Hall -> Library (Undirected)", graph.hasConnection("Main Hall", "Library"));

        // Duplicate connection
        assertTrue("Duplicate connection Library - Main Hall returns false", !graph.addConnection("Library", "Main Hall"));

        // Self-loop prevention
        assertTrue("Self-loop connection Library - Library returns false", !graph.addConnection("Library", "Library"));

        // Connecting non-existent locations
        assertTrue("Connecting to non-existent location returns false", !graph.addConnection("Library", "NonExistentLocation"));
        assertTrue("Connecting from non-existent location returns false", !graph.addConnection("NonExistentLocation", "Main Hall"));
    }

    private static void testRemoveConnectionAndMissing() {
        System.out.println("\n--- Test 5: Remove Connection & Missing Road Handling ---");
        CampusGraph graph = new CampusGraph();

        graph.addLocation("Library");
        graph.addLocation("Main Hall");
        graph.addLocation("Canteen");
        graph.addConnection("Library", "Main Hall");

        assertTrue("Remove active connection Library - Main Hall", graph.removeConnection("Library", "Main Hall"));
        assertEquals("Edge count is 0 after removal", 0, graph.getEdgeCount());
        assertTrue("Connection Library - Main Hall is removed", !graph.hasConnection("Library", "Main Hall"));

        // Remove non-existent connection
        assertTrue("Removing non-existent connection returns false", !graph.removeConnection("Library", "Canteen"));
    }

    private static void testSampleCampusNetwork() {
        System.out.println("\n--- Test 6: Sample Campus Network Population ---");
        CampusGraph graph = new CampusGraph();
        graph.populateSampleCampus();

        assertEquals("Sample campus should have 8 locations", 8, graph.getLocationCount());
        assertEquals("Sample campus should have 7 connections", 7, graph.getEdgeCount());

        assertTrue("Library exists", graph.locationExists("Library"));
        assertTrue("Administration exists", graph.locationExists("Administration"));
        assertTrue("Road Library - Main Hall exists", graph.hasConnection("Library", "Main Hall"));
        assertTrue("Road Student Centre - Administration exists", graph.hasConnection("Student Centre", "Administration"));
    }

    private static void testBFSTraversal() {
        System.out.println("\n--- Test 7: BFS Traversal Execution ---");
        CampusGraph graph = new CampusGraph();
        graph.populateSampleCampus();

        String[] bfsResult = graph.bfs("Library");
        assertEquals("BFS starting from Library visits all 8 connected locations", 8, bfsResult.length);
        assertTrue("First location visited in BFS is Library", bfsResult[0].equalsIgnoreCase("Library"));
    }

    private static void testDFSTraversal() {
        System.out.println("\n--- Test 8: DFS Traversal Execution ---");
        CampusGraph graph = new CampusGraph();
        graph.populateSampleCampus();

        String[] dfsResult = graph.dfs("Library");
        assertEquals("DFS starting from Library visits all 8 connected locations", 8, dfsResult.length);
        assertTrue("First location visited in DFS is Library", dfsResult[0].equalsIgnoreCase("Library"));
    }

    private static void testInvalidStartTraversal() {
        System.out.println("\n--- Test 9: Invalid Traversal Start Nodes ---");
        CampusGraph graph = new CampusGraph();
        graph.populateSampleCampus();

        String[] bfsInvalid = graph.bfs("InvalidStartNode");
        assertEquals("BFS on non-existent node returns empty array", 0, bfsInvalid.length);

        String[] dfsInvalid = graph.dfs("InvalidStartNode");
        assertEquals("DFS on non-existent node returns empty array", 0, dfsInvalid.length);

        CampusGraph emptyGraph = new CampusGraph();
        String[] emptyBfs = emptyGraph.bfs("Library");
        assertEquals("BFS on empty graph returns empty array", 0, emptyBfs.length);
    }
}
