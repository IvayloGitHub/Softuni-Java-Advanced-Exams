package JavaAdvancedExam26June2021;

import java.util.*;
import java.util.stream.Collectors;

public class OSPlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();
        int task = Integer.parseInt(scanner.nextLine());

        Stack<Integer> tasks = new Stack<>();
        Queue<Integer> threads = new ArrayDeque<>();

        Arrays.stream(firstLine.split(", ")).mapToInt(Integer::parseInt).forEach(tasks::push);
        Arrays.stream(secondLine.split(" ")).mapToInt(Integer::parseInt).forEach(threads::offer);

        while(true) {
            int queue = tasks.peek();
            int stack = threads.peek();

            if(queue == task) {
                break;
            }

            if (stack >= queue) {
                tasks.pop();
                threads.poll();
            } else {
                threads.poll();
            }

        }
        String leftThreads = threads.stream().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.printf("Thread with value %d killed task %s%n", threads.peek(), tasks.peek());
        System.out.println(String.join(" ", leftThreads));

    }
}
