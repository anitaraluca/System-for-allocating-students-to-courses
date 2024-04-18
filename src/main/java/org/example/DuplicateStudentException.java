package org.example;

public class DuplicateStudentException extends Exception {
    private String studentName;

    public DuplicateStudentException(String studentName) {
        super("Duplicate student: " + studentName);
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }
}
