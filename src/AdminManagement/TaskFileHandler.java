package AdminManagement;

import java.io.*;
import java.util.ArrayList;

public class TaskFileHandler {
    private static final String FILE_PATH = "C:\\Users\\msi\\Documents\\GitHub\\PL2\\src\\AdminManagement\\DF\\tasks.txt";

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    Phase phase = Phase.valueOf(parts[2].toUpperCase());
                    int empId = Integer.parseInt(parts[3]);
                    list.add(new Task(id, name, phase, empId));
                }
            }
        } catch (IOException e) { System.err.println(e.getMessage()); }
        return list;
    }

    public static void saveTasks(ArrayList<Task> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task t : list) {
                writer.println(t);
            }
        } catch (IOException e) { System.err.println(e.getMessage()); }
    }
}
