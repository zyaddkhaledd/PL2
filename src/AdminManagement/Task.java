package AdminManagement;

 enum Phase {PENDING,UNDER_WORK,TEST,EVALUATION,CANCELED};


public class Task {
    private int id;
    private String name;
    private Phase phase;
    private int assignedEmployeeId;

    public Task(int id, String name, Phase phase, int assignedEmployeeId) {
        this.id = id;
        this.name = name;
        this.phase = phase;
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Phase getPhase() { return phase; }
    public int getAssignedEmployeeId() { return assignedEmployeeId; }

    public void setName(String name) { this.name = name; }
    public void setPhase(Phase phase) { this.phase = phase; }
    public void setAssignedEmployeeId(int id) { this.assignedEmployeeId = id; }

    @Override
    public String toString() {
        return id + "|" + name + "|" + phase + "|" + assignedEmployeeId;
    }
}
