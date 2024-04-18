# Implemenatrea temei

## Clase
Am creat clasele : 
* Student
* Curs
* Licenta
* Master
* Secretariat

In **Student** am folosit capurile nume, average, preferinte, repartizat, programStudii, CursInscris, pentru care am implementat costructori, getters si setters. In clasa **Curs** am folosit parametrii nume, capacitateMaxima, o lista a studintilor inrolari si tipul de curs, pentru care am implementat constructori, metoda enrollStudents ce repartizeaza un student la un curs, getters si setters pentru parametrii. In **Secretariat** am folosit private List<Student> students: O listă care reține obiectele de tip Student, adică informații despre studenți.
private List<Curs<? extends Student>> courses: O listă care stochează obiectele de tip Curs, reprezentând cursurile oferite, cu posibilitatea ca acestea să fie de tip generic extins la clasa Student, un costructructor public Secretariat(), care inițializează listele de studenți și cursuri. De asemenea am implementat metodele public List<Student> getStudentsByAverage(): Returnează lista de studenți ordonați după medii.

* public List<Student> getStudents(), care returnează lista de studenți.
* public void addCourse(Curs<Student> curs), care adaugă un curs în lista de cursuri a secretariatului.
* public void addStudent(Student student), aceasta adaugă un student în lista de studenți a secretariatului.
* public void addPreferinta(Student student, Curs<?> curs), aceasta adaugă o preferință de curs pentru un student.
* public void addStudentGrade(String studentName, double grade), care adaugă o notă pentru un student identificat după nume.
* public void addStudentProgram(String studentName, String program), metoda adaugă informații despre programul de studii pentru un student identificat după nume.
* public Student findStudentByName(String studentName), care caută un student în listă după nume.
* public void sortStudentsByAverage(List<Student> students, String outputFilePath), ea sortează lista de studenți după medii și scrie rezultatele într-un fișier specificat.
* void addStudents(String numeStudent), care adaugă un student în lista de studenți a secretariatului.

## Main
Am implementat funtionalitatea pentru comenzile:

* adauga_student
* adauga_curs
* contestatie
* citeste_mediile
* posteaza_mediile
* posteaza_curs
* repartizeaza
* posteaza_student
* adauga_preferinte

In cadrul clasei **Main** citesc din fisierul input comenzile, daca intalnesc comanda citeste_mediile citesc din fisiere note si toate output-urile le scriu in fisierul de output, iar functiile de readAvg si avgCommand sunt pentru citirea si procesarea mediilor. Metoda readAvg este folosita pentru a citi mediile si pentru a verifica daca un student este dublicat, in vreme ce avgCommand seteaza notele pentru fiecare student