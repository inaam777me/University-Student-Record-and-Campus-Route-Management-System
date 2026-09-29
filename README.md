# University Student Record and Campus Route Management System

**Course:** CIT300 – Data Structures and Algorithms
**Project Type:** Graded Practical Assignment 1
**Project Role:** Lead Developer / Integration
**Programming Language:** Java
**Application Type:** Console-Based Application

---

## 📌 Project Overview

The **University Student Record and Campus Route Management System** is a unified Java console application developed for the CIT300 Data Structures and Algorithms module.

The system demonstrates the practical implementation and integration of fundamental data structures and algorithms in a real-world university management scenario.

The application combines student record management, service request processing, and campus route management into one integrated system.

The project demonstrates the following major data structures:

1. **Custom Singly Linked List** – Student record storage and CRUD operations.
2. **Custom Linked Stack (LIFO)** – Recent action and audit history.
3. **Custom Linked Queue (FIFO)** – Student service request processing.
4. **Binary Search Tree (BST)** – Student organization and searching by Student ID.
5. **Separate-Chaining Hash Table** – Efficient Student ID lookup.
6. **Undirected Graph using Adjacency Lists** – Campus locations and roads.
7. **BFS and DFS Graph Traversals** – Campus network traversal.

The system is designed to demonstrate how multiple data structures can work together while maintaining consistent student information across the application.

---

# 🎯 Project Objectives

The main objectives of this project are:

* To implement fundamental data structures manually using Java.
* To understand the practical use of Linked Lists, Stacks, Queues, BSTs, Hash Tables, and Graphs.
* To develop a menu-driven console application.
* To perform Create, Read, Update, and Delete operations on student records.
* To efficiently search student records using hashing.
* To organize student records using a Binary Search Tree.
* To process service requests using FIFO queue principles.
* To maintain recent system actions using LIFO stack principles.
* To represent campus locations and roads using a graph.
* To implement BFS and DFS graph traversal algorithms.
* To provide defensive input validation.
* To integrate all group members' components into one working system.
* To demonstrate collaboration using Git and GitHub.

---

# 🏗️ System Features

The integrated application provides the following features:

### Student Management

* Add a new student.
* Update an existing student.
* Delete a student.
* Display all students using a custom Linked List.
* Validate Student IDs.
* Validate student names and programmes.
* Validate marks between 0 and 100.
* Prevent duplicate Student IDs.

### Service Request Management

* Add student service requests.
* Validate that the student exists.
* Prevent duplicate pending request IDs.
* Process requests in FIFO order.
* Display empty-queue messages safely.

### Action History

* Record student additions.
* Record student updates.
* Record student deletions.
* Record processed service requests.
* Display recent actions using LIFO order.

### Student Searching and Organization

* Display students using BST inorder traversal.
* Search students using a Separate-Chaining Hash Table.
* Maintain Student ID ordering in the BST.
* Maintain efficient hash-based lookup.

### Campus Management

* Add campus locations.
* Remove campus locations.
* Add campus roads/connections.
* Remove campus roads/connections.
* Display campus connections.
* Perform BFS traversal.
* Perform DFS traversal.
* Validate campus locations and connections.

---

# 🧱 Data Structures Implemented

## 1. Custom Singly Linked List

The Linked List is used as the primary dynamic storage structure for student records.

Each student record is stored in a custom `StudentNode`.

### Main operations

* Add student
* Update student
* Delete student
* Search student
* Display all students

### Main classes

```text
StudentNode.java
StudentLinkedList.java
```

The implementation uses manually created nodes and links rather than Java's built-in `LinkedList`.

---

## 2. Custom Linked Stack

The Stack follows the **LIFO (Last In, First Out)** principle.

It stores recent system actions such as:

* Student added
* Student updated
* Student deleted
* Service request processed

The most recent action is displayed first.

### Main class

```text
ActionStack.java
```

### Example

If the following actions occur:

```text
Student Added
Student Updated
Student Deleted
```

The stack displays:

```text
Student Deleted
Student Updated
Student Added
```

This demonstrates the LIFO principle.

---

## 3. Custom Linked Queue

The Queue follows the **FIFO (First In, First Out)** principle.

It is used to process student service requests in the order they are received.

### Main class

```text
ServiceRequestQueue.java
```

### Example

If requests arrive in this order:

```text
R101
R102
R103
```

They are processed in:

```text
R101
R102
R103
```

This demonstrates the FIFO principle.

---

# 4. Binary Search Tree

The Binary Search Tree organizes student records according to their Student IDs.

For each node:

```text
Left Subtree < Current Node < Right Subtree
```

The Student ID is used as the BST key.

### Main operations

* Insert
* Search
* Delete
* Inorder traversal

### Main class

```text
StudentBinarySearchTree.java
```

### Complexity

Average-case search:

```text
O(log n)
```

Worst-case search:

```text
O(n)
```

Inorder traversal produces Student IDs in sorted order.

For example:

```text
S001
S102
S103
S104
S105
```

---

# 5. Separate-Chaining Hash Table

The Hash Table is used for efficient Student ID searching.

The implementation uses **separate chaining** to handle collisions.

Each bucket can contain a linked chain of student records.

### Main class

```text
StudentHashTable.java
```

The implementation contains:

```text
11 buckets
```

Average lookup performance is approximately:

```text
O(1)
```

depending on the distribution of keys and load factor.

---

# 6. Undirected Campus Graph

The campus route management system uses an undirected graph.

The graph contains:

* Vertices → Campus locations
* Edges → Roads/connections

The graph is represented using adjacency lists.

### Main classes

```text
CampusLocationNode.java
GraphEdgeNode.java
CampusGraph.java
```

Because the graph is undirected, a connection such as:

```text
Library ↔ Canteen
```

is stored in both directions.

---

# 7. BFS and DFS

Two graph traversal algorithms are implemented.

## Breadth-First Search (BFS)

BFS visits connected locations level by level.

Example:

```text
Starting Location
       ↓
Nearby Locations
       ↓
Next-Level Locations
       ↓
Further Locations
```

BFS is useful for exploring campus connectivity and shortest paths in an unweighted graph.

---

## Depth-First Search (DFS)

DFS explores one branch as deeply as possible before backtracking.

It is useful for exploring connected campus locations and graph structure.

### Main class

```text
CampusGraph.java
```

---

# 🚫 No Built-In Data Structure Replacement

The project implements the required data structures manually.

The application does not use Java's built-in collection classes as replacements for the required structures, such as:

```text
ArrayList
LinkedList
HashMap
TreeMap
Stack
Queue
```

Instead, custom nodes, pointers, links, buckets, and graph adjacency structures are implemented.

Standard Java utility classes may still be used where appropriate for normal application functionality, such as input handling.

---

# 📂 Project Structure

```text
University Student Record and Campus Route Management System/
│
├── bin/
│   └── # Compiled Java bytecode
│
├── src/
│   └── com/
│       └── university/
│           │
│           ├── model/
│           │   ├── Student.java
│           │   ├── Action.java
│           │   └── ServiceRequest.java
│           │
│           ├── datastructures/
│           │   ├── StudentNode.java
│           │   ├── StudentLinkedList.java
│           │   ├── ActionStack.java
│           │   ├── ServiceRequestQueue.java
│           │   ├── StudentBinarySearchTree.java
│           │   ├── StudentHashTable.java
│           │   ├── CampusLocationNode.java
│           │   ├── GraphEdgeNode.java
│           │   └── CampusGraph.java
│           │
│           ├── management/
│           │   ├── StudentManager.java
│           │   └── StudentServiceManager.java
│           │
│           ├── app/
│           │   ├── Main.java
│           │   └── StudentRecordApp.java
│           │
│           └── test/
│               ├── StudentManagementTest.java
│               ├── Member2ServicesTest.java
│               └── CampusGraphTest.java
│
└── README.md
```

---

# 🔄 Multi-Structure Synchronization Architecture

The system uses `StudentManager` as the central controller for maintaining student information across multiple data structures.

```text
                  USER OPERATION
                        │
                        ▼
                ┌───────────────┐
                │ StudentManager│
                └───────┬───────┘
                        │
       ┌────────────────┼────────────────┐
       │                │                │
       ▼                ▼                ▼
 Linked List           BST         Hash Table
       │                │                │
       └────────────────┼────────────────┘
                        │
                        ▼
                  Action Stack
```

---

## Add Student

When a student is added:

```text
User Input
    ↓
Validation
    ↓
StudentLinkedList
    ↓
StudentBinarySearchTree
    ↓
StudentHashTable
    ↓
ActionStack
```

The action:

```text
STUDENT_ADDED
```

is recorded in the Action Stack.

---

## Update Student

When an existing student is updated:

1. The Student ID is searched.
2. The student record is validated.
3. The student's details are updated.
4. The corresponding data structures remain synchronized.
5. The action is recorded.

The system records:

```text
STUDENT_UPDATED
```

The BST and Hash Table reference the student object used by the integrated system, allowing updated student information to remain consistent.

---

## Delete Student

When a student is deleted:

1. The student is located.
2. The student is removed from the Linked List.
3. The student is removed from the BST while maintaining BST ordering.
4. The student is removed from the Hash Table.
5. A deletion action is pushed onto the Stack.

The action recorded is:

```text
STUDENT_DELETED
```

---

# 🔄 Service Request Pipeline

The service request system follows the following process:

```text
Student ID
    ↓
Validate Student
    ↓
Create Service Request
    ↓
Add to Queue
    ↓
FIFO Processing
    ↓
Action Stack
```

The system verifies that the Student ID exists before adding the request to the queue.

When `processNextRequest()` is executed:

```text
Front Request
     ↓
Dequeue
     ↓
Process Request
     ↓
Record Action
```

The processed request is recorded as:

```text
SERVICE_REQUEST_PROCESSED
```

---

# 🗺️ Campus Graph Architecture

Campus locations are represented as graph vertices.

Campus roads are represented as edges.

Example:

```text
Library ───── Canteen
                │
                │
            Cafeteria
                │
          Student Centre
                │
         Administration
```

Because the graph is undirected, roads can be travelled in both directions.

---

# 📋 Master Console Menu

The integrated application contains 16 main options.

```text
=================================================================
      UNIVERSITY STUDENT RECORD AND CAMPUS ROUTE MANAGEMENT SYSTEM
                  CIT300 Data Structures and Algorithms
=================================================================

1.  Add Student Record
2.  Update Student Record
3.  Delete Student Record
4.  Display All Records using Linked List
5.  Add Service Request to Queue
6.  Process Next Service Request
7.  Display Recent Actions using Stack
8.  Display Students using BST
9.  Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS/DFS
16. Exit

=================================================================
```

---

# 🛡️ Input Validation

The application includes defensive input validation to prevent invalid operations and unexpected program termination.

| Input           | Validation                         |
| --------------- | ---------------------------------- |
| Student ID      | Cannot be blank and must be unique |
| Student Name    | Cannot be empty                    |
| Programme       | Cannot be empty                    |
| Marks           | Must be between 0 and 100          |
| Student Search  | Student must exist                 |
| Student Update  | Student must exist                 |
| Student Delete  | Student must exist                 |
| Service Request | Student ID must exist              |
| Request ID      | Must be unique                     |
| Queue           | Empty queue handled safely         |
| Stack           | Empty stack handled safely         |
| Campus Location | Must be unique and non-empty       |
| Campus Road     | Both locations must exist          |
| Campus Road     | Self-connections are rejected      |
| Campus Road     | Duplicate roads are rejected       |
| BFS Start       | Location must exist                |
| DFS Start       | Location must exist                |

---

# ❌ Invalid Input Handling

The system handles invalid inputs such as:

```text
Duplicate Student ID
Invalid marks
Missing Student ID
Empty student name
Empty programme
Duplicate campus location
Non-existing campus location
Duplicate campus connection
Self-loop campus connection
Missing BFS location
Missing DFS location
Empty queue
Empty stack
```

The application displays a suitable message instead of terminating unexpectedly.

---

# 🧪 Testing

The project contains automated verification classes for the major components.

## Test Suite 1 – Student Management

```text
StudentManagementTest.java
```

Tests:

* Student model
* Linked List
* Student CRUD
* BST
* Hash Table
* Synchronization

Total:

```text
65 tests
```

---

## Test Suite 2 – Stack and Queue

```text
Member2ServicesTest.java
```

Tests:

* Action creation
* Stack push
* Stack pop
* LIFO behavior
* Service request creation
* Queue enqueue
* Queue dequeue
* FIFO behavior
* Student-aware service validation

Total:

```text
28 tests
```

---

## Test Suite 3 – Campus Graph

```text
CampusGraphTest.java
```

Tests:

* Campus location creation
* Location removal
* Road creation
* Road removal
* Duplicate locations
* Duplicate roads
* Adjacency list
* BFS
* DFS
* Invalid traversal locations

Total:

```text
50 tests
```

---

## Total Automated Tests

```text
65 + 28 + 50 = 143 tests
```

The three test suites should be executed before final submission to confirm the functionality of each project component.

---

# 🧪 Manual Integration Testing

In addition to automated tests, the integrated application should be manually tested using the master menu.

Important scenarios include:

### Student Testing

* Add valid student.
* Add duplicate Student ID.
* Update existing student.
* Update non-existing student.
* Delete existing student.
* Delete non-existing student.
* Enter invalid marks.
* Display Linked List.
* Display BST.
* Search using Hash Table.

### Queue Testing

Example:

```text
R101
R102
R103
```

Expected processing order:

```text
R101
R102
R103
```

This confirms FIFO behavior.

### Stack Testing

Example actions:

```text
STUDENT_ADDED
STUDENT_UPDATED
STUDENT_DELETED
```

Expected display order:

```text
STUDENT_DELETED
STUDENT_UPDATED
STUDENT_ADDED
```

This confirms LIFO behavior.

### Graph Testing

* Add location.
* Add duplicate location.
* Add valid road.
* Add duplicate road.
* Add road using a missing location.
* Remove road.
* Remove location.
* BFS traversal.
* DFS traversal.
* Invalid BFS/DFS starting location.

---

# ⚙️ Compilation and Execution

## Requirements

The following software is required:

* Java Development Kit (JDK)
* Git
* Command Prompt or PowerShell
* GitHub account for repository collaboration

---

## 1. Compile the Project

### Windows PowerShell

```powershell
javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
```

### Linux / macOS / Git Bash

```bash
javac -d bin $(find src -name "*.java")
```

---

## 2. Run the Integrated Application

```bash
java -cp bin com.university.app.Main
```

The integrated master application is:

```text
com.university.app.Main
```

---

# 🧪 Running Automated Tests

## Student Management Tests

```bash
java -cp bin com.university.test.StudentManagementTest
```

Expected test coverage:

```text
65 tests
```

---

## Member 2 Stack and Queue Tests

```bash
java -cp bin com.university.test.Member2ServicesTest
```

Expected test coverage:

```text
28 tests
```

---

## Campus Graph Tests

```bash
java -cp bin com.university.test.CampusGraphTest
```

Expected test coverage:

```text
50 tests
```

---

# 👥 Group Member Contributions

## Member 1 – Student Management

**Responsibilities:**

* `Student.java`
* `StudentNode.java`
* `StudentLinkedList.java`
* Student CRUD operations
* Student validation

### Contribution

Member 1 developed the student entity and custom Linked List implementation used for the primary student record management functionality.

---

## Member 2 – Stack and Queue

**Responsibilities:**

* `Action.java`
* `ServiceRequest.java`
* `ActionStack.java`
* `ServiceRequestQueue.java`
* `StudentServiceManager.java`

### Contribution

Member 2 developed the custom Stack and Queue implementations.

The Stack manages recent actions using LIFO principles, while the Queue manages student service requests using FIFO principles.

---

## Member 3 – BST and Hashing

**Responsibilities:**

* `StudentBinarySearchTree.java`
* `StudentHashTable.java`

### Contribution

Member 3 developed the Binary Search Tree for Student ID organization and searching, as well as the Separate-Chaining Hash Table for efficient Student ID lookup.

---

## Member 4 – Campus Graph

**Responsibilities:**

* `CampusLocationNode.java`
* `GraphEdgeNode.java`
* `CampusGraph.java`
* Campus location management
* Campus road management
* BFS
* DFS

### Contribution

Member 4 developed the campus graph using an adjacency-list representation and implemented campus location management, road management, BFS, and DFS traversal.

---

# 👨‍💻 Lead Developer / Integration

**Responsibilities:**

* Integration of all group components.
* Master `Main.java`.
* Multi-structure synchronization.
* Integration testing.
* Conflict resolution.
* Debugging.
* Validation testing.
* Git/GitHub integration.
* Final README preparation.
* Final system verification.

The Lead Developer integrated the individual components into a single menu-driven application and ensured that student information remained synchronized across the Linked List, BST, Hash Table, and Action Stack.

---

# 👨‍👩‍👧‍👦 Group Member Details

> Replace the following placeholders with the actual group information before submission.

| Member   | Student Name | Student ID     | Main Responsibility   |
| -------- | ------------ | -------------- | --------------------- |
| Member 1 | `B.Inaamul Hasan`     | `23da2-0870` | Student / Linked List |
| Member 2 | `Mushna`     | `23da2-0756` | Stack / Queue         |
| Member 3 | `Safana`     | `23da2-0474` | BST / Hashing         |
| Member 4 | `Sahama`     | `23da2-0707` | Graph / BFS / DFS     |

---

# 🔀 Git and GitHub Collaboration

The project was developed collaboratively using Git and GitHub.

The repository contains the integrated project and development history.

### Repository

```text
https://github.com/inaam777me/University-Student-Record-and-Campus-Route-Management-System
```

### Suggested branch structure

```text
main

feature/member1-student-linked-list
feature/member2-stack-queue
feature/member3-bst-hashing
feature/member4-graph-bfs-dfs
```

Each member can work on their assigned component and merge the completed implementation into the main branch.

---

# 🔄 Development Workflow

The development process followed these general stages:

```text
Requirement Analysis
        ↓
Component Allocation
        ↓
Individual Development
        ↓
Component Testing
        ↓
Git/GitHub Collaboration
        ↓
Component Integration
        ↓
Synchronization Testing
        ↓
Validation Testing
        ↓
Final System Testing
        ↓
Documentation
        ↓
Final Submission
```

---

# 🐛 Error Handling and Defensive Programming

The application is designed to avoid common runtime failures.

Examples include:

* Checking for null values.
* Checking for empty input.
* Checking whether records exist before update/delete operations.
* Checking duplicate Student IDs.
* Checking marks range.
* Checking whether campus locations exist.
* Checking duplicate roads.
* Preventing self-loop roads.
* Handling empty Stack operations.
* Handling empty Queue operations.
* Validating BFS and DFS starting locations.

The application provides user-friendly messages instead of exposing Java runtime exceptions to the user during normal invalid input.

---

# 📊 Complexity Overview

| Data Structure / Algorithm | Main Operation | Average / Typical Complexity     |
| -------------------------- | -------------- | -------------------------------- |
| Linked List                | Search         | O(n)                             |
| Linked List                | Add            | O(n) depending on implementation |
| Linked List                | Delete         | O(n)                             |
| Stack                      | Push           | O(1)                             |
| Stack                      | Pop            | O(1)                             |
| Queue                      | Enqueue        | O(1)                             |
| Queue                      | Dequeue        | O(1)                             |
| BST                        | Search         | O(log n) average                 |
| BST                        | Search         | O(n) worst case                  |
| BST                        | Insert         | O(log n) average                 |
| BST                        | Delete         | O(log n) average                 |
| Hash Table                 | Search         | O(1) average                     |
| Hash Table                 | Search         | O(n) worst case                  |
| BFS                        | Traversal      | O(V + E)                         |
| DFS                        | Traversal      | O(V + E)                         |

Where:

```text
V = Number of vertices
E = Number of graph edges
n = Number of records
```

---

# 🔐 Data Consistency

The integrated system attempts to maintain the same student information across all relevant data structures.

For example, when Student `S001` is added:

```text
StudentLinkedList
        +
StudentBST
        +
StudentHashTable
        +
ActionStack
```

When the student is updated, the corresponding student information remains synchronized.

When the student is deleted, the record is removed from the student-related structures.

---

# 🖥️ Example System Flow

## Adding a Student

```text
User selects Option 1
        ↓
Enter Student ID
        ↓
Validate ID
        ↓
Enter Name
        ↓
Enter Programme
        ↓
Enter Marks
        ↓
Validate Data
        ↓
Create Student
        ↓
Linked List
        ↓
BST
        ↓
Hash Table
        ↓
Action Stack
        ↓
Student Added Successfully
```

---

# 🔎 Searching a Student

The Hash Table can be used for Student ID lookup.

Example:

```text
Enter Student ID: S104
```

The system searches the hash table and returns the matching student record if available.

---

# 🌳 BST Display

The BST uses inorder traversal.

Example:

```text
S001
S102
S103
S104
S105
```

This demonstrates that the records are organized according to Student ID.

---

# 🗺️ Campus Traversal Example

A campus network may contain:

```text
Library
Canteen
Cafeteria
Student Centre
Administration
Computer Lab
Engineering Lab
Main Hall
```

The graph stores the connections between these locations.

A BFS or DFS traversal can then start from a selected location.

Example:

```text
Start: Library

BFS:
Library
Canteen
Cafeteria
Student Centre
...
```

The exact traversal order depends on the adjacency-list structure and insertion order.

---

# 🎥 Demonstration Video

The project demonstration should demonstrate the complete integrated system.

Recommended demonstration sequence:

```text
00:00 – 01:00
Project introduction and group members

01:00 – 03:00
Student CRUD and Linked List

03:00 – 04:00
Stack and recent actions

04:00 – 05:00
Queue and FIFO processing

05:00 – 07:00
BST and inorder traversal

07:00 – 08:00
Hash Table searching

08:00 – 11:00
Campus Graph, roads, BFS and DFS

11:00 – 12:30
Input validation and error handling

12:30 – 14:00
GitHub and group contribution

14:00 – 14:30
Final conclusion
```

Each group member should explain their own component and contribution.

---

# 📦 Final Submission Checklist

Before submitting the project, verify the following:

### Source Code

* [ ] All `.java` source files are included.
* [ ] Project compiles successfully.
* [ ] `Main.java` runs successfully.
* [ ] No unnecessary files are missing.

### Student Management

* [ ] Add Student works.
* [ ] Update Student works.
* [ ] Delete Student works.
* [ ] Linked List display works.
* [ ] Duplicate Student IDs are rejected.
* [ ] Invalid marks are rejected.

### Stack

* [ ] Actions are recorded.
* [ ] Stack displays newest action first.
* [ ] Empty stack is handled.

### Queue

* [ ] Requests can be added.
* [ ] Requests are processed FIFO.
* [ ] Invalid Student IDs are rejected.
* [ ] Empty queue is handled.

### BST

* [ ] Students can be inserted.
* [ ] Students can be deleted.
* [ ] Inorder traversal works.
* [ ] Student IDs appear in sorted order.

### Hash Table

* [ ] Student IDs can be searched.
* [ ] Separate chaining works.
* [ ] Missing records are handled.

### Graph

* [ ] Locations can be added.
* [ ] Locations can be removed.
* [ ] Roads can be added.
* [ ] Roads can be removed.
* [ ] Duplicate roads are rejected.
* [ ] Invalid locations are rejected.
* [ ] BFS works.
* [ ] DFS works.

### Documentation

* [ ] README completed.
* [ ] All group members listed.
* [ ] Student IDs included.
* [ ] Individual contributions included.
* [ ] GitHub repository updated.
* [ ] Commit history available.
* [ ] Demonstration video completed.
* [ ] Final project tested.

---

# 🏁 Conclusion

The **University Student Record and Campus Route Management System** demonstrates the practical application of fundamental data structures and algorithms through a single integrated Java console application.

The project combines:

```text
Linked List
     +
Stack
     +
Queue
     +
Binary Search Tree
     +
Hash Table
     +
Graph
     +
BFS / DFS
```

The system demonstrates how these data structures can be applied to practical university management tasks such as student record management, service request processing, searching, campus connectivity, and route traversal.

The project also demonstrates software engineering practices including:

* Modular development
* Data structure implementation
* Input validation
* Automated testing
* Integration
* Git/GitHub collaboration
* Documentation
* Debugging
* Final system verification

---

# 📚 Technologies Used

```text
Programming Language : Java
Development          : Java JDK
Version Control      : Git
Repository           : GitHub
Application Type     : Console Application
Architecture         : Modular / Object-Oriented
```

---

# 📌 Academic Information

**Module:** CIT300 – Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 1
**Project:** University Student Record and Campus Route Management System
**Assessment Weight:** 10%
**Submission Week:** Week 10

---

# 👥 Group Information

| Role                         | Name           | Student ID   |
| ---------------------------- | -------------- | ------------ |
| Member 1                     | `B.Inaamul Hasan` | `23da2-0870` |
| Member 2                     | `Mushna` | `23da2-756` |
| Member 3                     | `Safana` | `23da2-0474` |
| Member 4                     | `Sahama` | `23da2-0707` |
| Lead Developer / Integration | `B.Inaamul Hasan` | `23da2-0870` |

---

## Final Project Status

```text
Student Management       ✓
Linked List              ✓
Stack                    ✓
Queue                    ✓
Binary Search Tree       ✓
Hash Table               ✓
Campus Graph             ✓
BFS                      ✓
DFS                      ✓
Input Validation         ✓
Automated Testing        ✓
System Integration       ✓
Git/GitHub Collaboration ✓
Documentation            ✓
```

**End of README**
