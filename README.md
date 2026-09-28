# University Student Record and Campus Route Management System
**Course:** CIT300 Data Structures and Algorithms  
<<<<<<< HEAD
**Components:** Member 1 - Student Records; Member 2 - Action Stack & Service Request Queue  
=======
**Components:** Member 1 - Student Records; Member 3 - BST and Hash Table
>>>>>>> c9deb9d83f67fb0780d7f34c31810618521bc8e1

---

## 📌 Project Overview
The project implements student records with a custom singly linked list and provides a custom linked-node stack for recent actions plus a custom FIFO queue for student service requests. The stack and queue do not use Java's built-in collection implementations.

---

## 📂 Project Structure
```
University Student Record and Campus Route Management System/
├── bin/                                    # Compiled Java bytecode (.class files)
├── src/
│   └── com/
│       └── university/
│           ├── model/
│           │   └── Student.java            # Student entity (ID, Name, Programme, Marks, Grade)
│           ├── datastructures/
│           │   ├── StudentNode.java        # Singly linked list node
│           │   └── StudentLinkedList.java  # Custom linked list with add, update, delete, search
<<<<<<< HEAD
│           │   ├── ActionStack.java        # Custom LIFO history stack
│           │   └── ServiceRequestQueue.java # Custom FIFO pending-request queue
=======
│           │   ├── StudentBinarySearchTree.java # BST ordered by Student ID
│           │   └── StudentHashTable.java   # Separate-chaining hash table
>>>>>>> c9deb9d83f67fb0780d7f34c31810618521bc8e1
│           ├── management/
│           │   └── StudentManager.java     # Service / business logic controller
│           │   └── StudentServiceManager.java # Student-aware service request operations
│           ├── model/
│           │   ├── Action.java             # History entry
│           │   └── ServiceRequest.java      # Validated service request
│           ├── app/
│           │   └── StudentRecordApp.java   # Interactive console UI
│           └── test/
│               ├── StudentManagementTest.java # Member 1 tests
│               └── Member2ServicesTest.java   # Stack, queue, and integration tests
└── README.md
```

---

## 🚀 How to Compile & Run

### 1. Compile All Files:
```bash
javac -d bin src/com/university/model/*.java src/com/university/datastructures/*.java src/com/university/management/*.java src/com/university/app/*.java src/com/university/test/*.java
```

### 2. Run the Interactive Console Application:
```bash
java -cp bin com.university.app.StudentRecordApp
```

### 3. Run the Automated Test Suite:
```bash
java -cp bin com.university.test.StudentManagementTest
java -cp bin com.university.test.Member2ServicesTest
```

---

## 🤝 Integration for Group Members
<<<<<<< HEAD
- **Member 2 (Stack & Queue):** `StudentManager` records successful add, update, and delete operations in `getActionStack()`. Construct `StudentServiceManager` with the shared manager to validate student IDs, enqueue requests, process them FIFO, and record processing actions. Its queue and history stack are also available through `getPendingRequestCount()`, `displayPendingRequests()`, and `getActionStack()`.
- **Member 3 (BST / AVL Tree):** Call `Student[] students = manager.getStudentList().getAllStudents();` and insert them into your tree for O(log n) lookups and sorted reports.
- **Member 4 (Hashing / Hash Table):** Hash `student.getStudentId()` as the key and store `student` in your buckets for O(1) searches.
- **Member 5 (Graph / Campus Routes):** Associate student records with campus destination nodes using `student.getStudentId()`.

### Member 2 Integration Example
```java
StudentManager manager = new StudentManager();
StudentServiceManager services = new StudentServiceManager(manager);

manager.addStudent("S201", "Ada Student", "BSc Computing", 85.0);
services.addServiceRequest("R1", "S201", "Request an academic transcript");
services.displayPendingRequests();
services.processNextRequest();
manager.getActionStack().display();
```

Request fields must be non-empty, the student must exist, and a request ID cannot duplicate another pending request (case-insensitive). Empty `pop`, `peek`, and `dequeue` operations return `null`.
=======
- **Member 2 (Stack & Queue):** Use `manager.getStudentList().getAllStudents()` or pass `Student` objects directly to your stack/queue.
- **Member 3 (BST and Hash Table):** `StudentManager` owns both custom structures and keeps them synchronized with the linked list. Use `manager.getStudentTree().search("S101")`, `manager.getStudentTree().delete("S101")`, `manager.displayStudentsInorder()`, or `manager.searchStudentByHashing("S101")`. The constructor accepting an existing list indexes its current students.
- **Member 5 (Graph / Campus Routes):** Associate student records with campus destination nodes using `student.getStudentId()`.

## Member 3: BST and Hash Table

The BST compares IDs case-insensitively. Search starts at the root, compares the requested ID with the current node, and follows only the left subtree for a smaller ID or the right subtree for a larger ID. It stops on a match or an empty child, taking O(h) time where h is tree height; an unbalanced tree can reach O(n), while a balanced tree is O(log n). Inorder traversal visits left, node, then right, so IDs display in sorted order.

The hash table uses a fixed array of 11 buckets and separate chaining. A normalized, case-insensitive ID hash selects the bucket; insert and search scan only that bucket's linked chain. IDs that map to the same bucket coexist in that chain, so collisions do not overwrite records. Average lookup is O(1) at a reasonable load, with O(n) worst case.

Student IDs must be non-null and non-blank; duplicate IDs are rejected case-insensitively. Missing searches return `null`, missing deletions return `false`, and empty traversals/displays report that the structure is empty. Add and delete through `StudentManager` to keep the list, BST, and hash table synchronized. Updates to a student's name, programme, or marks are visible in both indexes because all structures reference the same `Student` object.

The console menu provides option 8 to display BST inorder and option 9 to search by hash table; option 10 exits. Example:

```text
Enter your choice (1-10): 8
Student[ID=S101, Name=Alice Johnson, Programme=BSc Computer Science, Marks=88.50, Grade=A]
Student[ID=S102, Name=Bob Smith, Programme=BEng Software Engineering, Marks=74.00, Grade=B]

Enter your choice (1-10): 9
Enter Student ID to search: S102
[Hash Search Result] Student[ID=S102, Name=Bob Smith, Programme=BEng Software Engineering, Marks=74.00, Grade=B]
```

The automated suite covers tree insertion/search/deletion (including a two-child delete), case-insensitive duplicates, collision-chain operations, missing/blank IDs, and manager synchronization.

Suggested commit message: `Add synchronized student BST and hash table`
>>>>>>> c9deb9d83f67fb0780d7f34c31810618521bc8e1
