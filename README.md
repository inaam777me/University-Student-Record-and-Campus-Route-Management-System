# University Student Record and Campus Route Management System
**Course:** CIT300 Data Structures and Algorithms  
**Components:** Member 1 - Student Records; Member 3 - BST and Hash Table

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
│           │   ├── StudentBinarySearchTree.java # BST ordered by Student ID
│           │   └── StudentHashTable.java   # Separate-chaining hash table
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

### 3. Run the Automated Test Suite:
```bash
java -cp bin com.university.test.StudentManagementTest
```

---

## 🤝 Integration for Group Members
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
