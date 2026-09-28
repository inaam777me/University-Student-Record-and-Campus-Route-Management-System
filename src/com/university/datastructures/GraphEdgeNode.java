package com.university.datastructures;

/**
 * CIT300 Data Structures and Algorithms - Group Project
 * Project: University Student Record and Campus Route Management System
 * Responsibility: Member 4 - Graph Representation & Route Management
 *
 * GraphEdgeNode represents a directed connection/edge in the adjacency list of a vertex.
 * It stores the destination location name, road distance/weight, and a reference to the
 * next edge in the linked list.
 */
public class GraphEdgeNode {
    private String destinationName;
    private double weight;
    private GraphEdgeNode next;

    /**
     * Constructs an edge node pointing to a destination location with a default weight of 1.0.
     *
     * @param destinationName Name of the connected campus location
     */
    public GraphEdgeNode(String destinationName) {
        this(destinationName, 1.0, null);
    }

    /**
     * Constructs an edge node with destination location name and weight.
     *
     * @param destinationName Name of the connected campus location
     * @param weight          Distance or travel cost of the road/connection
     */
    public GraphEdgeNode(String destinationName, double weight) {
        this(destinationName, weight, null);
    }

    /**
     * Constructs an edge node with destination location name, weight, and next link.
     *
     * @param destinationName Name of the connected campus location
     * @param weight          Distance or travel cost
     * @param next            Reference to next GraphEdgeNode in adjacency list
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
