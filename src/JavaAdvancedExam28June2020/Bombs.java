package JavaAdvancedExam28June2020;

import java.util.*;
import java.util.stream.Collectors;

public class Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> bombs = new TreeMap<>();

        bombs.put("Datura Bombs", 0);
        bombs.put("Cherry Bombs", 0);
        bombs.put("Smoke Decoy Bombs", 0);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Queue<Integer> bombEffect = new ArrayDeque<>();
        Stack<Integer> bombCasing = new Stack<>();

        Arrays.stream(firstLine.split(", ")).mapToInt(Integer::parseInt).forEach(bombEffect::offer);
        Arrays.stream(secondLine.split(", ")).mapToInt(Integer::parseInt).forEach(bombCasing::push);


        while (!bombEffect.isEmpty() && !bombCasing.isEmpty()) {
            if (hasAll(bombs)) {
                break;
            }
            int queueElement = bombEffect.peek();
            int stackElement = bombCasing.pop();

            int sum = queueElement + stackElement;
            if (sum == 40) {
                bombEffect.poll();
                bombs.put("Datura Bombs", bombs.get("Datura Bombs") + 1);
            } else if (sum == 60) {
                bombEffect.poll();
                bombs.put("Cherry Bombs", bombs.get("Cherry Bombs") + 1);
            } else if (sum == 120) {
                bombEffect.poll();
                bombs.put("Smoke Decoy Bombs", bombs.get("Smoke Decoy Bombs") + 1);
            } else {
                bombCasing.push(stackElement - 5);
            }
        }
        if (hasAll(bombs)) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }
        if (bombEffect.isEmpty()) {
            System.out.println("Bomb Effects: empty");
        } else {
            System.out.print("Bomb Effects: ");
            String leftEffects = bombEffect.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println(leftEffects);
        }

        if (bombCasing.isEmpty()) {
            System.out.println("Bomb Casings: empty");
        } else {
            System.out.print("Bomb Casings: ");
            String leftCasing = bombCasing.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println(leftCasing);
        }

        for (Map.Entry<String, Integer> entry : bombs.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }

    private static boolean hasAll(Map<String, Integer> bombs) {

        return bombs.get("Datura Bombs") >= 3 &&
                bombs.get("Cherry Bombs") >= 3 &&
                bombs.get("Smoke Decoy Bombs") >= 3;


    }
}
