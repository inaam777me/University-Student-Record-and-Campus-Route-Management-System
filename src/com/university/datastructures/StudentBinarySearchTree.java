package com.university.datastructures;

import com.university.model.Student;

/**
 * Binary search tree of students ordered by Student ID (case-insensitively).
 */
public class StudentBinarySearchTree {
    private static class Node {
        private final Student student;
        private Node left;
        private Node right;

        private Node(Student student) {
            this.student = student;
        }
    }

    private Node root;
    private int size;

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        return size;
    }

    public boolean insert(Student student) {
        if (student == null || !isValidId(student.getStudentId())) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            size++;
            return true;
        }

        Node current = root;
        while (true) {
            int comparison = compareIds(student.getStudentId(), current.student.getStudentId());
            if (comparison == 0) {
                return false;
            }
            if (comparison < 0) {
                if (current.left == null) {
                    current.left = new Node(student);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
    }

    public Student search(String studentId) {
        if (!isValidId(studentId)) {
            return null;
        }

        Node current = root;
        while (current != null) {
            int comparison = compareIds(studentId, current.student.getStudentId());
            if (comparison == 0) {
                return current.student;
            }
            current = comparison < 0 ? current.left : current.right;
        }
        return null;
    }

    public boolean contains(String studentId) {
        return search(studentId) != null;
    }

    public boolean delete(String studentId) {
        if (!contains(studentId)) {
            return false;
        }
        root = deleteNode(root, studentId);
        size--;
        return true;
    }

    /** Prints students in ascending Student ID order. */
    public void inorderTraversal() {
        if (isEmpty()) {
            System.out.println("[Info] The BST is empty; there are no students to display.");
            return;
        }
        inorderTraversal(root);
    }

    private Node deleteNode(Node node, String studentId) {
        int comparison = compareIds(studentId, node.student.getStudentId());
        if (comparison < 0) {
            node.left = deleteNode(node.left, studentId);
        } else if (comparison > 0) {
            node.right = deleteNode(node.right, studentId);
        } else if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        } else {
            Node successor = minimum(node.right);
            successor.right = deleteNode(node.right, successor.student.getStudentId());
            successor.left = node.left;
            return successor;
        }
        return node;
    }

    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void inorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.student);
        inorderTraversal(node.right);
    }

    private static int compareIds(String first, String second) {
        return first.trim().compareToIgnoreCase(second.trim());
    }

    private static boolean isValidId(String studentId) {
        return studentId != null && !studentId.trim().isEmpty();
    }
}