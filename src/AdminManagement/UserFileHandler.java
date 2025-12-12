/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminManagement;

/**
 *
 * @author ZYAD KHALED
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserFileHandler {

    private static final String FILE_PATH = "src/AdminManagement/DF/users.txt";

    public static User login(String name, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String uName = parts[1];
                    String uPassword = parts[3];
                    String role = parts[4];

                    if (uName.equals(name) && uPassword.equals(password)) {
                        int id = Integer.parseInt(parts[0]);
                        String email = parts[2];

                        if (role.equalsIgnoreCase("Admin")) {
                            return new Admin(id, uName, uPassword, email);
                        } else {
                            return new Employee(id, uName, uPassword, email, 0, 0, 0, "");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users file: " + e.getMessage());
        }
        return null;
    }

    public static void addUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            String email = (user instanceof Employee) ? ((Employee) user).getEmail() : ((Admin) user).getEmail();
            String line = user.getId() + "|" +
                          user.getName() + "|" +
                          email + "|" +
                          user.getPassword() + "|" +
                          user.getRole();
            writer.println(line);
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }
}


