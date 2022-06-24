package JavaAdvancedRetakeExam17Dec2019;

import java.util.*;
import java.util.stream.Collectors;

public class SantaPresentFactory {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> toys = new TreeMap<>();

        toys.put("Doll", 0);
        toys.put("Wooden train", 0);
        toys.put("Teddy bear", 0);
        toys.put("Bicycle", 0);

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        Stack<Integer> materials = new Stack<>();
        Queue<Integer> magicValues = new ArrayDeque<>();

        Arrays.stream(firstLine.split("\\s+")).mapToInt(Integer::parseInt).forEach(materials::push);
        Arrays.stream(secondLine.split("\\s+")).mapToInt(Integer::parseInt).forEach(magicValues::offer);

        while(!materials.isEmpty() && !magicValues.isEmpty()) {
            int lastMaterial = materials.peek();
            int firstMagicValue = magicValues.peek();

            int multiplication = lastMaterial * firstMagicValue;
            if (multiplication == 150) {
                toys.put("Doll", toys.get("Doll") + 1);
                materials.pop();
                magicValues.poll();
            } else if (multiplication == 250) {
                toys.put("Wooden train", toys.get("Wooden train") + 1);
                materials.pop();
                magicValues.poll();
            } else if (multiplication == 300) {
                toys.put("Teddy bear", toys.get("Teddy bear") + 1);
                materials.pop();
                magicValues.poll();
            } else if (multiplication == 400) {
                toys.put("Bicycle", toys.get("Bicycle") + 1);
                materials.pop();
                magicValues.poll();
            } else if (multiplication < 0) {
                materials.pop();
                magicValues.poll();
                materials.push(lastMaterial + firstMagicValue);
            } else if (multiplication > 0) {
                materials.pop();
                magicValues.poll();
                materials.push(lastMaterial + 15);
            } else if (lastMaterial == 0 && firstMagicValue == 0) {
                materials.pop();
                magicValues.poll();
            } else if (lastMaterial == 0) {
                materials.pop();
            } else if (firstMagicValue == 0) {
                magicValues.poll();
            }
        }

        if ((toys.get("Doll") >= 1 && toys.get("Wooden train") >= 1) ||
                (toys.get("Teddy bear") >= 1 && toys.get("Bicycle") >= 1)  ) {
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
            System.out.println("No presents this Christmas!");
        }
        if(!materials.isEmpty()) {
            List<String> leftMaterials = materials.stream().map(String::valueOf).collect(Collectors.toList());
            Collections.reverse(leftMaterials);
            System.out.println("Materials left: " + String.join(", ",leftMaterials));
        }

        if(!magicValues.isEmpty()) {
            String leftMagicValues = magicValues.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("Magic left: " + leftMagicValues);
        }

        toys.entrySet().stream()
                .filter(e -> e.getValue() != 0)
                .forEach(el -> System.out.println(el.getKey() + ": " + el.getValue()));
    }
}
