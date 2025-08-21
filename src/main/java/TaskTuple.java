public class TaskTuple {
    String taskName;
    boolean taskDone;

    public TaskTuple(String taskName, boolean taskDone) {
        this.taskName = taskName;
        this.taskDone = taskDone;
    }

    public TaskTuple(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }
}
