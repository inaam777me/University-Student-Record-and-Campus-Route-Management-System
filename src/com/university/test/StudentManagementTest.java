package com.university.test;

import com.university.datastructures.StudentLinkedList;
import com.university.datastructures.StudentNode;
import com.university.management.StudentManager;
import com.university.model.Student;

/**
 * Automated Verification & Test Suite for Member 1 Component.
 * Validates:
 * 1. Linked list creation and empty state check
 * 2. Adding valid students
 * 3. Enforcing duplicate ID prevention
 * 4. Mark validation (rejecting <0 and >100)
 * 5. Searching for existing and non-existing students
 * 6. Updating records and rejecting invalid update values
 * 7. Deletion of Head, Middle, and Tail nodes
 * 8. Handling missing records gracefully
 * 9. Integration bridge: export to Student[] array for other group members
 */
public class StudentManagementTest {

    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" RUNNING CIT300 MEMBER 1 TEST SUITE ");
        System.out.println("=================================================");

        testInitialState();
        testAddStudents();
        testDuplicateIdRejection();
        testMarkValidation();
        testSearchStudent();
        testUpdateStudent();
        testDeleteOperations();
        testMissingRecordHandling();
        testIntegrationExport();

        System.out.println("\n=================================================");
        System.out.printf(" TEST SUMMARY: %d/%d PASSED (%.1f%%)\n",
                passedTests, totalTests, ((double) passedTests / totalTests) * 100);
        System.out.println("=================================================");

        if (passedTests == totalTests) {
            System.out.println(" ALL CIT300 MEMBER 1 REQUIREMENTS VERIFIED SUCCESSFULLY!");
        } else {
            System.err.println(" SOME TESTS FAILED. PLEASE REVIEW.");
        }
    }

    private static void assertTrue(String testName, boolean condition) {
        totalTests++;
        if (condition) {
            passedTests++;
            System.out.println(" [PASS] " + testName);
        } else {
            System.err.println(" [FAIL] " + testName);
        }
    }

    private static void testInitialState() {
        System.out.println("\n--- 1. Initial State Checks ---");
        StudentLinkedList list = new StudentLinkedList();
        assertTrue("List is initially empty", list.isEmpty());
        assertTrue("List initial size is 0", list.getSize() == 0);
        assertTrue("Head is null initially", list.getHead() == null);
    }

    private static void testAddStudents() {
        System.out.println("\n--- 2. Add Student Operations ---");
        StudentManager manager = new StudentManager();

        boolean added1 = manager.addStudent("S101", "Alice Johnson", "BSc CS", 85.5);
        boolean added2 = manager.addStudent("S102", "Bob Smith", "BEng SE", 72.0);

        assertTrue("Add Student 1 succeeds", added1);
        assertTrue("Add Student 2 succeeds", added2);
        assertTrue("Total count is 2", manager.getTotalStudents() == 2);
        assertTrue("Student S101 exists", manager.studentExists("S101"));
        assertTrue("Student S102 exists", manager.studentExists("S102"));
    }

    private static void testDuplicateIdRejection() {
        System.out.println("\n--- 3. Duplicate Student ID Validation ---");
        StudentManager manager = new StudentManager();
        manager.addStudent("S101", "Alice Johnson", "BSc CS", 85.5);

        // Attempt to add another student with same ID (even with different casing)
        boolean duplicateAdded = manager.addStudent("s101", "Alice Imposter", "BSc IT", 90.0);
        assertTrue("Duplicate Student ID 's101' rejected", !duplicateAdded);
        assertTrue("Total student count remains 1", manager.getTotalStudents() == 1);
    }

    private static void testMarkValidation() {
        System.out.println("\n--- 4. Academic Marks Validation ---");
        StudentManager manager = new StudentManager();

        boolean negativeMark = manager.addStudent("S103", "Test Neg", "BSc CS", -5.0);
        boolean excessiveMark = manager.addStudent("S104", "Test Over", "BSc CS", 105.0);
        boolean zeroMark = manager.addStudent("S105", "Test Zero", "BSc CS", 0.0);
        boolean hundredMark = manager.addStudent("S106", "Test Perfect", "BSc CS", 100.0);

        assertTrue("Negative marks rejected", !negativeMark);
        assertTrue("Marks > 100.0 rejected", !excessiveMark);
        assertTrue("Marks = 0.0 accepted", zeroMark);
        assertTrue("Marks = 100.0 accepted", hundredMark);
    }

    private static void testSearchStudent() {
        System.out.println("\n--- 5. Search Operations ---");
        StudentManager manager = new StudentManager();
        manager.addStudent("S101", "Alice Johnson", "BSc CS", 85.5);

        Student found = manager.searchStudent("S101");
        Student notFound = manager.searchStudent("S999");

        assertTrue("Search returns existing student", found != null && found.getName().equals("Alice Johnson"));
        assertTrue("Search returns null for non-existent student", notFound == null);
        assertTrue("Found student has correct grade 'A'", found != null && "A".equals(found.getGrade()));
    }

    private static void testUpdateStudent() {
        System.out.println("\n--- 6. Update Operations ---");
        StudentManager manager = new StudentManager();
        manager.addStudent("S101", "Alice Johnson", "BSc CS", 85.5);

        boolean updated = manager.updateStudent("S101", "Alice J. Williams", "BSc Data Science", 91.0);
        Student updatedStudent = manager.searchStudent("S101");

        assertTrue("Update operation succeeds", updated);
        assertTrue("Updated name matches", updatedStudent != null && "Alice J. Williams".equals(updatedStudent.getName()));
        assertTrue("Updated programme matches", updatedStudent != null && "BSc Data Science".equals(updatedStudent.getProgramme()));
        assertTrue("Updated marks match", updatedStudent != null && updatedStudent.getMarks() == 91.0);

        // Test update with invalid marks
        boolean invalidUpdate = manager.updateStudent("S101", "Alice", "BSc CS", 120.0);
        assertTrue("Update with invalid mark (120) rejected", !invalidUpdate);
    }

    private static void testDeleteOperations() {
        System.out.println("\n--- 7. Deletion Operations (Head, Middle, Tail) ---");
        StudentLinkedList list = new StudentLinkedList();
        list.addStudent(new Student("S101", "Alice", "BSc CS", 80.0));
        list.addStudent(new Student("S102", "Bob", "BSc CS", 75.0));
        list.addStudent(new Student("S103", "Charlie", "BSc CS", 70.0));
        list.addStudent(new Student("S104", "Diana", "BSc CS", 65.0));

        // Delete Middle Node (S102)
        boolean deleteMiddle = list.deleteStudent("S102");
        assertTrue("Delete middle node (S102) succeeds", deleteMiddle);
        assertTrue("Size decremented to 3", list.getSize() == 3);
        assertTrue("S102 no longer exists", !list.studentExists("S102"));

        // Delete Head Node (S101)
        boolean deleteHead = list.deleteStudent("S101");
        assertTrue("Delete head node (S101) succeeds", deleteHead);
        assertTrue("New head is S103", list.getHead() != null && "S103".equals(list.getHead().getData().getStudentId()));
        assertTrue("Size decremented to 2", list.getSize() == 2);

        // Delete Tail Node (S104)
        boolean deleteTail = list.deleteStudent("S104");
        assertTrue("Delete tail node (S104) succeeds", deleteTail);
        assertTrue("Size decremented to 1", list.getSize() == 1);
        assertTrue("Remaining node is S103", list.getHead() != null && "S103".equals(list.getHead().getData().getStudentId()));
    }

    private static void testMissingRecordHandling() {
        System.out.println("\n--- 8. Missing Record Handling ---");
        StudentManager manager = new StudentManager();
        manager.addStudent("S101", "Alice", "BSc CS", 80.0);

        boolean updateMissing = manager.updateStudent("S999", "Unknown", "None", 50.0);
        boolean deleteMissing = manager.deleteStudent("S999");
        Student searchMissing = manager.searchStudent("S999");

        assertTrue("Update missing record returns false", !updateMissing);
        assertTrue("Delete missing record returns false", !deleteMissing);
        assertTrue("Search missing record returns null", searchMissing == null);
    }

    private static void testIntegrationExport() {
        System.out.println("\n--- 9. Peer Integration Export ---");
        StudentLinkedList list = new StudentLinkedList();
        list.addStudent(new Student("S101", "Alice", "BSc CS", 80.0));
        list.addStudent(new Student("S102", "Bob", "BEng SE", 75.0));

        Student[] array = list.getAllStudents();
        assertTrue("Exported array is non-null", array != null);
        assertTrue("Exported array length matches list size", array.length == 2);
        assertTrue("First element matches S101", array[0].getStudentId().equals("S101"));
        assertTrue("Second element matches S102", array[1].getStudentId().equals("S102"));
    }
}
