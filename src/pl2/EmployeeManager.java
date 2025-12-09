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

    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
        System.out.println("Employee added successfully!");
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
}

