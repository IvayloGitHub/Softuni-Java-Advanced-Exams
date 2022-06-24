package JavaAdvancedRetakeExam19August2020;

import java.util.*;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Stack<Integer> liliesStack = new Stack<>();
        Queue<Integer> rosesQueue = new ArrayDeque<>();

        Arrays.stream(firstLine.split(", ")).mapToInt(Integer::parseInt).forEach(liliesStack::push);
        Arrays.stream(secondLine.split(", ")).mapToInt(Integer::parseInt).forEach(rosesQueue::offer);

        int sumOfLeftFlowers = 0;
        int countOfWreaths = 0;

        while(!liliesStack.isEmpty() && !rosesQueue.isEmpty()) {
            int stack = liliesStack.pop();
            int queue = rosesQueue.poll();
            int sum = stack + queue;
            if (sum == 15) {
                countOfWreaths++;
            } else if (sum < 15) {
                sumOfLeftFlowers += sum;
            } else {
                while(true) {
                    sum -= 2;
                    if (sum == 15) {
                        countOfWreaths++;
                        break;
                    } else if (sum < 15){
                        sumOfLeftFlowers += sum;
                        break;
                    }
                }
            }
        }

        if (sumOfLeftFlowers >= 15) {
            countOfWreaths += sumOfLeftFlowers / 15;
        }

        if (countOfWreaths >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!%n", countOfWreaths);
        } else {
            System.out.printf("You didn't make it, you need %d wreaths more!%n", 5 - countOfWreaths);
        }
    }
}
