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
        print(divideLine + "\t Got it. I've added this task:\n\t   " + taskInput.toString() + "\n"
                + "\t Now you have " + taskStorage.toArray().length + " tasks in the list.\n" + divideLine);
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
    public static boolean startsWithString(String input, String startString)
            throws KnightException, MeaninglessException {
        if (input.isBlank()) {
            return false;
        }
        String[] userInputs = input.split(" ");
        String fstString = userInputs[0];
        if ((fstString.equals("todo") || fstString.equals("event") || fstString.equals("deadline")) &&
                userInputs.length == 1) {
            throw new KnightException(input);
        } else if (userInputs.length == 1) {
            throw new MeaninglessException();
        }
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

    public static void removeFromTaskStorageAt(int index) {
        print(divideLine + "\t Noted. I've removed this task:");
        Task currTask = taskStorage.remove(index);
        print("\t   " + currTask.toString() + "\n\t Now you have "
                + taskStorage.toArray().length + " tasks in the list.\n" + divideLine);
    }

    // Helper to prune out the first string (eg, "deadline", "event", etc.)
    public static String withoutFirst(String input) {
        String[] originalInput = input.split(" ");
        String[] newInput = new String[originalInput.length - 1];
        // Copy elements from index 1 to the end to ignore "deadline"
        System.arraycopy(originalInput, 1, newInput, 0, newInput.length);
        return String.join(" ", newInput);
    }

    public static Update updateCommand(String userInput) throws KnightException, MeaninglessException {
        if (userInput.equals("list")) {
            return Update.LIST;
        } else if (startsWithString(userInput, "mark")) {
            return Update.MARK;
        } else if (startsWithString(userInput, "unmark")) {
            return Update.UNMARK;
        } else if (startsWithString(userInput, "delete")) {
            return Update.DELETE;
        } else if (startsWithString(userInput, "todo")) {
            return Update.TODO;
        } else if (startsWithString(userInput, "event")) {
            return Update.EVENT;
        } else if (startsWithString(userInput, "deadline")) {
            return Update.DEADLINE;
        } else {
            return Update.OTHER;
        }
    }

    public static void main(String[] args) {
        print(divideLine + "\t Hello! I'm Knight\n\t What can I do for you?\n" + divideLine);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        Task inputTask = new Task(userInput);
        do {
            try {
                switch(updateCommand(userInput)) {
                    case LIST:
                        listTasks();
                        break;
                    case MARK:
                        int markInputIndex = Integer.valueOf(userInput.split(" ")[1]) - 1;
                        markTaskStorageAt(markInputIndex);
                        break;
                    case UNMARK:
                        int unmarkInputIndex = Integer.valueOf(userInput.split(" ")[1]) - 1;
                        unmarkTaskStorageAt(unmarkInputIndex);
                        break;
                    case DELETE:
                        int delieteInputIndex = Integer.valueOf(userInput.split(" ")[1]) - 1;
                        removeFromTaskStorageAt(delieteInputIndex);
                        break;
                    case TODO:
                        String todoTaskDescription = withoutFirst(userInput);
                        Todo todoTask = new Todo(todoTaskDescription);
                        addTask(todoTask);
                        break;
                    case EVENT:
                        String withoutFirstString = withoutFirst(userInput);
                        String[] eventArr = withoutFirstString.split(" ");
                        // First find indexes of "\from" and "\to"
                        List<String> eventList = Arrays.asList(eventArr);
                        int indexFrom = eventList.indexOf("/from");
                        int indexTo = eventList.indexOf("/to");
                        String[] descriptionArr = new String[indexFrom];
                        String[] fromArr = new String[indexTo - indexFrom - 1];
                        String[] toArr = new String[eventArr.length - indexTo - 1];
                        System.arraycopy(eventArr, 0, descriptionArr, 0, indexFrom);
                        System.arraycopy(eventArr, indexFrom + 1, fromArr, 0, indexTo - indexFrom - 1);
                        System.arraycopy(eventArr, indexTo + 1, toArr, 0, eventArr.length - indexTo - 1);

                        // Put together
                        String description = String.join(" ", descriptionArr);
                        String from = String.join(" ", fromArr);
                        String to = (toArr.length == 1) ? toArr[0] : String.join(" ", toArr);

                        // Create Event task and add
                        Event eventTask = new Event(description, from, to);
                        addTask(eventTask);
                        break;
                    case DEADLINE:
                        String dWithoutFirstString = withoutFirst(userInput);
                        String[] deadlinesArr = dWithoutFirstString.split(" ");

                        // First find index of "\by"
                        List<String> deadlinesList = Arrays.asList(deadlinesArr);
                        int indexBy = deadlinesList.indexOf("/by");
                        String[] dDescriptionArr = new String[indexBy];
                        String[] byArr = new String[deadlinesArr.length - indexBy - 1];
                        System.arraycopy(deadlinesArr, 0, dDescriptionArr, 0, indexBy);
                        System.arraycopy(deadlinesArr, indexBy + 1, byArr, 0, deadlinesArr.length - indexBy - 1);

                        // Put together
                        String dDescription = String.join(" ", dDescriptionArr);
                        String by = String.join(" ", byArr);

                        // Create Deadline task and add
                        Deadline deadlineTask = new Deadline(dDescription, by);
                        addTask(deadlineTask);
                        break;
                    case OTHER:
                        // TODO: To raise an error here
                        addTask(inputTask); //userInput
                        break;
                }
            } catch (KnightException e) {
                print(divideLine + "\t Oh no! The description of " + e + " cannot be empty. :/\n" + divideLine);
            } catch (MeaninglessException e) {
                print(divideLine + "\t Oh no! I do not understand what that means. :/\n" + divideLine);
            }
            userInput = scanner.nextLine();
            inputTask = new Task(userInput);
        } while (!userInput.equals("bye"));

        print(divideLine + "\t Bye. Hope to see you again soon!\n" + divideLine);
        scanner.close();
    }
}
