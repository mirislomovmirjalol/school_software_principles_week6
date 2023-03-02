import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class University {

    private String filePath = "src/data.csv";
    private ArrayList<Person> people;
    private String name;

    public University(String name) {
        setName(name);
        people = new ArrayList<Person>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void removePerson(Person person) {
        people.remove(person);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath));) {
            writer.println(name);
            for (Person person : people)
                writer.println(person + "," + person.getAllDetail());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
            String line = null;
            // First line should be the University's name
            name = reader.readLine();
            // ensure we don't have any people at the moment
            people.clear();
            while ((line = reader.readLine()) != null) {
                makePerson(line);
            }
        } catch (Exception ex) {
            System.out.println("could not read the file");
            ex.printStackTrace();
        }
    }

    private void makePerson(String line) {
        String[] result = line.split(",");
        int start = result[0].indexOf('.') + 1;
        int end = result[0].indexOf('@');

        String className = new String(result[0].substring(start, end));
        Person person = null;

        if (className.equals("Student")) {
            person = new Student(result[1], result[2], result[3]);
        } else if (className.equals("Lecturer")) {
            person = new Lecturer(result[1], result[2], result[3]);
        }
        if (person != null) {
            people.add(person);
        }

    }
}
