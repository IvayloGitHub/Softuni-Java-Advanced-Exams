package JavaAdvancedRetakeExam15December2021.aquarium;

import java.util.LinkedHashSet;
import java.util.Set;

public class Aquarium {
    private Set<Fish> fishInPool;
    private String name;
    private int capacity;
    private int size;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new LinkedHashSet<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFishInPool() {
        return fishInPool.size();
    }

    public void add(Fish fish) {
        if (capacity > fishInPool.size()) {
            fishInPool.add(fish);
        }
    }

    public boolean remove(String name) {
        return fishInPool.removeIf(fish -> fish.getName().equals(name));
    }

    public Fish findFish(String name) {
        return fishInPool.stream()
                .filter(fish -> fish.getName().equals(name))
                .findFirst().orElse(null);
    }

    public String report() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Aquarium: %s ^ Size: %d", getName(), getSize()));
        sb.append(System.lineSeparator());
        for (Fish fish : fishInPool) {
            sb.append(fish);
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
