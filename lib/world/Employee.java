package lib.world;

public class Employee implements Comparable<Employee>{
    
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double percent) {
        double raise = percent * this.salary / 100;
        this.salary += raise;
    }

    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }
}
