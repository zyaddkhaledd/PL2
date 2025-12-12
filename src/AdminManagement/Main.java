package AdminManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        User currentUser = null;

        while (currentUser == null) {
            System.out.println("\n===== Welcome =====");
            System.out.println("1) Login");
            System.out.println("2) Sign Up");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                System.out.print("Enter Name: ");
                String name = input.nextLine();
                System.out.print("Enter Password: ");
                String password = input.nextLine();

                currentUser = UserFileHandler.login(name, password);

                if (currentUser == null) {
                    System.out.println("Login failed. Try again.");
                } else {
                    System.out.println("Login successful. Welcome " + currentUser.getName());
                }
            } else if (choice == 2) {
                System.out.print("Enter ID: ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("Enter Name: ");
                String name = input.nextLine();
                System.out.print("Enter Email: ");
                String email = input.nextLine();
                System.out.print("Enter Password: ");
                String password = input.nextLine();

                // تسجيل موظف جديد
                Employee newEmp = new Employee(id, name, password, email, 0, 0, 0, "");
                UserFileHandler.addUser(newEmp);
                EmployeeFileHandler.saveEmployees(EmployeeFileHandler.loadEmployees());

                System.out.println("Sign Up successful. You can now login.");
            }
        }

        // بعد تسجيل الدخول، نعرض القوائم حسب النوع
        if (currentUser instanceof Admin) {
            showAdminMenu((Admin) currentUser);
        } else {
            showEmployeeMenu((Employee) currentUser);
        }
    }

    // ================= ADMIN MENU =================
    public static void showAdminMenu(Admin admin) {
        Scanner input = new Scanner(System.in);
        EmployeeManager manager = admin.getManager();

        ArrayList<Task> tasks = TaskFileHandler.loadTasks();
        ArrayList<Project> projects = ProjectFileHandler.loadProjects();

        while (true) {
            System.out.println("\n===== ADMIN PANEL =====");
            System.out.println("1) Add Employee");
            System.out.println("2) Show All Employees");
            System.out.println("3) Update Employee");
            System.out.println("4) Delete Employee");
            System.out.println("5) Manage Tasks");
            System.out.println("6) Manage Projects");
            System.out.println("7) Exit");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: // إضافة موظف
                    System.out.print("Enter ID: ");
                    int id = input.nextInt(); input.nextLine();
                    System.out.print("Enter Name: "); String name = input.nextLine();
                    System.out.print("Enter Email: "); String email = input.nextLine();
                    System.out.print("Enter Password: "); String password = input.nextLine();
                    System.out.print("Enter Salary: "); double salary = input.nextDouble();
                    System.out.print("Enter Bonus: "); double bonus = input.nextDouble();
                    System.out.print("Enter Phone: "); int phone = input.nextInt(); input.nextLine();
                    System.out.print("Enter Department: "); String dep = input.nextLine();
                    Employee e = new Employee(id, name, password, email, salary, bonus, phone, dep);
                    manager.addEmployee(e);
                    break;

                case 2: // عرض جميع الموظفين
                    manager.displayAll();
                    break;

                case 3: // تحديث الموظف
                    System.out.print("Enter ID: "); int uid = input.nextInt();
                    System.out.print("Enter new salary: "); double sal = input.nextDouble();
                    System.out.print("Enter new bonus: "); double bon = input.nextDouble();
                    manager.updateEmployee(uid, sal, bon);
                    break;

                case 4: // حذف موظف
                    System.out.print("Enter ID: "); int did = input.nextInt();
                    manager.deleteEmployee(did);
                    break;

                case 5: // إدارة المهام
                    manageTasks(input, tasks);
                    break;

                case 6: // إدارة المشاريع
                    manageProjects(input, projects);
                    break;

                case 7:
                    TaskFileHandler.saveTasks(tasks);
                    ProjectFileHandler.saveProjects(projects);
                    return;
            }
        }
    }

    // ================= EMPLOYEE MENU =================
    public static void showEmployeeMenu(Employee emp) {
        System.out.println("\n===== EMPLOYEE PROFILE =====");
        System.out.println("ID: " + emp.getId());
        System.out.println("Name: " + emp.getName());
        System.out.println("Email: " + emp.getEmail());
        System.out.println("Salary: " + emp.getSalary());
        System.out.println("Bonus: " + emp.getBonus());
        System.out.println("Phone: " + emp.getPhoneNumber());
        System.out.println("Department: " + emp.getEmployeeType());

        // عرض المهام المخصصة له فقط
        ArrayList<Task> tasks = TaskFileHandler.loadTasks();
        System.out.println("\n===== YOUR TASKS =====");
        for (Task t : tasks) {
            if (t.getAssignedEmployeeId() == emp.getId()) {
                System.out.println(t);
            }
        }
    }

    // ================= إدارة المهام =================
    private static void manageTasks(Scanner input, ArrayList<Task> tasks) {
        System.out.println("1) Add Task, 2) Update Task, 3) Delete Task, 4) Show Tasks");
        int choice = input.nextInt(); input.nextLine();

        switch (choice) {
            case 1: // إضافة مهمة
                System.out.print("Enter ID: "); int id = input.nextInt(); input.nextLine();
                System.out.print("Enter Task Name: "); String name = input.nextLine();
                System.out.print("Enter Phase (PENDING, UNDER_WORK, TEST, EVALUATION, CANCELED): ");
                Phase phase = Phase.valueOf(input.nextLine().trim().toUpperCase());
                System.out.print("Enter Assigned Employee ID: "); int empId = input.nextInt(); input.nextLine();
                tasks.add(new Task(id, name, phase, empId));
                break;

            case 2: // تحديث مهمة
                System.out.print("Enter Task ID to update: "); int tid = input.nextInt(); input.nextLine();
                for (Task t : tasks) {
                    if (t.getId() == tid) {
                        System.out.print("New Name: "); t.setName(input.nextLine());
                        System.out.print("New Phase: "); t.setPhase(Phase.valueOf(input.nextLine().trim().toUpperCase()));
                        System.out.print("New Employee ID: "); t.setAssignedEmployeeId(input.nextInt()); input.nextLine();
                    }
                }
                break;

            case 3: // حذف مهمة
                System.out.print("Enter Task ID to delete: "); int delId = input.nextInt(); input.nextLine();
                tasks.removeIf(t -> t.getId() == delId);
                break;

            case 4: // عرض المهام
                for (Task t : tasks) {
                    System.out.println(t);
                }
                break;
        }
        TaskFileHandler.saveTasks(tasks);
    }

    // ================= إدارة المشاريع =================
    private static void manageProjects(Scanner input, ArrayList<Project> projects) {
        System.out.println("1) Add Project, 2) Update Project, 3) Delete Project, 4) Show Projects");
        int choice = input.nextInt(); input.nextLine();

        switch (choice) {
            case 1: // إضافة مشروع
                System.out.print("Enter ID: "); int id = input.nextInt(); input.nextLine();
                System.out.print("Enter Project Name: "); String name = input.nextLine();
                System.out.print("Enter Customer Name: "); String customer = input.nextLine();
                projects.add(new Project(id, name, customer));
                break;

            case 2: // تحديث مشروع
                System.out.print("Enter Project ID to update: "); int pid = input.nextInt(); input.nextLine();
                for (Project p : projects) {
                    if (p.getId() == pid) {
                        System.out.print("New Project Name: "); p.setName(input.nextLine());
                        System.out.print("New Customer Name: "); p.setCustomer(input.nextLine());
                    }
                }
                break;

            case 3: // حذف مشروع
                System.out.print("Enter Project ID to delete: "); int delId = input.nextInt(); input.nextLine();
                projects.removeIf(p -> p.getId() == delId);
                break;

            case 4: // عرض المشاريع
                for (Project p : projects) {
                    System.out.println(p);
                }
                break;
        }
        ProjectFileHandler.saveProjects(projects);
    }
}
