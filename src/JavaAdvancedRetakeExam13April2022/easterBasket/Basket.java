package JavaAdvancedRetakeExam13April2022.easterBasket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Basket {
    private List<Egg> data;
    private String material;
    private int capacity;

    public Basket(String material, int capacity) {
        this.material = material;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public List<Egg> getData() {
        return data;
    }

    public void setData(List<Egg> data) {
        this.data = data;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addEgg(Egg egg) {
        if (capacity > data.size()) {
            data.add(egg);
        }
    }

    public boolean removeEgg(String color) {
        return data.removeIf(egg -> egg.getColor().equals(color));
    }

    public Egg getStrongestEgg() {
        return data.stream().max(Comparator.comparingInt(Egg::getStrength)).orElse(null);
    }

    public Egg getEgg(String color) {
        return data.stream().filter(egg -> egg.getColor().equals(color)).findFirst().orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s basket contains:", getMaterial()));
        sb.append(System.lineSeparator());

        for (Egg egg : data) {
            sb.append(egg);
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
