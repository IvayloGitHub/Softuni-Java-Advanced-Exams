package JavaAdvancedRetakeExam15December2021;

import java.util.*;
import java.util.stream.Collectors;

public class Meeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Stack<Integer> males = new Stack<>();
        Queue<Integer> females = new ArrayDeque<>();

        Arrays.stream(firstLine.split(" ")).mapToInt(Integer::parseInt).forEach(males::push);
        Arrays.stream(secondLine.split(" ")).mapToInt(Integer::parseInt).forEach(females::offer);

        int matchCount = 0;

        while(!males.isEmpty() && !females.isEmpty()) {
            int queueFemale = females.peek();
            int stackMale = males.peek();

            if (queueFemale <= 0) {
                females.poll();
                continue;
            }
            if (stackMale <= 0) {
                males.pop();
                continue;
            }

            if (queueFemale % 25 == 0) {
                females.poll();
                females.poll();
                continue;
            }

            if (stackMale % 25 == 0) {
                males.pop();
                males.pop();
                continue;
            }

            if (queueFemale == stackMale) {
                matchCount++;
                females.poll();
                males.pop();
            } else {
                females.poll();
                males.pop();
                males.push(stackMale - 2);
            }
        }
        System.out.printf("Matches: %d%n", matchCount);

        if (males.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            List<String> leftMales = males.stream().map(String::valueOf).collect(Collectors.toList());
            Collections.reverse(leftMales);
            System.out.println("Males left: " + String.join(", ", leftMales));
        }

        if (females.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            String output = females.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Females left: " + output);
        }
    }
}
