package com.university.datastructures;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 4 - Graph Representation & Route Management
 *
 * CampusLocationNode represents a vertex in the campus graph master location list.
 * Each node maintains a location name, a linked list of incident edges (headEdge),
 * and a reference to the next location vertex node (nextLocation).
 */
public class CampusLocationNode {
    private String locationName;
    private GraphEdgeNode headEdge;
    private CampusLocationNode nextLocation;

    /**
     * Constructs a campus location vertex.
     *
     * @param locationName The name of the campus location (e.g., "Library")
     */
    public CampusLocationNode(String locationName) {
        this.locationName = locationName;
        this.headEdge = null;
        this.nextLocation = null;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public GraphEdgeNode getHeadEdge() {
        return headEdge;
    }

    public void setHeadEdge(GraphEdgeNode headEdge) {
        this.headEdge = headEdge;
    }

    public CampusLocationNode getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(CampusLocationNode nextLocation) {
        this.nextLocation = nextLocation;
    }

    /**
     * Checks if a direct edge connection exists to destinationName (case-insensitive).
     *
     * @param destinationName Destination location to check
     * @return true if connected, false otherwise
     */
    public boolean hasEdgeTo(String destinationName) {
        if (destinationName == null || headEdge == null) {
            return false;
        }
        GraphEdgeNode current = headEdge;
        while (current != null) {
            if (current.getDestinationName().equalsIgnoreCase(destinationName.trim())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Adds an edge to destinationName if not already present.
     *
     * @param destinationName Destination location
     * @param weight          Distance or weight
     * @return true if added, false if duplicate edge
     */
    public boolean addEdge(String destinationName, double weight) {
        if (destinationName == null || destinationName.trim().isEmpty()) {
            return false;
        }
        if (hasEdgeTo(destinationName)) {
            return false; // Duplicate edge
        }

        GraphEdgeNode newEdge = new GraphEdgeNode(destinationName.trim(), weight);
        if (headEdge == null) {
            headEdge = newEdge;
        } else {
            GraphEdgeNode current = headEdge;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newEdge);
        }
        return true;
    }

    /**
     * Removes an edge pointing to destinationName (case-insensitive).
     *
     * @param destinationName Destination location
     * @return true if removed, false if not found
     */
    public boolean removeEdge(String destinationName) {
        if (destinationName == null || headEdge == null) {
            return false;
        }

        String target = destinationName.trim();

        // Case 1: Head edge matches
        if (headEdge.getDestinationName().equalsIgnoreCase(target)) {
            headEdge = headEdge.getNext();
            return true;
        }

        // Case 2: Intermediate/tail edge matches
        GraphEdgeNode prev = headEdge;
        GraphEdgeNode curr = headEdge.getNext();

        while (curr != null) {
            if (curr.getDestinationName().equalsIgnoreCase(target)) {
                prev.setNext(curr.getNext());
                return true;
            }
            prev = curr;
            curr = curr.getNext();
        }

        return false;
    }

    /**
     * Counts the degree (number of outgoing/incident connections) for this vertex.
     *
     * @return count of neighbor locations
     */
    public int getDegree() {
        int count = 0;
        GraphEdgeNode current = headEdge;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
