# University Student Record and Campus Route Management System
**Course:** CIT300 Data Structures and Algorithms  
**Component:** Member 1 - Custom Singly Linked List & Student Record Management  

---

## 📌 Project Overview
This module implements the core **Student Record Management** component using a custom **Singly Linked List** built completely from scratch in Java (no `ArrayList` or `java.util.LinkedList`). It manages student entities (ID, Name, Programme, Marks) with robust validation and duplicate checking, and serves as the data foundation for peer components (Stack, Queue, BST/AVL, Hash Table, Graph).

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
│           ├── management/
│           │   └── StudentManager.java     # Service / business logic controller
│           ├── app/
│           │   └── StudentRecordApp.java   # Interactive console UI
│           └── test/
│               └── StudentManagementTest.java # Automated verification test suite
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
```

---

## 🤝 Integration for Group Members
- **Member 2 (Stack & Queue):** Use `manager.getStudentList().getAllStudents()` or pass `Student` objects directly to your stack/queue.
- **Member 3 (BST / AVL Tree):** Call `Student[] students = manager.getStudentList().getAllStudents();` and insert them into your tree for O(log n) lookups and sorted reports.
- **Member 4 (Hashing / Hash Table):** Hash `student.getStudentId()` as the key and store `student` in your buckets for O(1) searches.
- **Member 5 (Graph / Campus Routes):** Associate student records with campus destination nodes using `student.getStudentId()`.
