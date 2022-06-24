package JavaAdvancedRetakeExam14April2021;

import java.util.*;

public class Bouquets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Stack<Integer> tulipsStack = new Stack<>();
        Queue<Integer> daffodilsQueue = new ArrayDeque<>();

        Arrays.stream(firstLine.split(", ")).mapToInt(Integer::parseInt).forEach(tulipsStack::push);
        Arrays.stream(secondLine.split(", ")).mapToInt(Integer::parseInt).forEach(daffodilsQueue::offer);

        int numberOfBouquets = 0;
        int sumOfLeftFlowers = 0;
        while (!tulipsStack.isEmpty() && !daffodilsQueue.isEmpty()) {
            int stack = tulipsStack.pop();
            int queue = daffodilsQueue.poll();

            int sum = stack + queue;

            if (sum == 15) {
                numberOfBouquets++;
            } else if (sum < 15) {
                sumOfLeftFlowers += sum;
            } else {
                while (true) {
                    sum -= 2;
                    if (sum == 15) {
                        numberOfBouquets++;
                        break;
                    } else if (sum < 15) {
                        sumOfLeftFlowers += sum;
                        break;
                    }
                }
            }
        }
        if (sumOfLeftFlowers >= 15) {
            numberOfBouquets += sumOfLeftFlowers / 15;
        }

        if (numberOfBouquets >= 5) {
            System.out.printf("You made it! You go to the competition with %d bouquets!%n", numberOfBouquets);
        } else {
            System.out.printf("You failed... You need more %d bouquets.%n", 5 - numberOfBouquets);
        }
    }
}
