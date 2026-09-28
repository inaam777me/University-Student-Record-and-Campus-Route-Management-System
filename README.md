# University Student Record and Campus Route Management System
**Course:** CIT300 Data Structures and Algorithms  
**Components:** Member 1 - Student Records; Member 2 - Action Stack & Service Request Queue  

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
│           │   ├── ActionStack.java        # Custom LIFO history stack
│           │   └── ServiceRequestQueue.java # Custom FIFO pending-request queue
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

### 3. Run the Automated Test Suite (100% Pass Verification):
```bash
java -cp bin com.university.test.StudentManagementTest
java -cp bin com.university.test.Member2ServicesTest
```

---

## 🤝 Integration for Group Members
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
