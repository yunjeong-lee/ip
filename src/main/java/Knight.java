import java.util.ArrayList;
import java.util.Scanner;

public class Knight {

    // Helper to simplify println
    public static void print(String input) {
        System.out.println(input);
    }
    static String line = "__________";
    static String divideLine = "\t" + line.repeat(6) + "\n";

    // Initialize ArrayList tasks with capacity 100
    public static ArrayList<TaskTuple> taskStorage = new ArrayList<>(100);

    // addTask : add to taskStorage and print the added
    public static void addTask(TaskTuple taskInput) {
        taskStorage.add(taskInput);
        System.out.println(taskStorage.toArray().length);
        print(divideLine + "\t added: " + taskInput.taskName + "\n" + divideLine);
    }

    // listTasks : list all elements in 'tasks'
    public static void listTasks() {
        print(divideLine + "\t Here are the tasks in your list:");
        for (int i = 0; i < taskStorage.toArray().length; i++) {
            String index = String.valueOf(i+1);
            TaskTuple taskObj = taskStorage.get(i);
            String tickmark = (taskObj.taskDone) ? ".[X] " : ".[ ] ";
            print("\t " + index + tickmark + taskObj.taskName);
        }
        print("\n" + divideLine);
    }

    // Helper to identify "mark number"
    public static boolean startsWithMark(String input) {
        // add exception handling
        if (input.isBlank()) {
            return false;
        }
        String[] userInputs = input.split(" ");
        return (userInputs[0].equals("mark"));
    }

    // Helper to identify "unmark number"
    public static boolean startsWithUnark(String input) {
        // add exception handling
        if (input.isBlank()) {
            return false;
        }
        String[] userInputs = input.split(" ");
        return (userInputs[0].equals("unmark"));
    }

    public static void markTaskStorageAt(int index) {
        print(divideLine + "\t Nice! I've marked this task as done:");
        TaskTuple currTask = taskStorage.get(index);
        currTask.taskDone = true;
        print("\t   [X] " + currTask.taskName);
        print(divideLine);
    }

    public static void unmarkTaskStorageAt(int index) {
        print(divideLine + "\t OK, I've marked this task as not done yet:");
        TaskTuple currTask = taskStorage.get(index);
        currTask.taskDone = false;
        print("\t   [ ] " + currTask.taskName);
        print(divideLine);
    }

    public static void main(String[] args) {
        print(divideLine + "\t Hello! I'm Knight\n\t What can I do for you?\n" + divideLine);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        TaskTuple inputTask = new TaskTuple(userInput);
        do {
            if (userInput.equals("list")) {
                listTasks();
            } else if (startsWithMark(userInput)) {
                print("now marking!\n");
                int inputIndex = Integer.valueOf(userInput.split(" ")[1]) - 1;
                markTaskStorageAt(inputIndex);

            } else if (startsWithUnark(userInput)) {
                print("now unmarking!\n");
                int inputIndex = Integer.valueOf(userInput.split(" ")[1]) - 1;
                unmarkTaskStorageAt(inputIndex);
            } else {
                addTask(inputTask); //userInput
            }
            userInput = scanner.nextLine();
            inputTask = new TaskTuple(userInput);
        } while (!userInput.equals("bye"));

        print(divideLine + "\t Bye. Hope to see you again soon!\n" + divideLine);
        scanner.close();
    }
}
