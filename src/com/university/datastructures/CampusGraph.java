package com.university.datastructures;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 4 - Campus Graph Component
 *
 * This class implements a custom Graph data structure representing a University Campus
 * using an Adjacency List.
 *
 * Vertices = Campus locations (e.g., Library, Main Hall, Computer Lab)
 * Edges    = Bidirectional roads or direct walkways connecting locations
 *
 * Core Features:
 * - Add/Remove Campus Locations (Vertices)
 * - Add/Remove Campus Connections/Roads (Undirected Edges)
 * - Display Campus Adjacency List Topology
 * - Breadth-First Search (BFS) Traversal
 * - Depth-First Search (DFS) Traversal
 * - Complete input validation and error resilience (handles empty graph, duplicate nodes/edges, missing nodes)
 */
public class CampusGraph {
    private CampusLocationNode headLocation;
    private int locationCount;
    private int edgeCount; // Counts total undirected connections

    /**
     * Initializes an empty Campus Graph.
     */
    public CampusGraph() {
        this.headLocation = null;
        this.locationCount = 0;
        this.edgeCount = 0;
    }

    /**
     * Checks whether the graph contains any locations.
     *
     * @return true if graph is empty, false otherwise
     */
    public boolean isEmpty() {
        return headLocation == null || locationCount == 0;
    }

    /**
     * Returns the total count of location vertices in the graph.
     *
     * @return integer location count
     */
    public int getLocationCount() {
        return locationCount;
    }

    /**
     * Returns the total count of undirected connections/roads in the graph.
     *
     * @return integer edge count
     */
    public int getEdgeCount() {
        return edgeCount;
    }

    /**
     * Checks if a location vertex exists in the graph (case-insensitive).
     *
     * @param locationName Name of the location to check
     * @return true if location exists, false otherwise
     */
    public boolean locationExists(String locationName) {
        if (locationName == null || locationName.trim().isEmpty() || isEmpty()) {
            return false;
        }
        return findLocationNode(locationName.trim()) != null;
    }

    /**
     * Helper method to locate a CampusLocationNode by name (case-insensitive).
     *
     * @param locationName Name of the location
     * @return CampusLocationNode reference or null if not found
     */
    private CampusLocationNode findLocationNode(String locationName) {
        if (locationName == null || headLocation == null) {
            return null;
        }
        String target = locationName.trim();
        CampusLocationNode current = headLocation;
        while (current != null) {
            if (current.getLocationName().equalsIgnoreCase(target)) {
                return current;
            }
            current = current.getNextLocation();
        }
        return null;
    }

    /**
     * Adds a new campus location vertex to the graph.
     * Validates non-empty input and duplicate check.
     * Time Complexity: O(V)
     *
     * @param locationName Name of the campus location
     * @return true if added successfully, false if duplicate or invalid
     */
    public boolean addLocation(String locationName) {
        if (locationName == null || locationName.trim().isEmpty()) {
            System.out.println("[Error] Location name cannot be empty or null.");
            return false;
        }

        String name = locationName.trim();

        if (locationExists(name)) {
            System.out.println("[Duplicate Error] Campus location '" + name + "' already exists.");
            return false;
        }

        CampusLocationNode newNode = new CampusLocationNode(name);

        if (headLocation == null) {
            headLocation = newNode;
        } else {
            CampusLocationNode current = headLocation;
            while (current.getNextLocation() != null) {
                current = current.getNextLocation();
            }
            current.setNextLocation(newNode);
        }

        locationCount++;
        return true;
    }

    /**
     * Removes a campus location vertex from the graph, along with all incident edges.
     * Time Complexity: O(V + E)
     *
     * @param locationName Name of the location to remove
     * @return true if removed successfully, false if location not found
     */
    public boolean removeLocation(String locationName) {
        if (locationName == null || locationName.trim().isEmpty() || isEmpty()) {
            System.out.println("[Error] Cannot remove location: Graph is empty or name is blank.");
            return false;
        }

        String target = locationName.trim();
        CampusLocationNode targetNode = findLocationNode(target);

        if (targetNode == null) {
            System.out.println("[Not Found Error] Cannot remove location '" + target + "': Location does not exist.");
            return false;
        }

        // 1. Remove all outgoing edges from targetNode and decrease global edgeCount
        int removedEdgesCount = targetNode.getDegree();
        edgeCount -= removedEdgesCount;

        // 2. Remove all incident incoming edges pointing to targetNode from all other vertices
        CampusLocationNode current = headLocation;
        while (current != null) {
            if (!current.getLocationName().equalsIgnoreCase(target)) {
                if (current.removeEdge(target)) {
                    // Incident edge removed
                }
            }
            current = current.getNextLocation();
        }

        // 3. Remove targetNode from master location linked list
        if (headLocation.getLocationName().equalsIgnoreCase(target)) {
            headLocation = headLocation.getNextLocation();
        } else {
            CampusLocationNode prev = headLocation;
            CampusLocationNode curr = headLocation.getNextLocation();
            while (curr != null) {
                if (curr.getLocationName().equalsIgnoreCase(target)) {
                    prev.setNextLocation(curr.getNextLocation());
                    break;
                }
                prev = curr;
                curr = curr.getNextLocation();
            }
        }

        locationCount--;
        return true;
    }

    /**
     * Checks if a direct connection/road exists between location1 and location2 (case-insensitive).
     *
     * @param location1 Source location
     * @param location2 Destination location
     * @return true if connection exists, false otherwise
     */
    public boolean hasConnection(String location1, String location2) {
        if (location1 == null || location2 == null) {
            return false;
        }
        CampusLocationNode node1 = findLocationNode(location1);
        if (node1 == null) {
            return false;
        }
        return node1.hasEdgeTo(location2);
    }

    /**
     * Adds an undirected connection (road/path) between two campus locations.
     * Default distance weight = 1.0.
     *
     * @param location1 First location
     * @param location2 Second location
     * @return true if connection added, false on validation failure
     */
    public boolean addConnection(String location1, String location2) {
        return addConnection(location1, location2, 1.0);
    }

    /**
     * Adds an undirected connection (road/path) between two campus locations with a weight.
     * Validates node existence, self-loop prohibition, and duplicate edge prevention.
     * Time Complexity: O(V + deg(V))
     *
     * @param location1 First location
     * @param location2 Second location
     * @param weight    Road distance / weight
     * @return true if connection added successfully, false otherwise
     */
    public boolean addConnection(String location1, String location2, double weight) {
        if (location1 == null || location1.trim().isEmpty() || location2 == null || location2.trim().isEmpty()) {
            System.out.println("[Error] Location names cannot be null or blank.");
            return false;
        }

        String loc1 = location1.trim();
        String loc2 = location2.trim();

        if (loc1.equalsIgnoreCase(loc2)) {
            System.out.println("[Validation Error] Cannot connect location '" + loc1 + "' to itself (Self-loops not allowed).");
            return false;
        }

        CampusLocationNode node1 = findLocationNode(loc1);
        CampusLocationNode node2 = findLocationNode(loc2);

        if (node1 == null) {
            System.out.println("[Not Found Error] Cannot add connection: Location '" + loc1 + "' does not exist in graph.");
            return false;
        }
        if (node2 == null) {
            System.out.println("[Not Found Error] Cannot add connection: Location '" + loc2 + "' does not exist in graph.");
            return false;
        }

        if (node1.hasEdgeTo(loc2)) {
            System.out.println("[Duplicate Error] Connection between '" + node1.getLocationName() + "' and '" + node2.getLocationName() + "' already exists.");
            return false;
        }

        // Add bidirectional edges (Undirected Graph)
        node1.addEdge(node2.getLocationName(), weight);
        node2.addEdge(node1.getLocationName(), weight);

        edgeCount++;
        return true;
    }

    /**
     * Removes an undirected connection (road/path) between two campus locations.
     * Time Complexity: O(deg(V1) + deg(V2))
     *
     * @param location1 First location
     * @param location2 Second location
     * @return true if connection removed successfully, false if missing or connection not found
     */
    public boolean removeConnection(String location1, String location2) {
        if (location1 == null || location1.trim().isEmpty() || location2 == null || location2.trim().isEmpty()) {
            System.out.println("[Error] Location names cannot be null or blank.");
            return false;
        }

        String loc1 = location1.trim();
        String loc2 = location2.trim();

        CampusLocationNode node1 = findLocationNode(loc1);
        CampusLocationNode node2 = findLocationNode(loc2);

        if (node1 == null || node2 == null) {
            System.out.println("[Not Found Error] Cannot remove connection: One or both locations do not exist.");
            return false;
        }

        if (!node1.hasEdgeTo(loc2)) {
            System.out.println("[Not Found Error] No direct road exists between '" + node1.getLocationName() + "' and '" + node2.getLocationName() + "'.");
            return false;
        }

        // Remove bidirectional edges
        node1.removeEdge(node2.getLocationName());
        node2.removeEdge(node1.getLocationName());

        edgeCount--;
        return true;
    }

    /**
     * Displays all campus locations and their connected neighbors in Adjacency List format.
     */
    public void displayConnections() {
        if (isEmpty()) {
            System.out.println("\n[Info] Campus Graph is currently empty. No locations or roads defined.");
            return;
        }

        System.out.println("\n==========================================================================");
        System.out.println("            CAMPUS NETWORK CONNECTIONS (ADJACENCY LIST)");
        System.out.println("==========================================================================");

        CampusLocationNode current = headLocation;
        while (current != null) {
            System.out.printf("%-18s -> [", current.getLocationName());
            GraphEdgeNode edge = current.getHeadEdge();
            if (edge == null) {
                System.out.print(" No Direct Connections ");
            } else {
                while (edge != null) {
                    System.out.print(edge.getDestinationName());
                    if (edge.getNext() != null) {
                        System.out.print(", ");
                    }
                    edge = edge.getNext();
                }
            }
            System.out.println("]");
            current = current.getNextLocation();
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Total Locations (Vertices): " + locationCount + " | Total Roads (Edges): " + edgeCount);
        System.out.println("==========================================================================\n");
    }

    /**
     * Performs Breadth-First Search (BFS) starting from startLocation.
     * Explores locations level-by-level (shallowest neighbor paths first).
     *
     * @param startLocation Starting campus location
     * @return Array of location names in BFS traversal order, or empty array if invalid
     */
    public String[] bfs(String startLocation) {
        if (isEmpty()) {
            System.out.println("[Error] Cannot perform BFS: Campus Graph is empty.");
            return new String[0];
        }

        if (startLocation == null || startLocation.trim().isEmpty()) {
            System.out.println("[Error] BFS start location cannot be null or blank.");
            return new String[0];
        }

        CampusLocationNode startNode = findLocationNode(startLocation.trim());
        if (startNode == null) {
            System.out.println("[Invalid Start Location] Location '" + startLocation.trim() + "' does not exist in the graph.");
            return new String[0];
        }

        // Data structures for BFS traversal
        String[] visited = new String[locationCount];
        int visitedCount = 0;

        // Custom linked Queue array representation for traversal
        String[] queue = new String[locationCount * 2];
        int front = 0;
        int rear = 0;

        // Enqueue start location
        queue[rear++] = startNode.getLocationName();
        visited[visitedCount++] = startNode.getLocationName();

        while (front < rear) {
            String currentLocName = queue[front++];
            CampusLocationNode currentVertex = findLocationNode(currentLocName);

            if (currentVertex != null) {
                GraphEdgeNode edge = currentVertex.getHeadEdge();
                while (edge != null) {
                    String neighbor = edge.getDestinationName();
                    if (!containsString(visited, visitedCount, neighbor)) {
                        visited[visitedCount++] = neighbor;
                        queue[rear++] = neighbor;
                    }
                    edge = edge.getNext();
                }
            }
        }

        // Trim result array
        String[] result = new String[visitedCount];
        System.arraycopy(visited, 0, result, 0, visitedCount);

        printTraversalResult("Breadth-First Search (BFS)", startNode.getLocationName(), result);
        return result;
    }

    /**
     * Performs Depth-First Search (DFS) starting from startLocation.
     * Explores locations deeply along each branch before backtracking.
     *
     * @param startLocation Starting campus location
     * @return Array of location names in DFS traversal order, or empty array if invalid
     */
    public String[] dfs(String startLocation) {
        if (isEmpty()) {
            System.out.println("[Error] Cannot perform DFS: Campus Graph is empty.");
            return new String[0];
        }

        if (startLocation == null || startLocation.trim().isEmpty()) {
            System.out.println("[Error] DFS start location cannot be null or blank.");
            return new String[0];
        }

        CampusLocationNode startNode = findLocationNode(startLocation.trim());
        if (startNode == null) {
            System.out.println("[Invalid Start Location] Location '" + startLocation.trim() + "' does not exist in the graph.");
            return new String[0];
        }

        String[] visited = new String[locationCount];
        int[] visitedCount = new int[]{0};

        dfsHelper(startNode.getLocationName(), visited, visitedCount);

        String[] result = new String[visitedCount[0]];
        System.arraycopy(visited, 0, result, 0, visitedCount[0]);

        printTraversalResult("Depth-First Search (DFS)", startNode.getLocationName(), result);
        return result;
    }

    /**
     * Recursive helper algorithm for Depth-First Search traversal.
     *
     * @param locationName Current location vertex name
     * @param visited      Visited array accumulator
     * @param visitedCount Pointer array tracking visited element count
     */
    private void dfsHelper(String locationName, String[] visited, int[] visitedCount) {
        visited[visitedCount[0]++] = locationName;

        CampusLocationNode node = findLocationNode(locationName);
        if (node == null) return;

        GraphEdgeNode edge = node.getHeadEdge();
        while (edge != null) {
            String neighbor = edge.getDestinationName();
            if (!containsString(visited, visitedCount[0], neighbor)) {
                dfsHelper(neighbor, visited, visitedCount);
            }
            edge = edge.getNext();
        }
    }

    /**
     * Helper to print traversal path output cleanly.
     *
     * @param algorithmName Algorithm name (BFS or DFS)
     * @param startLocation Starting vertex
     * @param traversalPath Ordered traversal array
     */
    private void printTraversalResult(String algorithmName, String startLocation, String[] traversalPath) {
        System.out.println("\n--------------------------------------------------------------------------");
        System.out.println(" " + algorithmName + " Traversal (Start: \"" + startLocation + "\")");
        System.out.println("--------------------------------------------------------------------------");
        if (traversalPath.length == 0) {
            System.out.println(" No locations visited.");
        } else {
            System.out.print(" Path: ");
            for (int i = 0; i < traversalPath.length; i++) {
                System.out.print(traversalPath[i]);
                if (i < traversalPath.length - 1) {
                    System.out.print(" ---> ");
                }
            }
            System.out.println();
            System.out.println(" Total Reachable Locations Visited: " + traversalPath.length + " of " + locationCount);
        }
        System.out.println("--------------------------------------------------------------------------\n");
    }

    /**
     * Checks if array contains target string case-insensitively up to length.
     */
    private boolean containsString(String[] array, int length, String target) {
        if (array == null || target == null) return false;
        for (int i = 0; i < length; i++) {
            if (array[i] != null && array[i].equalsIgnoreCase(target.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an array of all campus location names currently in the graph.
     *
     * @return String array of location names
     */
    public String[] getAllLocations() {
        String[] locations = new String[locationCount];
        CampusLocationNode current = headLocation;
        int idx = 0;
        while (current != null) {
            locations[idx++] = current.getLocationName();
            current = current.getNextLocation();
        }
        return locations;
    }

    /**
     * Populates the graph with the standard 8 example university campus locations
     * and 7 direct road connections specified in the CIT300 assignment brief.
     */
    public void populateSampleCampus() {
        // Clear existing graph if any
        clear();

        // 1. Add 8 Example Locations
        addLocation("Library");
        addLocation("Main Hall");
        addLocation("Canteen");
        addLocation("Computer Lab");
        addLocation("Engineering Lab");
        addLocation("Administration");
        addLocation("Student Centre");
        addLocation("Cafeteria");

        // 2. Add 7 Example Connections
        addConnection("Library", "Main Hall");
        addConnection("Library", "Canteen");
        addConnection("Main Hall", "Computer Lab");
        addConnection("Computer Lab", "Engineering Lab");
        addConnection("Canteen", "Cafeteria");
        addConnection("Cafeteria", "Student Centre");
        addConnection("Student Centre", "Administration");
    }

    /**
     * Resets/Clears the graph structure completely.
     */
    public void clear() {
        headLocation = null;
        locationCount = 0;
        edgeCount = 0;
    }
}
