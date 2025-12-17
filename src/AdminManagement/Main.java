package AdminManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            User currentUser = null;

            while (currentUser == null) {
                System.out.println("\n===== Welcome to Management System =====");
                System.out.println("1) Login");
                System.out.println("2) Sign Up");
                System.out.println("3) Exit Program");
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
                    int id = input.nextInt(); input.nextLine();
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();
                    System.out.print("Enter Password: ");
                    String password = input.nextLine();

                    Employee newEmp = new Employee(id, name, password, email, 0, 0, 0, "General");
                    UserFileHandler.addUser(newEmp);
                    EmployeeFileHandler.saveEmployees(EmployeeFileHandler.loadEmployees());

                    System.out.println("Sign Up successful. You can now login.");
                } else if (choice == 3) {
                    System.exit(0);
                }
            }

            if (currentUser instanceof Admin) {
                showAdminMenu((Admin) currentUser);
            } else if (currentUser instanceof Employee) {
                showEmployeeMenu((Employee) currentUser);
            }
        }
    }

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
            System.out.println("7) Logout");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: "); int id = input.nextInt(); input.nextLine();
                    System.out.print("Enter Name: "); String name = input.nextLine();
                    System.out.print("Enter Email: "); String email = input.nextLine();
                    System.out.print("Enter Password: "); String password = input.nextLine();
                    System.out.print("Enter Salary: "); double salary = input.nextDouble();
                    System.out.print("Enter Bonus: "); double bonus = input.nextDouble();
                    System.out.print("Enter Phone: "); int phone = input.nextInt(); input.nextLine();
                    System.out.print("Enter Department: "); String dep = input.nextLine();
                    manager.addEmployee(new Employee(id, name, password, email, salary, bonus, phone, dep));
                    break;
                case 2:
                    manager.displayAll();
                    break;
                case 3:
                    System.out.print("Enter ID: "); int uid = input.nextInt(); input.nextLine();
                    System.out.print("New Name: "); String nName = input.nextLine();
                    System.out.print("New Email: "); String nEmail = input.nextLine();
                    System.out.print("New Salary: "); double nSal = input.nextDouble();
                    System.out.print("New Bonus: "); double nBon = input.nextDouble();
                    System.out.print("New Phone: "); int nPh = input.nextInt(); input.nextLine();
                    System.out.print("New Department: "); String nDep = input.nextLine();
                    manager.updateEmployee(uid, nName, nEmail, nSal, nBon, nPh, nDep);
                    break;
                case 4:
                    System.out.print("Enter ID: "); int did = input.nextInt();
                    manager.deleteEmployee(did);
                    break;
                case 5: manageTasks(input, tasks); break;
                case 6: manageProjects(input, projects); break;
                case 7: return;
            }
        }
    }

    public static void showEmployeeMenu(Employee emp) {
        System.out.println("\n===== EMPLOYEE PROFILE =====");
        System.out.println("ID: " + emp.getId());
        System.out.println("Name: " + emp.getName());
        System.out.println("Email: " + emp.getEmail());
        System.out.println("Salary: " + emp.getSalary());
        System.out.println("Bonus: " + emp.getBonus());
        System.out.println("Phone: " + emp.getPhoneNumber());
        System.out.println("Department: " + emp.getEmployeeType());

        ArrayList<Task> tasks = TaskFileHandler.loadTasks();
        System.out.println("\n===== YOUR TASKS =====");
        for (Task t : tasks) {
            if (t.getAssignedEmployeeId() == emp.getId()) {
                System.out.println(t);
            }
        }
    }

    private static void manageTasks(Scanner input, ArrayList<Task> tasks) {
        System.out.println("1) Add Task, 2) Update Task, 3) Delete Task, 4) Show Tasks");
        int choice = input.nextInt(); input.nextLine();
        switch (choice) {
            case 1:
                System.out.print("ID: "); int id = input.nextInt(); input.nextLine();
                System.out.print("Name: "); String name = input.nextLine();
                System.out.print("Phase: "); Phase ph = Phase.valueOf(input.nextLine().trim().toUpperCase());
                System.out.print("Emp ID: "); int eid = input.nextInt();
                tasks.add(new Task(id, name, ph, eid));
                break;
            case 2:
                System.out.print("ID: "); int tid = input.nextInt(); input.nextLine();
                for (Task t : tasks) {
                    if (t.getId() == tid) {
                        System.out.print("Name: "); t.setName(input.nextLine());
                        System.out.print("Phase: "); t.setPhase(Phase.valueOf(input.nextLine().trim().toUpperCase()));
                        System.out.print("Emp ID: "); t.setAssignedEmployeeId(input.nextInt());
                    }
                }
                break;
            case 3:
                System.out.print("ID: "); int delId = input.nextInt();
                tasks.removeIf(t -> t.getId() == delId);
                break;
            case 4:
                for (Task t : tasks) System.out.println(t);
                break;
        }
        TaskFileHandler.saveTasks(tasks);
    }

    private static void manageProjects(Scanner input, ArrayList<Project> projects) {
        System.out.println("1) Add Project, 2) Update Project, 3) Delete Project, 4) Show Projects");
        int choice = input.nextInt(); input.nextLine();
        switch (choice) {
            case 1:
                System.out.print("ID: "); int id = input.nextInt(); input.nextLine();
                System.out.print("Name: "); String name = input.nextLine();
                System.out.print("Customer: "); String customer = input.nextLine();
                projects.add(new Project(id, name, customer));
                break;
            case 2:
                System.out.print("ID: "); int pid = input.nextInt(); input.nextLine();
                for (Project p : projects) {
                    if (p.getId() == pid) {
                        System.out.print("Name: "); p.setName(input.nextLine());
                        System.out.print("Customer: "); p.setCustomer(input.nextLine());
                    }
                }
                break;
            case 3:
                System.out.print("ID: "); int delId = input.nextInt();
                projects.removeIf(p -> p.getId() == delId);
                break;
            case 4:
                for (Project p : projects) System.out.println(p);
                break;
        }
        ProjectFileHandler.saveProjects(projects);
    }
}