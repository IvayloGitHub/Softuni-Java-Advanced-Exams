package JavaAdvancedExam23October2021;

import java.util.*;

public class AutumnCocktails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> cocktails = new TreeMap<>();

        cocktails.put("Pear Sour", 0);
        cocktails.put("The Harvest", 0);
        cocktails.put("Apple Hinny", 0);
        cocktails.put("High Fashion", 0);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Queue<Integer> ingredients = new ArrayDeque<>();
        Stack<Integer> freshness = new Stack<>();

        Arrays.stream(firstLine.split(" ")).mapToInt(Integer::parseInt).forEach(ingredients::offer);
        Arrays.stream(secondLine.split(" ")).mapToInt(Integer::parseInt).forEach(freshness::push);

        while(!ingredients.isEmpty() && !freshness.isEmpty()) {

            int queue = ingredients.poll();

            if (queue == 0) {
                continue;
            }
            int stack = freshness.pop();

            int multiplication = queue * stack;

            if (multiplication == 150) {
                cocktails.put("Pear Sour", cocktails.get("Pear Sour") + 1);
            } else if (multiplication == 250) {
                cocktails.put("The Harvest", cocktails.get("The Harvest") + 1);
            } else if (multiplication == 300) {
                cocktails.put("Apple Hinny", cocktails.get("Apple Hinny") + 1);
            } else if (multiplication == 400) {
                cocktails.put("High Fashion", cocktails.get("High Fashion") + 1);
            } else {
                ingredients.offer(queue + 5);
            }
        }

        if (isValid(cocktails)) {
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }

        if(!ingredients.isEmpty()) {
            int sum = ingredients.stream().mapToInt(Integer::intValue).sum();
            System.out.printf("Ingredients left: %d%n", sum);
        }
        for (Map.Entry<String, Integer> entry : cocktails.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.printf(" # %s --> %d%n", entry.getKey(), entry.getValue());
            }
        }
    }

    private static boolean isValid(Map<String, Integer> cocktails) {
        return cocktails.get("Pear Sour") >= 1 &&
                cocktails.get("The Harvest") >= 1 &&
                cocktails.get("Apple Hinny") >= 1 &&
                cocktails.get("High Fashion") >= 1;
    }
}
