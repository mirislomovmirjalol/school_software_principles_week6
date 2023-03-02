public class Menu {
    private KeyboardReader kbr;
    private University university;

    public Menu() {
        kbr = new KeyboardReader();
        university = new University("University of the People");
        university.restoreFromFile();
    }

    public void runMenu() {
        boolean exit = false;

        while (!exit) {
            // clear the console - note won`t work in IDE console.
            // see https://www.javatpoint.com/how-to-clear-screen-in-java for OS independent methods
            System.out.print("\033[H\033[2J");
            System.out.flush();

            displayMenu();

            int choice = kbr.getInt("Select a menu option", 1, 5);
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addLecturer();
                    break;
                case 3:
                    displayPeople();
                    break;
                case 4:
                    deletePerson();
                    break;
                case 5:
                    university.saveToFile();
                    exit = true;
                    break;
            }
        }
    }

    public void displayMenu() {
        String[] menuItems = {"Add Student", "Add Lecturer", "Display All", "Delete person", "Exit"};
        System.out.println("\n\nUniversity of the People");
        System.out.println("Main Menu");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }
    }

    public void addStudent() {
        String name = kbr.getString("Enter student name");
        String dob = kbr.getString("Enter student date of birth");
        String id = kbr.getString("Enter student ID");
        Student student = new Student(name, dob, id);
        university.addPerson(student);
    }

    public void addLecturer() {
        String name = kbr.getString("Enter lecturer name");
        String dob = kbr.getString("Enter lecturer date of birth");
        String phoneNumber = kbr.getString("Enter lecturer phone number");
        Lecturer lecturer = new Lecturer(name, dob, phoneNumber);
        university.addPerson(lecturer);
    }

    public void deletePerson() {
        if (university.getPeople().isEmpty()) {
            System.out.println("There are no people to delete!!");
            return;
        }

        System.out.println("People Details\n");
        for (int i = 0; i < university.getPeople().size(); i++) {
            System.out.println((i + 1) + " : " + university.getPeople().get(i).getAllDetail());
        }
        int choice = kbr.getInt("Select a person to delete", 1, university.getPeople().size());
        System.out.println("\n\n" + university.getPeople().get(choice - 1).getName() + " is  deleted successfully!");
        university.removePerson(university.getPeople().get(choice - 1));
        kbr.getString("Press enter to continue");
    }

    public void displayPeople() {
        for (Person person : university.getPeople()) {
            System.out.println(person.getAllDetail());
        }
        kbr.getString("Press enter to continue");
    }
}
