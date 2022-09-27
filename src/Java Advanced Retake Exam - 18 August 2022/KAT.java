import java.util.*;

public class KAT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Queue<Long> platesQueue = new ArrayDeque<>();
        Stack<Long> carsStack = new Stack<>();

        Arrays.stream(firstLine.split(", ")).mapToLong(Long::parseLong).forEach(platesQueue::offer);
        Arrays.stream(secondLine.split(", ")).mapToLong(Long::parseLong).forEach(carsStack::push);

        long countOfRegisteredCars = 0;
        long countOfDays = 0;


        while(!platesQueue.isEmpty() && !carsStack.isEmpty()) {
            long plates = platesQueue.poll();
            long cars = carsStack.pop();

            long necessaryPlates = cars * 2;
            countOfDays ++;

            if (plates > necessaryPlates) {
                long platesLeft = plates - necessaryPlates;
                countOfRegisteredCars += cars;
                platesQueue.offer(platesLeft);
            } else if (plates == necessaryPlates) {
                countOfRegisteredCars += cars;
            } else {
                long notRegisteredCars = cars - (plates / 2);
                countOfRegisteredCars += (plates / 2);
                carsStack.insertElementAt(notRegisteredCars, 0);
            }
        }

        System.out.printf("%d cars were registered for %d days!%n", countOfRegisteredCars, countOfDays);

        if (!platesQueue.isEmpty()) {
            long countOfRemainPlates = platesQueue.stream().mapToLong(Long::longValue).sum();
            System.out.printf("%d license plates remain!%n", countOfRemainPlates);
        } else if (!carsStack.isEmpty()) {
            long countOfCarsWithoutPlates = carsStack.stream().mapToLong(Long::longValue).sum();
            System.out.printf("%d cars remain without license plates!%n", countOfCarsWithoutPlates);
        } else {
            System.out.println("Good job! There is no queue in front of the KAT!");
        }
    }
}