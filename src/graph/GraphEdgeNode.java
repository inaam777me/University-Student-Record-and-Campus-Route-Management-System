package graph;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 4 - Campus Graph Component
 *
 * GraphEdgeNode represents a connection/edge in the adjacency linked list of a location vertex.
 * Stores the destination location name, road weight, and pointer to the next edge node.
 */
public class GraphEdgeNode {
    private String destinationName;
    private double weight;
    private GraphEdgeNode next;

    /**
     * Constructs an edge node with default weight of 1.0.
     *
     * @param destinationName Destination location name
     */
    public GraphEdgeNode(String destinationName) {
        this(destinationName, 1.0, null);
    }

    /**
     * Constructs an edge node with destination name and road weight.
     *
     * @param destinationName Destination location name
     * @param weight          Road distance or weight
     */
    public GraphEdgeNode(String destinationName, double weight) {
        this(destinationName, weight, null);
    }

    /**
     * Constructs an edge node with destination name, weight, and next link.
     *
     * @param destinationName Destination location name
     * @param weight          Road distance or weight
     * @param next            Link to next GraphEdgeNode in adjacency list
     */
    public GraphEdgeNode(String destinationName, double weight, GraphEdgeNode next) {
        this.destinationName = destinationName;
        this.weight = weight;
        this.next = next;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public GraphEdgeNode getNext() {
        return next;
    }

    public void setNext(GraphEdgeNode next) {
        this.next = next;
    }
}
