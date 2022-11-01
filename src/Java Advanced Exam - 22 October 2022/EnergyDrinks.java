import java.util.*;
import java.util.stream.Collectors;

public class EnergyDrinks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Stack<Integer> caffeine = new Stack<>();
        Queue<Integer> energyDrinks = new ArrayDeque<>();

        Arrays.stream(firstLine.split(", ")).mapToInt(Integer::parseInt).forEach(caffeine::push);
        Arrays.stream(secondLine.split(", ")).mapToInt(Integer::parseInt).forEach(energyDrinks::offer);

        int caffeineStamat = 0;

        while (!caffeine.isEmpty() && !energyDrinks.isEmpty()) {
            int caffeineMilligrams = caffeine.pop();
            int energyDrink = energyDrinks.poll();

            int caffeineAvailable = caffeineMilligrams * energyDrink;

            if ((caffeineStamat + caffeineAvailable) <= 300) {
                caffeineStamat += caffeineAvailable;
            } else {
                energyDrinks.offer(energyDrink);
                if (caffeineStamat - 30 > 0) {
                    caffeineStamat -= 30;
                }
            }
        }

        if (!energyDrinks.isEmpty()) {
            String energyDrinksLeft = energyDrinks.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Drinks left: " + energyDrinksLeft);
        } else {
            System.out.println("At least Stamat wasn't exceeding the maximum caffeine.");
        }

        System.out.printf("Stamat is going to sleep with %d mg caffeine.", caffeineStamat);
    }
}
