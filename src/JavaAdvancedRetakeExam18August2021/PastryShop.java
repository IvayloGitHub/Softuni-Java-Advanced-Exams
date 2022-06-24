package JavaAdvancedRetakeExam18August2021;

import java.util.*;
import java.util.stream.Collectors;

public class PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> food = new LinkedHashMap<>();

        food.put("Biscuit", 0);
        food.put("Cake", 0);
        food.put("Pie", 0);
        food.put("Pastry", 0);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Queue<Integer> liquids = new ArrayDeque<>();
        Stack<Integer> ingredients = new Stack<>();

        Arrays.stream(firstLine.split(" ")).mapToInt(Integer::parseInt).forEach(liquids::offer);
        Arrays.stream(secondLine.split(" ")).mapToInt(Integer::parseInt).forEach(ingredients::push);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int queue = liquids.poll();
            int stack = ingredients.pop();

            int sum = queue + stack;

            if (sum == 25) {
                food.put("Biscuit", food.get("Biscuit") + 1);
            } else if (sum == 50) {
                food.put("Cake", food.get("Cake") + 1);
            } else if (sum == 100) {
                food.put("Pie", food.get("Pie") + 1);
            } else if (sum == 75) {
                food.put("Pastry", food.get("Pastry") + 1);
            } else {
                ingredients.push(stack + 3);
            }
        }

        if (checkForMoreThanOne(food)) {
            System.out.println("Great! You succeeded in cooking all the food!");
        } else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }

        if (liquids.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            String output = liquids.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Liquids left: " + output);
        }

        if (ingredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            List<String> leftIngredients = ingredients.stream().map(String::valueOf).collect(Collectors.toList());
            Collections.reverse(leftIngredients);
            System.out.println("Ingredients left: " + String.join(", ", leftIngredients));
        }

        for (Map.Entry<String, Integer> entry : food.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }

    private static boolean checkForMoreThanOne(Map<String, Integer> food) {
        return food.get("Biscuit") >= 1 &&
                food.get("Cake") >= 1 &&
                food.get("Pie") >= 1 &&
                food.get("Pastry") >= 1;
    }
}
