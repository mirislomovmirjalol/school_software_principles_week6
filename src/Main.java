public class Main {

    public static void main(String[] args) {
        University university = new University("University of the People");

        university.addPerson(new Student("Fred", "1/1/1", "234567/1"));
        university.addPerson(new Lecturer("Nigel", "5/5/1995", "+44 1223 245345"));
        university.addPerson(new Student("Wilma", "2/2/2", "345678/1"));
        university.addPerson(new Student("Bambam", "3/3/3", "456789/1"));

        for (Person person : university.getPeople()) {
            System.out.println(person.getAllDetail());
        }
        university.saveToFile();

        System.out.println("##############################################");
        University newUniversity = new University("New University");
        newUniversity.restoreFromFile();
        for (Person person : newUniversity.getPeople()) {
            System.out.println(person.getAllDetail());
        }
    }
}