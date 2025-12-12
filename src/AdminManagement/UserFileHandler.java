package AdminManagement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserFileHandler {

    private static final String FILE_PATH = "C:\\Users\\msi\\Documents\\GitHub\\PL2\\src\\AdminManagement\\DF\\users.txt";

public static User login(String name, String password) {
    // أولاً: Admin افتراضي
    if (name.equals("admin") && password.equals("admin123")) {
        return new Admin(0, "admin", "admin123", "admin@example.com");
    }

    // الموظفين: نبحث مباشرة في employees.txt
    ArrayList<Employee> employees = EmployeeFileHandler.loadEmployees();

    for (Employee e : employees) {
        if (e.getName().equals(name) && e.getPassword().equals(password)) {
            return e; // ارجع الموظف كامل مع كل بياناته
        }
    }

    return null; // لو الاسم أو الباسورد غلط
}




    public static void addUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            String email = (user instanceof Employee) ? ((Employee) user).getEmail() : ((Admin) user).getEmail();
            String line = user.getId() + "|"
                    + user.getName() + "|"
                    + email + "|"
                    + user.getPassword() + "|"
                    + user.getRole();
            writer.println(line);
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }
}
