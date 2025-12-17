package AdminManagement;

import java.io.*;
import java.util.ArrayList;

public class ProjectFileHandler {
    private static final String FILE_PATH = "src/AdminManagement/DF/projects.txt";

    public static ArrayList<Project> loadProjects() {
        ArrayList<Project> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    list.add(new Project(Integer.parseInt(parts[0]), parts[1], parts[2]));
                }
            }
        } catch (IOException e) { }
        return list;
    }

    public static void saveProjects(ArrayList<Project> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("ProjectID|ProjectName|CustomerName");
            for (Project p : list) {
                writer.println(p.getId() + "|" + p.getName() + "|" + p.getCustomer());
            }
        } catch (IOException e) { }
    }
} 