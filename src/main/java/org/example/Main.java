package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <testName>");
            return;
        }

        String testName = args[0];
        String inputFilePath = "src/main/resources/" + testName + "/" + testName + ".in";
        String outputFilePath = "src/main/resources/" + testName + "/" + testName + ".out";
        String noteFilePath = "src/main/resources/" + testName + "/" + "note_1.txt";
        String noteFilePath2 = "src/main/resources/" + testName + "/" + "note_2.txt";
        String noteFilePath3 = "src/main/resources/" + testName + "/" + "note_3.txt";

        List<Student> students = new ArrayList<>();
        List<Curs<?>> courses = new ArrayList<>();
        Secretariat secretariat =  new Secretariat();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
             FileWriter writer = new FileWriter(outputFilePath, true)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" - ");
                String action = tokens[0];
                if(action.equals("adauga_student")) {
                    String programStudii = tokens[1];
                    String numeStudent = tokens[2];
                    Student student = new Student(numeStudent);
                    student.setProgramStudii(programStudii);
                    student.addProgramStudii(programStudii);
                    secretariat.addStudents(numeStudent);
                }
                else if(action.equals("citeste_mediile")) {
                    readAvg(noteFilePath, students, outputFilePath);
                    readAvg(noteFilePath2, students, outputFilePath);
                    readAvg(noteFilePath3, students, outputFilePath);
                }
                else if(action.equals("posteaza_mediile")) {
                    secretariat.sortStudentsByAverage(students, outputFilePath);
                }
                else if(action.equals("contestatie")) {
                    double mediaNoua = Double.parseDouble(tokens[2]);
                    for(Student student : students) {
                        if(student.getNume().equals(tokens[1])) {
                            student.setAverage(mediaNoua);
                        }
                    }
                }
                else if(action.equals("adauga_curs")) {
                    String numeCurs = tokens[2];
                    int capacitateMaxima = Integer.parseInt(tokens[3]);
                    Curs<Student> curs = new Curs<>(numeCurs, capacitateMaxima);
                    courses.add(curs);
                    secretariat.addCourse(curs);
                    curs.setNume(numeCurs);
                    curs.setTipCurs(tokens[1]);
                    curs.setCapacitateMaxima(capacitateMaxima);
                }
                else if(action.equals("adauga_preferinte")) {
                    String numeStudent = tokens[1];
                    for(Student student : students) {
                        if(student.getNume().equals(tokens[1])) {
                            student.setNume(tokens[1]);
                            //writer.write(student.getNume() + " " + student.getAverage() + " ");
                            List<Curs<?>> noiPreferinte = new ArrayList<>();
                            for(int i = 2; i < tokens.length; i++) {
                                Curs<?> curs = new Curs<>(tokens[i]);
                                //writer.write(curs.getNume() + "\n");
                                student.addPreferinta(curs);
                                noiPreferinte.add(curs);
                            }
                            student.setPreferinte(noiPreferinte);
                        }
                    }
                }
                else if(action.equals("repartizeaza")) {
                    for (Curs<?> curs : courses) {
                        int locuriDisponibile = curs.getCapacitateMaxima();
                        List<Student> studentiInscrisi = curs.getStudentiInrolati();
                        for(Student student: studentiInscrisi) {
                            writer.write(student.getNume());
                        }
                        Collections.sort(studentiInscrisi, Comparator.comparingDouble(Student::getAverage).reversed());// Sortează studenții în ordinea descrescătoare a mediilor

                        int locuriOcupate = 0;
                        for (Student student : studentiInscrisi) { // Repartizează studenții în funcție de locurile disponibile și preferințele lor
                            if (locuriOcupate < locuriDisponibile && student.getPreferinte().contains(curs)) {
                                curs.enrollStudent(student);
                                student.setCursInscris(curs);
                                locuriOcupate++;
                            }
                        }
                    }
                }
                else if (action.equals("posteaza_curs")) {
                    String numeCurs = tokens[1];
                    writer.write("***\n");

                    for (Curs<?> curs : courses) {
                        if (curs.getNume().equals(numeCurs)) {
                            writer.write(curs.getNume() + " (" + curs.getCapacitateMaxima() + ")\n");

                            int locuriOcupate = 0;
                            Set<Student> studentiInrolatiLaCurs = new HashSet<>(); // Folosim un set pentru a stoca studenții înrolați la curs
                            List<Student> studentiInrolatiOrdineCrescatoare = new ArrayList<>(); // Listă separată pentru stocarea și afișarea în ordine alfabetică

                            for (Student student : students) {
                                if (!student.isRepartizat()) { // Verificăm dacă studentul nu a fost deja repartizat
                                    for (Curs<?> preferinta : student.getPreferinte()) {
                                        if (!studentiInrolatiLaCurs.contains(student) && preferinta.getNume().equals(tokens[1]) && locuriOcupate < curs.getCapacitateMaxima()) { // Verific dacă studentul nu a fost deja înrolat la alt curs
                                            curs.enrollStudent(student); // Adaug studentul în lista de studenți înrolați la curs
                                            student.setCursInscris(curs);
                                            locuriOcupate++;
                                            studentiInrolatiLaCurs.add(student);
                                            student.setRepartizat(true); // Setez câmpul de repartizare la true pentru student
                                            studentiInrolatiOrdineCrescatoare.add(student); // Adaugăm studentul la lista separată pentru ordine alfabetică
                                            break;
                                        }
                                    }
                                }
                            }

                            Collections.sort(studentiInrolatiOrdineCrescatoare, Comparator.comparing(Student::getNume));  // Sortez lista separată în ordine alfabetică

                            for (Student student : studentiInrolatiOrdineCrescatoare) { // Afișez studenții în ordine alfabetică
                                writer.write(student.getNume() + " - " + student.getAverage() + "\n");
                            }
                        }
                    }
                }
                else if (action.equals("posteaza_student")) {
                    writer.write("***\n");
                    for (Student student : students) {
                        if (student.getNume().equals(tokens[1])) {
                            if(student.getCursInscris().getTipCurs().equals("licenta")){
                                writer.write("Student Licenta: " + student.getNume() + " - " + student.getAverage() + " - " + student.getCursInscris().getNume() + "\n");
                            }
                            else if(student.getCursInscris().getTipCurs().equals("master")) {
                                writer.write("Student Master: " + student.getNume() + " - " + student.getAverage() + " - " + student.getCursInscris().getNume() + "\n");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void readAvg(String noteFilePath, List<Student> students, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(noteFilePath));
             FileWriter writer = new FileWriter(outputFilePath, true)) {

            Secretariat secretariat = new Secretariat();

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String result = avgCommand(secretariat, line);
                } catch (DuplicateStudentException e) {
                    writer.write("Student duplicat: " + e.getStudentName() + "\n");
                }
            }

            students.addAll(secretariat.getStudents()); //adaug studentii in lista

            System.out.println("Average grades processed successfully. Results written to: " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Error processing average grades: " + e.getMessage());
        }
    }
    private static String avgCommand(Secretariat secretariat, String command) throws DuplicateStudentException {
        String[] parts = command.split(" - ");
        if (parts.length == 2) {
            String numeStudent = parts[0];
            String nota = parts[1];
            double medie = Double.parseDouble(nota);
            Student student = new Student(numeStudent);
            secretariat.addStudent(student);
            secretariat.addStudentGrade(numeStudent, medie);

            return numeStudent + " - " + medie;
        } else {
            return "Format invalid: " + command;
        }
    }
}