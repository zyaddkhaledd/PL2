
package AdminManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EmployeeFileHandler {
    private static final String FILE_PATH = "src/AdminManagement/DF/employees.txt";

    public static ArrayList<Employee> loadEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String email = parts[2];
                        String password = parts[3];
                        double salary = Double.parseDouble(parts[4]);
                        double bonus = Double.parseDouble(parts[5]);
                        int phoneNumber = Integer.parseInt(parts[6]);
                        String type = parts[7];
                        employees.add(new Employee(id, name, password, email, salary, bonus, phoneNumber, type));
                    } catch (IllegalArgumentException ex) {
                        System.err.println("Error parsing: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
        return employees;
    }

    public static void saveEmployees(ArrayList<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println("ID|Name|Email|Password|Salary|Bonus|PhoneNumber|Type");
            for (Employee e : employees) {
                writer.println(e.getId() + "|" + e.getName() + "|" + e.getEmail() + "|" + e.getPassword() + "|" + e.getSalary() + "|" + e.getBonus() + "|" + e.getPhoneNumber() + "|" + e.getEmployeeType());
            }
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }
}
