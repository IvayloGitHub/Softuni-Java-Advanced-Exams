package JavaAdvancedRetakeExam17Dec2019.christmas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bag {
    private String color;
    private int capacity;
    private List<Present> data;

    public Bag(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getColor() {
        return this.color;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int count() {
        return this.data.size();
    }

    public void add(Present present) {
        if (this.capacity > data.size()) {
            data.add(present);
        }
    }

    public boolean remove(String name) {
        return data.removeIf(present -> present.getName().equals(name));
    }

    public Present heaviestPresent() {
        return  data.stream().max(Comparator.comparingDouble(Present::getWeight)).orElse(null);
    }

    public Present getPresent(String name) {
        for (Present present : data) {
            if (present.getName().equals(name)) {
                return present;
            }
        }
        return null;
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s bag contains:%n", getColor()));
        for (Present present : data) {
            sb.append(present.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

}
