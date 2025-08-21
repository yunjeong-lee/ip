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
    public static ArrayList<String> tasks = new ArrayList<>(100);

    public static void addTask(String taskInput) {
        tasks.add(taskInput);
        System.out.println(tasks.toArray().length);
        print(divideLine + "\t added: " + taskInput + "\n" + divideLine);
    }

    public static void listTasks() {
        for (int i = 0; i < tasks.toArray().length; i++) {
            String index = String.valueOf(i+1);
            print(index + ". " + tasks.get(i));
        }
        print("\n");
    }

    public static void main(String[] args) {
        print(divideLine + "\t Hello! I'm Knight\n\t What can I do for you?\n" + divideLine);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        do {
            if (userInput.equals("list")) {
                listTasks();
            } else {
                addTask(userInput);
            }
            userInput = scanner.next();
        } while (!userInput.equals("bye"));

        print(divideLine + "\t Bye. Hope to see you again soon!\n" + divideLine);
        scanner.close();
    }
}
