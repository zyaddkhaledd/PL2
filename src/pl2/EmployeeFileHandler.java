 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EmployeeFileHandler {
    private static final String FILE_PATH = "pl2/DF/employees.txt";
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
                        String password = parts[2];
                        Role role = Role.valueOf(parts[3]); 
                        double salary = Double.parseDouble(parts[4]);
                        double bonus = Double.parseDouble(parts[5]);
                        int phoneNumber = Integer.parseInt(parts[6]);
                        
                        EmployeeType type = new EmployeeType(parts[7]); 
                        
                        Employee emp = new Employee(id, name, password, role, salary, bonus, phoneNumber, type);
                        employees.add(emp);
                    } catch (IllegalArgumentException ex) { 
                        System.err.println("Error parsing employee data line: " + line);
                        
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Employee file not found. Starting with an empty list.");
        } catch (IOException e) {
            System.err.println("Error reading employees data: " + e.getMessage());
        }
        
        return employees;
    }
    
    public static void saveEmployees(ArrayList<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            
            writer.println("ID|Name|Password|Role|Salary|Bonus|PhoneNumber|Department"); 
            
            for (Employee e : employees) {
                String line = e.getId() + "|" + 
                              e.getName() + "|" + 
                              e.getPassword() + "|" + 
                              e.getRole() + "|" + 
                              e.getSalary() + "|" + 
                              e.getBonus() + "|" + 
                              e.getPhoneNumber() + "|" + 
                              e.getEmployeeType().getDepartment();
                writer.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving employees data: " + e.getMessage());
        }
    }

    public static String getFilePath() {
        return FILE_PATH;
    }
}