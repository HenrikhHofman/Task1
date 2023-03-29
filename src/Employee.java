import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Employee {
    private String name;
    private LocalDate birthDate;
    private int[] monthlySalary;
    public Employee(String name, LocalDate birthDate, int[] monthlySalary) {
        this.name = name;
        this.birthDate = birthDate;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int[] getMonthlySalary() {
        return monthlySalary;
    }

    public double getAverageMonthlySalary() {
        int sum = 0;
        for (int salary : monthlySalary) {
            sum += salary;
        }
        return (double) sum / monthlySalary.length;
    }

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Anton", LocalDate.of(1990, 1, 1), new int[]{2000, 2300, 3000, 3500, 4100, 4500}),
                new Employee("Gin", LocalDate.of(1985, 3, 15), new int[]{2200, 2800, 3200, 3800, 4200, 4900}),
                new Employee("Ben", LocalDate.of(1995, 8, 20), new int[]{1800, 2100, 2300, 2320, 2400, 2600})
        };

        try (FileWriter writer = new FileWriter("employees_salary.txt")) {
            for (Employee employee : employees) {
                String line = String.format("%s\t%.2f\n", employee.getName(), employee.getAverageMonthlySalary());
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("employees_salary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}