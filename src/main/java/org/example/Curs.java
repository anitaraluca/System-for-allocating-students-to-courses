package org.example;

import java.util.List;
import java.util.ArrayList;
public class Curs<T> {
    private String nume;
    private int capacitateMaxima;
    private List<Student> studentiInrolati;
    private String tipCurs;

    public Curs(String nume, int capacitateMaxima) {
        this.nume = nume;
        this.capacitateMaxima = capacitateMaxima;
        this.studentiInrolati = new ArrayList<>();
    }

    public Curs(String nume, int capacitateMaxima, List<? super Student> studentiInrolati, String tipCurs) {
        this.nume = nume;
        this.capacitateMaxima = capacitateMaxima;
        this.studentiInrolati = (List<Student>) studentiInrolati;
        this.tipCurs = tipCurs;
    }

    public Curs(String nume) {
        this.nume = nume;
    }

    public void enrollStudent(Student student) {
        if (studentiInrolati.size() < capacitateMaxima) {
            studentiInrolati.add(student);
        } else {
            System.out.println("Curs " + nume + " is full. Cannot enroll more students.");
        }
    }

    public int getCapacitateMaxima() {
        return capacitateMaxima;
    }

    public void setCapacitateMaxima(int capacitateMaxima) {
        this.capacitateMaxima = capacitateMaxima;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Student> getStudentiInrolati() {
        return studentiInrolati;
    }

    public String getTipCurs() {
        return tipCurs;
    }

    public void setTipCurs(String tipCurs) {
        this.tipCurs = tipCurs;
    }

    public Curs(String nume, int capacitateMaxima, String tipCurs) {
        this.nume = nume;
        this.capacitateMaxima = capacitateMaxima;
        this.studentiInrolati = studentiInrolati;
        this.tipCurs = tipCurs;
    }
}
