package JavaAdvancedExam22Feb2020;

import java.util.*;

public class Lootbox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        List<Integer> claimedItems = new ArrayList<>();

        Queue<Integer> queueBox = new ArrayDeque<>();
        Stack<Integer> stackBox = new Stack<>();

        Arrays.stream(firstLine.split("\\s+")).mapToInt(Integer::parseInt).forEach(queueBox::offer);
        Arrays.stream(secondLine.split("\\s+")).mapToInt(Integer::parseInt).forEach(stackBox::push);

        while(!queueBox.isEmpty() && !stackBox.isEmpty()) {
            int queueItem = queueBox.peek();
            int stackItem = stackBox.peek();

            int sumItem = queueItem + stackItem;

            if(sumItem % 2 == 0) {
                claimedItems.add(sumItem);
                queueBox.poll();
                stackBox.pop();
            } else {
                queueBox.offer(stackBox.pop());
            }
        }

        if (queueBox.isEmpty()) {
            System.out.println("First lootbox is empty");
        }

        if (stackBox.isEmpty()) {
            System.out.println("Second lootbox is empty");
        }

        int sum = claimedItems.stream().mapToInt(Integer::valueOf).sum();

        if (sum >= 100) {
            System.out.printf("Your loot was epic! Value: %s", sum);
        } else {
            System.out.printf("Your loot was poor... Value: %d", sum);
        }
    }
}
