package AdminManagement;

import java.io.*;
import java.util.ArrayList;

public class ProjectFileHandler {
    private static final String FILE_PATH = "C:\\Users\\msi\\Documents\\GitHub\\PL2\\src\\AdminManagement\\DF\\projects.txt";

    public static ArrayList<Project> loadProjects() {
        ArrayList<Project> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String customer = parts[2];
                    list.add(new Project(id, name, customer));
                }
            }
        } catch (IOException e) { System.err.println(e.getMessage()); }
        return list;
    }

    public static void saveProjects(ArrayList<Project> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Project p : list) {
                writer.println(p);
            }
        } catch (IOException e) { System.err.println(e.getMessage()); }
    }
}
