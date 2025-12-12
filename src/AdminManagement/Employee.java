package AdminManagement;

public class Employee extends User {

    private double salary;
    private double bonus;
    private int phoneNumber;
    private String employeeType;

    public Employee() {
    }

    public Employee(int id, String name, String password, String email,
            double salary, double bonus, int phoneNumber,
            String employeeType) {

        super(id, name, password, email, Role.Employee);
        this.salary = salary;
        this.bonus = bonus;
        this.phoneNumber = phoneNumber;
        this.employeeType = employeeType;
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

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + " | Name: " + getName()
                + " | Email: " + getEmail()
                + " | Salary: " + salary
                + " | Bonus: " + bonus
                + " | Phone: " + phoneNumber
                + " | Type: " + employeeType;
    }
}
