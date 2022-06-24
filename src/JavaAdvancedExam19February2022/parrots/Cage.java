package JavaAdvancedExam19February2022.parrots;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Parrot> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
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

    public List<Parrot> getData() {
        return data;
    }

    public void setData(List<Parrot> data) {
        this.data = data;
    }

    public void add(Parrot parrot) {
        if (capacity > data.size()) {
            data.add(parrot);
        }
    }

    public boolean remove(String name) {
        return data.removeIf(parrot -> parrot.getName().equals(name));
    }

    public Parrot sellParrot(String name) {
        Parrot result = null;
       for(Parrot parrot: data) {
           if (parrot.getName().equals(name)) {
               parrot.setAvailable(false);
               result = parrot;
           }

       }
        return result;
    }

    public List<Parrot>	sellParrotBySpecies(String species) {
        List<Parrot> soldBySpecies = new ArrayList<>();
        for (Parrot parrot : data) {
            if (parrot.getSpecies().equals(species)) {
                parrot.setAvailable(false);
                soldBySpecies.add(parrot);
            }
        }
        return soldBySpecies;
    }

    public int count() {
        return data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Parrots available at %s:", getName()));
        sb.append(System.lineSeparator());
        for (Parrot parrot : data) {
            if (parrot.isAvailable()) {
                sb.append(parrot);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}
