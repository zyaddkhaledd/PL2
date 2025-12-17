package AdminManagement;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserFileHandler {
    private static final String FILE_PATH = "src/AdminManagement/DF/users.txt";

    public static User login(String name, String password) {
        if (name.equals("admin") && password.equals("admin123")) {
            return new Admin(0, "admin", "admin123", "admin@example.com");
        }
        ArrayList<Employee> employees = EmployeeFileHandler.loadEmployees();
        for (Employee e : employees) {
            if (e.getName().equals(name) && e.getPassword().equals(password)) {
                return e;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            String email = (user instanceof Employee) ? ((Employee) user).getEmail() : ((Admin) user).getEmail();
            writer.println(user.getId() + "|" + user.getName() + "|" + email + "|" + user.getPassword() + "|" + user.getRole());
        } catch (IOException e) { }
    }
}
