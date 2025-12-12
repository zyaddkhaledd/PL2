package AdminManagement;


public class EmployeeType {

    private String department;

    public EmployeeType() {
    }

    public EmployeeType(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return department;
    }
}
