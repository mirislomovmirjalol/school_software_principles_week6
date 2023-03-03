import java.util.ArrayList;

public class SelectedModuleMenu extends Menu {
    private Module module;
    private KeyboardReader kbr;
    private University university;
    private ArrayList<Student> studentsFromModule;
    private ArrayList<Student> students;

    public SelectedModuleMenu(University university, Module module) {
        kbr = new KeyboardReader();
        this.module = module;
        this.university = university;
        this.students = university.getStudents();
        this.studentsFromModule = module.getStudents();
        runSelectedModuleMenu();
    }

    public void runSelectedModuleMenu() {
        boolean exit = false;

        while (!exit) {
            // clear the console - note won`t work in IDE console.
            // see https://www.javatpoint.com/how-to-clear-screen-in-java for OS independent methods
            System.out.print("\033[H\033[2J");
            System.out.flush();

            displaySelectedModuleMenu();

            int choice = kbr.getInt("Select a menu option", 1, 4);
            switch (choice) {
                case 1:
                    displayStudents();
                    break;
                case 2:
                    addStudentToModule();
                    break;
                case 3:
                    removeStudentFromModule();
                case 4:
                    return;
            }
        }
    }

    private void removeStudentFromModule() {
        if (students.isEmpty()) {
            System.out.println("There are no students to display!!");
        } else {
            System.out.println("Students from this module\n");
            for (int i = 0; i < students.size(); i++) {
                System.out.println("Student " + (i + 1) + " : " + "Name:" + students.get(i).getName());
            }
            int choiceStudent = kbr.getInt("Please select student you want to remove!", 1, students.size());
            Student student = students.get(choiceStudent - 1);
            module.removeStudent(student);

        }
    }

    private void displaySelectedModuleMenu() {
        String[] menuItems = {"Display Students", "Add Student to Module", "Delete student from module", "Back"};
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }
    }

    public void displayStudents() {
        if (studentsFromModule.isEmpty()) {
            System.out.println("There are no students to display!!");
        } else {
            System.out.println("Student Details\n");
            for (int i = 0; i < studentsFromModule.size(); i++) {
                System.out.println("Student " + (i + 1) + " : " + "Name:" + studentsFromModule.get(i).getName());
            }
        }
    }

    public void addStudentToModule() {
        ArrayList<Student> students = university.getStudents();
        if (students.isEmpty()) {
            System.out.println("There are no students to display!!");
        } else {
            System.out.println("Student Details\n");
            for (int i = 0; i < students.size(); i++) {
                System.out.println("Student " + (i + 1) + " : " + "Name:" + students.get(i).getName());
            }
            int choiceStudent = kbr.getInt("Please select student you want!", 1, students.size());
            Student student = students.get(choiceStudent - 1);
            module.addStudent(student);
        }
    }

}
