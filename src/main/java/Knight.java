import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Knight {

    // Helper to simplify println
    public static void print(String input) {
        System.out.println(input);
    }
    static String line = "__________";
    static String divideLine = "\t" + line.repeat(6) + "\n";

    // Initialize ArrayList tasks with capacity 100
    public static ArrayList<Task> taskStorage = new ArrayList<>(100);

    // addTask : add to taskStorage and print the added
    public static void addTask(Task taskInput) {
        taskStorage.add(taskInput);
        print(divideLine + "\t added: " + taskInput.taskName + "\n" + divideLine);
    }

    // listTasks : list all elements in 'tasks'
    public static void listTasks() {
        print(divideLine + "\t Here are the tasks in your list:");
        for (int i = 0; i < taskStorage.toArray().length; i++) {
            String index = String.valueOf(i+1) + ".";
            print("\t " + index + taskStorage.get(i).toString());
        }
        print(divideLine);
    }

    // Helpers to identify 'mark' 'unmark' 'deadlien' 'event' 'todo'
    public static boolean startsWithString(String input, String startString) {
        if (input.isBlank()) {
            return false;
        }
        String[] userInputs = input.split(" ");
        return userInputs[0].equals(startString);
    }

    public static void markTaskStorageAt(int index) {
        print(divideLine + "\t Nice! I've marked this task as done:");
        Task currTask = taskStorage.get(index);
        currTask.taskDone = true;
        print("\t   " + currTask.toString() + "\n" + divideLine);
    }

    public static void unmarkTaskStorageAt(int index) {
        print(divideLine + "\t OK, I've marked this task as not done yet:");
        Task currTask = taskStorage.get(index);
        currTask.taskDone = false;
        print("\t   " + currTask.toString() + "\n" + divideLine);
    }

    // Helper to prune out the first string (eg, "deadline", "event", etc.)
    public static String withoutFirst(String input) {
        String[] originalInput = input.split(" ");
        String[] newInput = new String[originalInput.length - 1];
        // Copy elements from index 1 to the end to ignore "deadline"
        System.arraycopy(originalInput, 1, newInput, 0, newInput.length);
        return String.join(" ", newInput);
    }

    public static void addDeadline(Deadline dTask) {
        taskStorage.add(dTask);
        print(divideLine + "\t Got it. I've added this task:\n\t   " + dTask.toString() + "\n" + divideLine);
    }
    public static void main(String[] args) {
        print(divideLine + "\t Hello! I'm Knight\n\t What can I do for you?\n" + divideLine);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        Task inputTask = new Task(userInput);
        do {
            if (userInput.equals("list")) {
                listTasks();
            } else if (startsWithString(userInput, "mark")) {
                int inputIndex = Integer.valueOf(userInput.split(" ")[1]) - 1;
                markTaskStorageAt(inputIndex);
            } else if (startsWithString(userInput, "mark")) {
                int inputIndex = Integer.valueOf(userInput.split(" ")[1]) - 1;
                unmarkTaskStorageAt(inputIndex);
            } else if (startsWithString(userInput, "deadline")) {
                String withoutFirstString = withoutFirst(userInput);

                String[] deadlinesArr = withoutFirstString.split(" ");
                // First find index of "\by"
                List<String> deadlinesList = Arrays.asList(deadlinesArr);
                int indexBy = deadlinesList.indexOf("/by");
                String[] descriptionArr = new String[indexBy];
                System.arraycopy(deadlinesArr, 0, descriptionArr, 0, indexBy);

                String description = String.join(" ", descriptionArr);
                Deadline deadlineTask = new Deadline(description,"some date");
                addDeadline(deadlineTask);
            } else {
                addTask(inputTask); //userInput
            }
            userInput = scanner.nextLine();
            inputTask = new Task(userInput);
        } while (!userInput.equals("bye"));

        print(divideLine + "\t Bye. Hope to see you again soon!\n" + divideLine);
        scanner.close();
    }
}
