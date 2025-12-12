package AdminManagement;

public class Project {
    private int id;
    private String name;
    private String customer;

    public Project(int id, String name, String customer) {
        this.id = id;
        this.name = name;
        this.customer = customer;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCustomer() { return customer; }

    public void setName(String name) { this.name = name; }
    public void setCustomer(String customer) { this.customer = customer; }

    @Override
    public String toString() {
        return id + "|" + name + "|" + customer;
    }
}
