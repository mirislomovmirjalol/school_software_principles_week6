import java.io.*;
import java.util.ArrayList;

public class ModuleMenu extends Menu {
    private KeyboardReader kbr;
    private ArrayList<Module> modules;
    private University university;
    private String filePath = "src/modules.csv";

    public ModuleMenu(University university) {
        this.university = university;
        kbr = new KeyboardReader();
        modules = university.getModules();
        this.runModuleMenu();
    }

    public void displayMenu() {
        String[] menuItems = {"Select module", "Display modules", "Add Module", "Delete Module", "Edit Module", "Exit"};
        System.out.println("\n\n" + university.getName());
        System.out.println("Module Menu");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }
    }

    public void runModuleMenu() {
        boolean exit = false;

        while (!exit) {
            // clear the console - note won`t work in IDE console.
            // see https://www.javatpoint.com/how-to-clear-screen-in-java for OS independent methods
            System.out.print("\033[H\033[2J");
            System.out.flush();

            displayModulesMenu();

            int choice = kbr.getInt("Select a menu option", 1, 4);
            switch (choice) {
                case 1:
                    selectModule();
                    break;
                case 2:
                    displayModule();
                    break;
                case 3:
                    addModule();
                    break;
                case 4:
                    return;
            }
        }
    }

    private void displayModulesMenu() {
        String[] menuItems = {"Select module", "Display modules", "Add Module", "Back"};
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }
    }

    public void selectModule() {
        restoreModulesFromFile();
        if (modules.isEmpty()) {
            System.out.println("There are no modules to display!!");
        } else {
            System.out.println("Module Details\n");
            for (int i = 0; i < modules.size(); i++) {
                System.out.println("Module " + (i + 1) + " : " + "Name:" + modules.get(i).getTitle());
            }
            int choiceModule = kbr.getInt("Please select module you want!", 1, (int) modules.size());
            Module module = modules.get(choiceModule - 1);
            System.out.println(module.getTitle() + " selected");
            SelectedModuleMenu selectedModuleMenu = new SelectedModuleMenu(this.university, module);
        }
    }

    public void addModule() {
        String title = kbr.getString("Enter module title");
        ArrayList<Lecturer> lecturers = university.getLecturers();
        for (int i = 0; i < lecturers.size(); i++) {
            System.out.println((i + 1) + " : " + "Name:" + lecturers.get(i).getName());
        }
        int choiceLecture = kbr.getInt("Please select lecturer you want!", 1, lecturers.size());
        Lecturer lecturer = lecturers.get(choiceLecture - 1);

        Module module = new Module(title, lecturer, university);
        modules.add(module);
        saveModuleToFile();
        System.out.println("Module added successfully!");
    }

    public void displayModule() {
        restoreModulesFromFile();
        if (modules.isEmpty()) {
            System.out.println("There are no modules to display!!");
        } else {
            System.out.println("Module Details\n");
            for (Module module : modules) {
                System.out.println("Name:" + module.getTitle() + " Lecturer:" + module.getLecturer().getName());
            }
        }
    }

    public void saveModuleToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath));) {
            for (Module module : modules)
                writer.println(module.getTitle() + "," + module.getLecturer().getName() + "," + module.getUniversity().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreModulesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
            String line = null;
            modules.clear();
            while ((line = reader.readLine()) != null) {
                makeModule(line);
            }
        } catch (Exception ex) {
            System.out.println("could not read the file");
            ex.printStackTrace();
        }
    }

    private void makeModule(String line) {
        if (line == null || line.trim().length() == 0) return;
        String[] parts = line.split(",");
        String title = parts[0];
        String lecturerName = parts[1];
        Lecturer lecturer = university.getLecturerByName(lecturerName);
        Module module = new Module(title, lecturer, university);
        modules.add(module);
    }


}
