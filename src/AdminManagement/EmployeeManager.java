
package AdminManagement;

import java.util.ArrayList;

public class EmployeeManager {
    private ArrayList<Employee> employees;

    public EmployeeManager() {
        this.employees = EmployeeFileHandler.loadEmployees();
    }

    public ArrayList<Employee> getAllEmployees() {
        return this.employees;
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
        System.out.println("Employee added successfully!");
    }

    public boolean updateEmployee(int id, String newName, String newEmail, double newSalary, double newBonus, int newPhone, String newType) {
        Employee emp = findEmployee(id); 
        if (emp != null) {
            emp.setName(newName);
            emp.setEmail(newEmail);
            emp.setSalary(newSalary);
            emp.setBonus(newBonus);
            emp.setPhoneNumber(newPhone);
            emp.setEmployeeType(newType);
            EmployeeFileHandler.saveEmployees(employees); 
            return true;
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        Employee employeeToDelete = findEmployee(id);
        if (employeeToDelete != null) {
            employees.remove(employeeToDelete);
            EmployeeFileHandler.saveEmployees(employees);
            return true;
        }
        return false;
    }

    public void displayAll() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public void saveAll() {
        EmployeeFileHandler.saveEmployees(employees);
    }
}