package list.register;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private int ID;
    private String name;
    private PhoneNumber phone;
    private Seniority seniority;

    public Employee(String name, PhoneNumber phone, Seniority seniority) {
        this.name = name;
        this.phone = phone;
        this.seniority = seniority;
    }

    void setID(int ID) {
        this.ID = ID;
    }

    public void addSeniority(LocalDate startDate, LocalDate endDate) {
        seniority.addPeriod(startDate, endDate);
    }

    public void addSeniority(LocalDate startDate) {
        seniority.addCurrentWork(startDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name) && phone.equals(employee.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", Имя: " + name +
                ", Тел.: " + phone +
                ", Опыт: " + seniority;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public Seniority getSeniority() {
        return seniority;
    }
}
