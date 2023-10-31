package list.register;

import java.time.LocalDate;
import java.util.ArrayList;

public class Register {
    private final ArrayList<Employee> employees;
    private int lastID;

    public Register() {
        employees = new ArrayList<>();
    }

    public boolean addEmployee(Employee employee) {
        if (!isEmployeeExist(employee)) {
            employee.setID(++lastID);
            employees.add(employee);
            return true;
        }
        return false;
    }

    private boolean isEmployeeExist(Employee employee) {
        for (Employee employee1 : employees) {
            if (employee1.equals(employee)) return true;
        }
        return false;
    }

    public boolean deleteEmployee(int ID) {
        Employee employee = findByID(ID);
        if (employee != null) {
            employees.remove(employee);
            return true;
        }
        return false;
    }

    public void addSeniority(int ID, LocalDate startDate, LocalDate endDate) {
        Employee employee = findByID(ID);
        if (employee != null) {
            employee.addSeniority(startDate, endDate);
        }
    }

    public void addSeniority(int ID, LocalDate startDate) {
        Employee employee = findByID(ID);
        if (employee != null) {
            employee.addSeniority(startDate);
        }
    }

    public void addSeniority(Employee employee, LocalDate startDate, LocalDate endDate) {
        if (employee != null) {
            employee.addSeniority(startDate, endDate);
        }
    }

    public void addSeniority(Employee employee, LocalDate startDate) {
        if (employee != null) {
            employee.addSeniority(startDate);
        }
    }

    public int size() {
        return employees.size();
    }

    @Override
    public String toString() {
        if (employees.size() == 0) return "Empty register";
        StringBuilder sb = new StringBuilder("Register: \n");
        for (Employee employee : employees) {
            sb.append(employee).append('\n');
        }
        return sb.substring(0, sb.length() - 1);
    }

    // Добавить метод, который ищет сотрудника по стажу (может быть список)
    public ArrayList<Employee> findBySeniority(int minValueYears, int minValueMonths,
                                               int maxValueYears, int maxValueMonths) {
        int minCountMonths = minValueYears * 12 + minValueMonths;
        int maxCountMonths = maxValueYears * 12 + maxValueMonths;
        if (minCountMonths > maxCountMonths) {
            int flag = minCountMonths;
            minCountMonths = maxCountMonths;
            maxCountMonths = flag;
        }
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            int months = employee.getSeniority().getCountMonths();
            if (months >= minCountMonths && months <= maxCountMonths) {
                result.add(employee);
            }
        }
        return result;
    }

    public ArrayList<Employee> findBySeniority(int numberOfYears, int numberOfMonths) {
        return findBySeniority(numberOfYears, numberOfMonths, numberOfYears, numberOfMonths);
    }

    // Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
    public ArrayList<String> findPhoneNumbersByName(String name) {
        ArrayList<String> phones = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equals(name))
                phones.add(employee.getPhone().toString());
        }
        return phones;
    }

    // Добавить метод, который ищет сотрудника по табельному номеру
    public Employee findByID(int ID) {
        for (Employee employee : employees) {
            if (employee.getID() == ID) return employee;
        }
        return null;
    }

}
