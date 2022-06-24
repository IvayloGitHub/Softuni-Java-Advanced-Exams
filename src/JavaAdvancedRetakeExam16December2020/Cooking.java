package JavaAdvancedRetakeExam16December2020;

import java.util.*;
import java.util.stream.Collectors;

public class Cooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Queue<Integer> liquidsQueue = new ArrayDeque<>();
        Stack<Integer> ingredientsStack = new Stack<>();

        Arrays.stream(firstLine.split(" ")).mapToInt(Integer::parseInt).forEach(liquidsQueue::offer);
        Arrays.stream(secondLine.split(" ")).mapToInt(Integer::parseInt).forEach(ingredientsStack::push);

        Map<String, Integer> food = new TreeMap<>();

        food.put("Bread", 0);
        food.put("Cake", 0);
        food.put("Pastry", 0);
        food.put("Fruit Pie", 0);

        while (!liquidsQueue.isEmpty() && !ingredientsStack.isEmpty()) {
            int queue = liquidsQueue.poll();
            int stack = ingredientsStack.pop();

            int sum = queue + stack;

            if (sum == 25) {
               food.put("Bread", food.get("Bread") + 1);
            } else if (sum == 50) {
                food.put("Cake", food.get("Cake") + 1);
            } else if (sum == 75) {
                food.put("Pastry", food.get("Pastry") + 1);
            } else if (sum == 100) {
                food.put("Fruit Pie", food.get("Fruit Pie") + 1);
            } else {
                ingredientsStack.push(stack + 3);
            }
        }

        if(checkForFoods(food)) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }

        if (liquidsQueue.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            String queueOutput = liquidsQueue.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Liquids left: " + queueOutput);
        }

        if (ingredientsStack.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            List<String> leftIngredients = ingredientsStack.stream().map(String::valueOf).collect(Collectors.toList());
            Collections.reverse(leftIngredients);
            System.out.println("Ingredients left: " + String.join(", ", leftIngredients));
        }

        for (Map.Entry<String, Integer> entry : food.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }

    private static boolean checkForFoods(Map<String, Integer> food) {
        return food.get("Bread") >= 1 &&
                food.get("Cake") >= 1 &&
                food.get("Pastry") >= 1 &&
                food.get("Fruit Pie") >= 1;
    }
}
