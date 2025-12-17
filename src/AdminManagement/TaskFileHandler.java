package AdminManagement;

import java.io.*;
import java.util.ArrayList;

public class TaskFileHandler {
    private static final String FILE_PATH = "src/AdminManagement/DF/tasks.txt";

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    Phase phase = Phase.valueOf(parts[2].trim().toUpperCase());
                    int empId = Integer.parseInt(parts[3].trim());
                    list.add(new Task(id, name, phase, empId));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static void saveTasks(ArrayList<Task> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("TaskID|Title|Status|EmployeeID");
            for (Task t : list) {
                writer.println(t.getId() + "|" + t.getName() + "|" + t.getPhase() + "|" + t.getAssignedEmployeeId());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
} 