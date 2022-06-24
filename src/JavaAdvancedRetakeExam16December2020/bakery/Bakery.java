package JavaAdvancedRetakeExam16December2020.bakery;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bakery {
    private List<Employee> employees;
    private String name;
    private int capacity;

    public Bakery(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public void add(Employee employee) {
        if (capacity > employees.size()) {
            employees.add(employee);
        }
    }

    public boolean remove(String name) {
        return employees.removeIf(employee -> employee.getName().equals(name));
    }

    public Employee getOldestEmployee() {
        return employees.stream().max(Comparator.comparingInt(Employee::getAge)).orElse(null);
    }

    public Employee getEmployee(String name) {
        return employees.stream().filter(employee -> employee.getName().equals(name)).findFirst().orElse(null);
    }

    public int getCount() {
        return employees.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Employees working at Bakery %s:", getName()));
        sb.append(System.lineSeparator());
        for (Employee employee : employees) {
            sb.append(employee.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
