/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl2;

/**
 *
 * @author ZYAD KHALED
 */
public class Employee extends User {

    private double salary;
    private double bonus;
    private int phoneNumber;
    
    public Employee() { }

    public Employee(int id, String name, String password, Role role,
                    double salary, double bonus, int phoneNumber,
                    EmployeeType type) {

        super(id, name, password, role);
        this.salary = salary;
        this.bonus = bonus;
        this.phoneNumber = phoneNumber;
        
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
public String toString() {
    return "ID: " + getId() +
           " | Name: " + getName() +
           " | Salary: " + salary +
           " | Bonus: " + bonus +
           " | Phone: " + phoneNumber;
}

   


}

