package AdminManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;

    public LoginFrame() {
        setTitle("Login System");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel(" Username:"));
        userField = new JTextField();
        add(userField);

        add(new JLabel(" Password:"));
        passField = new JPasswordField();
        add(passField);

        loginBtn = new JButton("Login");
        add(new JLabel("")); 
        add(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userField.getText();
                String pass = new String(passField.getPassword());

                User user = UserFileHandler.login(name, pass);

                if (user != null) {
                    JOptionPane.showMessageDialog(null, "Welcome " + user.getName());
                    
                    if (user instanceof Admin) {
                        AdminDashboard adminPage = new AdminDashboard((Admin) user);
                        adminPage.setVisible(true); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Employee Dashboard is under development.");
                    }
                    dispose(); 
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Invalid Name or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}