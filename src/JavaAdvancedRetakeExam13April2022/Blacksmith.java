package JavaAdvancedRetakeExam13April2022;

import java.util.*;
import java.util.stream.Collectors;

public class Blacksmith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> swords = new TreeMap<>();

        swords.put("Gladius", 0);
        swords.put("Shamshir", 0);
        swords.put("Katana", 0);
        swords.put("Sabre", 0);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Queue<Integer> steelQueue = new ArrayDeque<>();
        Stack<Integer> carbonStack = new Stack<>();

        Arrays.stream(firstLine.split("\\s+")).mapToInt(Integer::parseInt).forEach(steelQueue::offer);
        Arrays.stream(secondLine.split("\\s+")).mapToInt(Integer::parseInt).forEach(carbonStack::push);

        int countOfForgedSwords = 0;
        while (!steelQueue.isEmpty() && !carbonStack.isEmpty()) {
            int steel = steelQueue.poll();
            int carbon = carbonStack.pop();

            int sum = steel + carbon;
            if (sum == 70) {
                swords.put("Gladius", swords.get("Gladius") + 1);
                countOfForgedSwords++;
            } else if (sum == 80) {
                swords.put("Shamshir", swords.get("Shamshir") + 1);
                countOfForgedSwords++;
            } else if (sum == 90) {
                swords.put("Katana", swords.get("Katana") + 1);
                countOfForgedSwords++;
            } else if (sum == 110) {
                swords.put("Sabre", swords.get("Sabre") + 1);
                countOfForgedSwords++;
            } else {
                carbonStack.push(carbon + 5);
            }
        }

        if (countOfForgedSwords >= 1) {
            System.out.printf("You have forged %d swords.%n", countOfForgedSwords);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }

        if (steelQueue.isEmpty()) {
            System.out.println("Steel left: none");
        } else {
            String steelLeft = steelQueue.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Steel left: " + steelLeft);
        }

        if (carbonStack.isEmpty()) {
            System.out.println("Carbon left: none");
        } else {
            List<String> carbonLeft = carbonStack.stream().map(String::valueOf).collect(Collectors.toList());
            Collections.reverse(carbonLeft);
            System.out.println("Carbon left: " + String.join(", ", carbonLeft));
        }

        for (Map.Entry<String, Integer> entry : swords.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
            }
        }
    }
}
