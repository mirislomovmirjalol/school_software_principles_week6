import java.io.*;
import java.util.ArrayList;

public class Module {
    private String filePath = "src/students_in_module.csv";
    private String title;
    private Lecturer lecturer;
    private ArrayList<Student> students;
    private University university;

    public Module(String title, Lecturer lecturer, University university) {
        setTitle(title);
        setLecturer(lecturer);
        students = new ArrayList<Student>();
        setUniversity(university);
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public ArrayList<Student> getStudents() {
        restoreFromFile();
        return students;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath));) {
            for (Student student : students)
                writer.println(student + "," + student.getAllDetail());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
            String line = null;
            students.clear();
            while ((line = reader.readLine()) != null) {
                makeStudents(line);
            }
        } catch (Exception ex) {
            System.out.println("could not read the file");
            ex.printStackTrace();
        }
    }

    public void makeStudents(String line) {
        String[] result = line.split(",");
        int start = result[0].indexOf('.') + 1;
        int end = result[0].indexOf('@');

        String className = new String(result[0].substring(start, end));
        Student student = null;

        if (className.equals("Student")) {
            student = new Student(result[1], result[2], result[3]);
        } else if (className.equals("Lecturer")) {
            student = new Student(result[1], result[2], result[3]);
        }
        if (student != null) {
            students.add(student);
        }
    }
}
