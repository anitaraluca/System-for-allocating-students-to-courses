package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
public class Secretariat{
    private List<Student> students;
    private List<Curs<? extends Student>> courses;
    private List<Student> studentsByAverage = new ArrayList<>();
    public Secretariat() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }
    public List<Student> getStudents() {
        return students;
    }
    public void addCourse(Curs<Student> curs) {
        courses.add(curs);
    }
    public void addStudent(Student student){
        students.add(student);
    }
    public void addStudentGrade(String studentName, double grade) {
        Student student = findStudentByName(studentName);
        if (student != null) {
            student.setAverage(grade);
        }
    }
    public Student findStudentByName(String studentName) {
        for (Student student : students) {
            if (student.getNume().equals(studentName)) {
                return student;
            }
        }
        return null;
    }
    void sortStudentsByAverage(List<Student> students, String outputFilePath) {
        Collections.sort(students, Comparator.comparing(Student::getNume));
        Collections.sort(students, Comparator.comparingDouble(Student::getAverage).reversed());
        try (FileWriter writer = new FileWriter(outputFilePath, true)) {
            writer.write("***\n");
            for (Student student : students) {
                writer.write(student.getNume() + " - " + student.getAverage() + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error sorting and writing students: " + e.getMessage());
        }
    }
    void addStudents(String numeStudent){
        Secretariat secretariat = new Secretariat();
        Student student = new Student(numeStudent);
        secretariat.addStudent(student);
    }
}
