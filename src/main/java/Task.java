public class Task {
    String taskName;
    boolean taskDone;

    public Task(String taskName, boolean taskDone) {
        this.taskName = taskName;
        this.taskDone = taskDone;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

    @Override
    public String toString() {
        String tickmark = (this.taskDone) ? "[X] " : "[ ] ";
        return tickmark + this.taskName;
    }
}
