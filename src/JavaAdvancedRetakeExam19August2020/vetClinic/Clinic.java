package JavaAdvancedRetakeExam19August2020.vetClinic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Clinic {
    private List<Pet> data;
    private int capacity;

    public Clinic(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public List<Pet> getData() {
        return data;
    }

    public void setData(List<Pet> data) {
        this.data = data;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void add(Pet pet) {
        if (capacity > data.size()) {
            data.add(pet);
        }
    }

    public boolean remove(String name) {
        return  data.removeIf(p -> p.getName().equals(name));
    }

    public Pet getPet(String name, String owner) {
        return data.stream().filter(p -> p.getName().equals(name) && p.getOwner().equals(owner))
                .findFirst().orElse(null);
    }

    public Pet getOldestPet() {
        return data.stream().max(Comparator.comparingInt(Pet::getAge)).orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append("The clinic has the following patients:");
        sb.append(System.lineSeparator());
        for (Pet pet : data) {
            sb.append(String.format("%s %s", pet.getName(), pet.getOwner()));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

}
