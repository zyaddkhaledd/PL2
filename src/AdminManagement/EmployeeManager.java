/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2;

/**
 *
 * @author ZYAD KHALED
 */
import java.util.ArrayList;

public class EmployeeManager {

    private ArrayList<Employee> employees; 
    public EmployeeManager() {
        this.employees = EmployeeFileHandler.loadEmployees(); 
    }

    public Employee findEmployee(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
        EmployeeFileHandler.saveEmployees(employees); 
        System.out.println("Employee added successfully and saved to file!");
    }

    public boolean updateEmployee(int id, double newSalary, double newBonus) {
        Employee employeeToUpdate = findEmployee(id);

        if (employeeToUpdate != null) {
            
            employeeToUpdate.setSalary(newSalary);
            employeeToUpdate.setBonus(newBonus);

            EmployeeFileHandler.saveEmployees(employees); 
            
            System.out.println("Employee ID: " + id + " updated successfully!");
            return true;
        } else {
            System.out.println("Error: Employee with ID " + id + " not found. Update failed.");
            return false;
        }
    }
    
       public boolean deleteEmployee(int id) {
        Employee employeeToDelete = findEmployee(id);
        
        if (employeeToDelete != null) {
            employees.remove(employeeToDelete);
            
            EmployeeFileHandler.saveEmployees(employees); 
            
            System.out.println("Employee ID: " + id + " deleted successfully!");
            return true;
        } else {
            System.out.println("Error: Employee with ID " + id + " not found. Deletion failed.");
            return false;
        }
    }

    public void displayAll() {
        if (employees.isEmpty()) {
            System.out.println("No employees recorded.");
            return;
        }

        for (Employee e : employees) {
            System.out.println(e);
        }
    }
            public void saveAll() {
        EmployeeFileHandler.saveEmployees(employees);
    }
}
