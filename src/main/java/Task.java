public class Task {
    private String task;
    private boolean completed;
    public String status;
    public String time;

    public Task(String task) {
        this.task = task;
        completed = false;
        status = "Pending";
        time = "00:00";

    }
}
