# University Student Record and Campus Route Management System
**Course:** CIT300 Data Structures and Algorithms  
**Project Role:** Lead Developer Integration (Members 1, 2, 3, and 4)

---

## 📌 Project Overview
The **University Student Record and Campus Route Management System** is a unified, high-performance Java console application integrating seven foundational computer science data structures into a single cohesive system:

1. **Custom Singly Linked List** (Member 1) — Primary dynamic sequential storage for student records.
2. **Custom Linked Stack (LIFO)** (Member 2) — Audit history log tracking recent system mutations (Add, Update, Delete, Process Request).
3. **Custom Linked Queue (FIFO)** (Member 2) — Student service request processing queue ensuring fair first-come, first-served execution.
4. **Binary Search Tree (BST)** (Member 3) — Natural ordering by unique Student ID with $O(\log n)$ average search and in-order sorted reporting.
5. **Separate-Chaining Hash Table** (Member 3) — Fixed-bucket hash map with linked collision resolution providing near $O(1)$ student record lookup.
6. **Custom Undirected Campus Graph** (Member 4) — Adjacency list representation of campus location vertices and bidirectional road edges.
7. **Graph Traversals: BFS & DFS** (Member 4) — Breadth-First Search (level-by-level shortest reach) and Depth-First Search (deep exploration) for campus navigation.

> [!IMPORTANT]
> Zero reliance on Java's built-in collections framework (`ArrayList`, `LinkedList`, `HashMap`, `TreeMap`, `Stack`, `Queue`). All nodes, pointers, and memory links are implemented completely from scratch.

---

## 📂 Project Structure
```
University Student Record and Campus Route Management System/
├── bin/                                            # Compiled Java bytecode (.class files)
├── src/
│   └── com/
│       └── university/
│           ├── model/
│           │   ├── Student.java                    # Core entity (ID, Name, Programme, Marks, Grade)
│           │   ├── Action.java                     # LIFO audit history entry
│           │   └── ServiceRequest.java             # Validated service request item
│           ├── datastructures/
│           │   ├── StudentNode.java                # Singly linked list node
│           │   ├── StudentLinkedList.java          # Custom linked list with CRUD operations
│           │   ├── ActionStack.java                # Custom linked LIFO stack
│           │   ├── ServiceRequestQueue.java        # Custom linked FIFO queue
│           │   ├── StudentBinarySearchTree.java    # ID-ordered binary search tree
│           │   ├── StudentHashTable.java           # Separate-chaining hash table (11 buckets)
│           │   ├── CampusLocationNode.java         # Graph location vertex node
│           │   ├── GraphEdgeNode.java              # Adjacency list edge node
│           │   └── CampusGraph.java                # Graph structure with roads, BFS, and DFS
│           ├── management/
│           │   ├── StudentManager.java             # Multi-structure business logic & synchronization controller
│           │   └── StudentServiceManager.java      # Student-aware FIFO service request manager
│           ├── app/
│           │   ├── Main.java                       # Master integrated console application (16 options)
│           │   └── StudentRecordApp.java           # Standalone Member 1 console UI
│           └── test/
│               ├── StudentManagementTest.java      # Member 1 & 3 automated verification (65 tests)
│               ├── Member2ServicesTest.java        # Member 2 Stack & Queue verification (28 tests)
│               └── CampusGraphTest.java            # Member 4 Campus Graph verification (50 tests)
└── README.md
```

---

## 🔄 Multi-Structure Synchronization Architecture

```
                                  [Incoming User Operation]
                                              │
                                              ▼
                                    ┌───────────────────┐
                                    │  StudentManager   │
                                    └─────────┬─────────┘
                                              │
            ┌──────────────────┬──────────────┴───────────────┬──────────────────┐
            ▼                  ▼                              ▼                  ▼
   ┌─────────────────┐ ┌───────────────┐              ┌───────────────┐ ┌─────────────────┐
   │StudentLinkedList│ │  StudentBST   │              │StudentHashTable│ │   ActionStack   │
   ├─────────────────┤ ├───────────────┤              ├───────────────┤ ├─────────────────┤
   │ addStudent()    │ │ insert()      │              │ insert()      │ │ push(Action)    │
   │ updateStudent() │ │ (Shared Ref)  │              │ (Shared Ref)  │ │ push(Action)    │
   │ deleteStudent() │ │ delete()      │              │ delete()      │ │ push(Action)    │
   └─────────────────┘ └───────────────┘              └───────────────┘ └─────────────────┘
```

1. **Add Student**: Appends to `StudentLinkedList`, inserts into `StudentBST`, indexes into `StudentHashTable`, and pushes a `"STUDENT_ADDED"` audit log to `ActionStack`.
2. **Update Student**: Mutates the shared `Student` entity in-place. Because the BST and Hash Table hold identical object references, updates to Name, Programme, and Marks are instantly reflected in all structures without rebuilding trees or hash buckets. Pushes `"STUDENT_UPDATED"` to `ActionStack`.
3. **Delete Student**: Unlinks node from `StudentLinkedList`, removes and rebalances node in `StudentBST`, removes entry from `StudentHashTable` bucket chain, and pushes `"STUDENT_DELETED"` to `ActionStack`.
4. **Service Request Pipeline**: Verifies that the Student ID exists in the system, enqueues to `ServiceRequestQueue`, and on `processNextRequest()`, dequeues in FIFO order and pushes `"SERVICE_REQUEST_PROCESSED"` to `ActionStack`.

---

## 🚀 How to Compile & Run

### 1. Compile All Components:
```bash
# Windows PowerShell
javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })

# Linux / macOS / Bash
javac -d bin $(find src -name "*.java")
```

### 2. Run the Integrated Master Application:
```bash
java -cp bin com.university.app.Main
```

### 3. Run All Automated Test Suites (143 Tests):
```bash
# Member 1 & 3: Linked List, BST, Hash Table, Synchronization (65 tests)
java -cp bin com.university.test.StudentManagementTest

# Member 2: Action Stack & Service Request Queue (28 tests)
java -cp bin com.university.test.Member2ServicesTest

# Member 4: Campus Graph, Adjacency List, Roads, BFS & DFS (50 tests)
java -cp bin com.university.test.CampusGraphTest
```

---

## 📋 Master Console Menu (16 Unified Options)

```text
=================================================================
  UNIVERSITY STUDENT RECORD AND CAMPUS ROUTE MANAGEMENT SYSTEM  
                CIT300 Data Structures and Algorithms            
=================================================================
   1. Add Student Record
   2. Update Student Record
   3. Delete Student Record
   4. Display All Records using Linked List
   5. Add Service Request to Queue
   6. Process Next Service Request
   7. Display Recent Actions using Stack
   8. Display Students using BST
   9. Search Student using Hashing
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

## 🛡️ Defensive Input Validation Matrix

| Input Category | Constraint Enforced | Defensive Handling |
| :--- | :--- | :--- |
| **Student ID** | Non-null, non-blank, globally unique | Case-insensitive duplicate check before insertion; clean warning |
| **Student Name & Programme** | Non-null, non-empty, non-whitespace | Re-prompts in UI; returns `false` in service logic |
| **Academic Marks** | Double in range $[0.0, 100.0]$ | Validated via `Student.isValidMarks(m)`; catches `NumberFormatException` |
| **Service Request** | Student ID must exist; Request ID unique | Validates student before enqueueing; prevents duplicate pending IDs |
| **Empty Data Structures** | Operations executed when empty | Displays user-friendly notice without throwing `NullPointerException` |
| **Campus Locations** | Unique location name; non-empty | Case-insensitive duplicate rejection; preserves existing graph |
| **Campus Roads** | Undirected; no self-loops; vertices exist | Verifies both endpoints exist; prevents duplicate or reflexive roads |
| **BFS / DFS Start Node** | Must match existing location vertex | Validates vertex existence prior to starting queue/recursion |

---

## 👥 Group Member Contributions & Component Allocation
- **Member 1**: `Student`, `StudentNode`, `StudentLinkedList`, Student CRUD logic, input validation.
- **Member 2**: `Action`, `ServiceRequest`, `ActionStack` (LIFO), `ServiceRequestQueue` (FIFO), `StudentServiceManager`.
- **Member 3**: `StudentBinarySearchTree` (ID ordering, deletion, inorder display), `StudentHashTable` (Separate chaining, 11 buckets).
- **Member 4**: `CampusLocationNode`, `GraphEdgeNode`, `CampusGraph` (Adjacency list, bidirectional roads, BFS, DFS).
- **Lead Developer**: Master `Main.java` integration, multi-structure synchronization, git conflict resolution, testing.
