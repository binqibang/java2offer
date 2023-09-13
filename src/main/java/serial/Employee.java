package serial;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("(name: %s, salary: %.2f)", name, salary);
    }
}
