package JavaAdvancedExam28June2020.parking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Parking {
    private String type;
    private int capacity;
    private List<Car> data;

    public Parking(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Car> getData() {
        return data;
    }

    public void setData(List<Car> data) {
        this.data = data;
    }

    public void add(Car car) {
        if (capacity > data.size()) {
            data.add(car);
        }
    }

    public boolean remove(String manufacturer, String model) {
        return data.removeIf(c -> c.getManufacturer().equals(manufacturer) && c.getModel().equals(model));
    }

    public Car getLatestCar() {
        if (data.size() == 0) {
            return null;
        } else {
          List<Car>sorted = data.stream().sorted(Comparator.comparingInt(Car::getYear).reversed())
                  .collect(Collectors.toList());
          return sorted.get(0);
        }
    }

    public Car getCar(String manufacturer, String model) {
        return data.stream().filter(car -> car.getManufacturer().equals(manufacturer) && car.getModel()
                .equals(model)).findFirst().orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("The cars are parked in %s:", getType()));
        sb.append(System.lineSeparator());
        for (Car car : data) {
            sb.append(car.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
