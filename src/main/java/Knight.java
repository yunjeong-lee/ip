import java.util.Scanner;

public class Knight {
    private static void print(String input) {
        System.out.println(input);
    }
    public static void main(String[] args) {
        String line = "__________";
        String divideLine = "\t" + line.repeat(6) + "\n";
        print(divideLine + "\t Hello! I'm Knight\n\t What can I do for you?\n" + divideLine);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        do {
            print(divideLine + "\t " + userInput + "\n" + divideLine);
            userInput = scanner.next();
        } while (!userInput.equals("bye"));

        print(divideLine + "\t Bye. Hope to see you again soon!\n" + divideLine);
        scanner.close();
    }
}
