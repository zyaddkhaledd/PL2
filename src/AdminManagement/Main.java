/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AdminManagement;

/**
 *
 * @author ZYAD KHALED
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();

        while (true) {

            System.out.println("\n===== Employee System =====");
            System.out.println("1) Add Employee");
            System.out.println("2) Show All Employees");
            System.out.println("3) Update Employee Data"); 
            System.out.println("4) Exit");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {

                System.out.print("Enter ID: ");
                int id = input.nextInt();
                input.nextLine();

                System.out.print("Enter Name: ");
                String name = input.nextLine();

                System.out.print("Enter Password: ");
                String password = input.nextLine();

                System.out.print("Enter Salary: ");
                double salary = input.nextDouble();

                System.out.print("Enter Bonus: ");
                double bonus = input.nextDouble();

                System.out.print("Enter Phone: ");
                int phone = input.nextInt();
                input.nextLine();

                System.out.print("Enter Department: ");
                String dep = input.nextLine();

                EmployeeType type = new EmployeeType(dep);

                Employee emp = new Employee(
                        id,
                        name,
                        password,
                        Role.Employee,
                        salary,
                        bonus,
                        phone,
                        type
                );

                manager.addEmployee(emp);

            } else if (choice == 2) {
                System.out.println(" All Employees");
                manager.displayAll();

            } else if (choice == 3) {
                
                System.out.print("Enter ID of Employee to Update: ");
                int idToUpdate = input.nextInt();
                
                System.out.print("Enter NEW Salary: ");
                double newSalary = input.nextDouble();
                
                System.out.print("Enter NEW Bonus: ");
                double newBonus = input.nextDouble();
                input.nextLine();
                
                manager.updateEmployee(idToUpdate, newSalary, newBonus);

            } else if (choice == 4) {
                
                manager.saveAll(); 
                System.out.println("Exiting the system. Data saved.");
                break;

            } else {
    }
}
    }
}


