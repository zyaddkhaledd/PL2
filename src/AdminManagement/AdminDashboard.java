package AdminManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminDashboard extends JFrame {
    private JTabbedPane tabbedPane;

    public AdminDashboard(Admin admin) {
        setTitle("Admin Panel - Welcome " + admin.getName());
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Employees", createEmployeePanel(admin.getManager()));
        tabbedPane.addTab("Tasks", createTasksPanel());
        tabbedPane.addTab("Projects", createProjectsPanel());

        add(tabbedPane);
    }

    private JPanel createEmployeePanel(EmployeeManager manager) {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"ID", "Name", "Email", "Salary", "Type"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        ArrayList<Employee> list = manager.getAllEmployees();
        for (Employee e : list) {
            model.addRow(new Object[]{e.getId(), e.getName(), e.getEmail(), e.getSalary(), e.getEmployeeType()});
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton addBtn = new JButton("Add Employee");
        JButton delBtn = new JButton("Delete Selected");
        JButton updateBtn = new JButton("Update Salary");
        JButton logoutBtn = new JButton("Logout");

        logoutBtn.setBackground(new Color(255, 69, 0));
        logoutBtn.setForeground(Color.WHITE);
        
        addBtn.addActionListener(e -> showAddEmployeeDialog(manager, model));
        
        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = (int) model.getValueAt(row, 0);
                if (manager.deleteEmployee(id)) {
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Employee Deleted!");
                }
            }
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String newSalary = JOptionPane.showInputDialog(this, "Enter New Salary:");
                if (newSalary != null && !newSalary.isEmpty()) {
                    try {
                        int id = (int) model.getValueAt(row, 0);
                        Employee emp = manager.findEmployee(id);
                        if (emp != null) {
                            emp.setSalary(Double.parseDouble(newSalary));
                            manager.saveAll();
                            model.setValueAt(emp.getSalary(), row, 3);
                            JOptionPane.showMessageDialog(this, "Salary Updated!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Invalid Salary Format!");
                    }
                }
            }
        });

        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new LoginFrame().setVisible(true);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(delBtn);
        btnPanel.add(logoutBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void showAddEmployeeDialog(EmployeeManager manager, DefaultTableModel model) {
        JTextField idF = new JTextField();
        JTextField nameF = new JTextField();
        JTextField emailF = new JTextField();
        JTextField passF = new JTextField();
        JTextField salaryF = new JTextField();
        JTextField bonusF = new JTextField();
        JTextField phoneF = new JTextField();
        String[] types = {"FullTime", "PartTime", "Intern"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        Object[] message = {
            "ID:", idF, "Name:", nameF, "Email:", emailF, "Password:", passF,
            "Salary:", salaryF, "Bonus:", bonusF, "Phone:", phoneF, "Type:", typeBox
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add New Employee", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idF.getText());
                double sal = Double.parseDouble(salaryF.getText());
                double bon = Double.parseDouble(bonusF.getText());
                int ph = Integer.parseInt(phoneF.getText());
                String type = (String) typeBox.getSelectedItem();

                Employee newEmp = new Employee(id, nameF.getText(), passF.getText(), emailF.getText(), sal, bon, ph, type);
                manager.addEmployee(newEmp);
                UserFileHandler.addUser(newEmp);
                model.addRow(new Object[]{id, nameF.getText(), emailF.getText(), sal, type});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Check data format!");
            }
        }
    }

    private JPanel createTasksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"TaskID", "Title", "Status", "EmployeeID"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        ArrayList<Task> list = TaskFileHandler.loadTasks();
        for (Task t : list) {
            model.addRow(new Object[]{t.getId(), t.getName(), t.getPhase(), t.getAssignedEmployeeId()});
        }
        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createProjectsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"ProjectID", "Name", "Customer"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        ArrayList<Project> list = ProjectFileHandler.loadProjects();
        for (Project p : list) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getCustomer()});
        }
        panel.add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        return panel;
    }
} 