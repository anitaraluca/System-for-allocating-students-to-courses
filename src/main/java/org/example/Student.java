package org.example;

import java.util.List;
import java.util.*;
import java.io.*;

public class Student {
    private String nume;
    private double average;
    private List<Curs<?>> preferinte;
    private boolean repartizat;
    private String programStudii;
    private Curs<?> cursInscris;

    public Curs<?> getCursInscris() {
        return cursInscris;
    }

    public void setCursInscris(Curs<?> cursInscris) {
        this.cursInscris = cursInscris;
    }

    public String getProgramStudii() {
        return programStudii;
    }

    public void setProgramStudii(String programStudii) {
        this.programStudii = programStudii;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Student(String nume) {
        this.nume = nume;
        this.preferinte = new ArrayList<>();
        this.cursInscris = null;
    }

    public Student(String nume, String programStudii) {
        this.nume = nume;
        this.programStudii = programStudii;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public List<Curs<?>> getPreferinte() {
        return preferinte;
    }

    public void setPreferinte(List<Curs<?>> preferinte) {
        this.preferinte = preferinte;
    }
    public void addPreferinta(Curs<?> curs) {
        if (preferinte == null) {
            preferinte = new ArrayList<>();
        }
        preferinte.add(curs);
        //curs.enrollStudent(this);
    }
    public boolean isRepartizat() {
        return repartizat;
    }

    public void setRepartizat(boolean repartizat) {
        this.repartizat = repartizat;
    }
    public void addProgramStudii(String programStudii) {
        this.programStudii = programStudii;
    }
}
