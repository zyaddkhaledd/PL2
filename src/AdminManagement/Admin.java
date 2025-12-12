package AdminManagement;

public class Admin extends User {

    private EmployeeManager manager;

    public Admin() {}

    public Admin(int id, String name, String password, String email) {
        super(id, name, password, email, Role.Admin);
        this.manager = new EmployeeManager();
    }

    public EmployeeManager getManager() {
        return manager;
    }
}
