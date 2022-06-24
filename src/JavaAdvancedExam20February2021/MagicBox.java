package JavaAdvancedExam20February2021;

import java.util.*;

public class MagicBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> claimedItems = new ArrayList<>();

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Queue<Integer> queueBox = new ArrayDeque<>();
        Stack<Integer> stackBox = new Stack<>();

        Arrays.stream(firstLine.split(" ")).mapToInt(Integer::parseInt).forEach(queueBox::offer);
        Arrays.stream(secondLine.split(" ")).mapToInt(Integer::parseInt).forEach(stackBox::push);

        while(!queueBox.isEmpty() && !stackBox.isEmpty()) {
            int queue = queueBox.peek();
            int stack = stackBox.peek();

            int sum = queue + stack;

            if (sum % 2 == 0) {
                claimedItems.add(sum);
                queueBox.poll();
                stackBox.pop();
            } else {
                queueBox.offer(stackBox.pop());
            }
         }

        int totalSum = claimedItems.stream().mapToInt(Integer::intValue).sum();

        if (queueBox.isEmpty()) {
            System.out.println("First magic box is empty.");
        }

        if(stackBox.isEmpty()) {
            System.out.println("Second magic box is empty.");
        }

        if (totalSum >= 90) {
            System.out.printf("Wow, your prey was epic! Value: %d%n", totalSum);
        } else {
            System.out.printf("Poor prey... Value: %d%n", totalSum);
        }
    }
}
