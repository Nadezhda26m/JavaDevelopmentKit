package list;

/*
Создать справочник сотрудников
Необходимо:
    Создать класс справочник сотрудников, который содержит внутри коллекцию сотрудников -
    каждый сотрудник должен иметь следующие атрибуты:
        * Табельный номер
        * Номер телефона
        * Имя
        * Стаж
    Добавить метод, который ищет сотрудника по стажу (может быть список)
    Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
    Добавить метод, который ищет сотрудника по табельному номеру
    Добавить метод добавления нового сотрудника в справочник
 */

import list.register.Employee;
import list.register.PhoneNumber;
import list.register.Register;
import list.register.Seniority;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Василий",
                new PhoneNumber("+7", 123, 1234567),
                new Seniority(1945L, false));
        Employee employee2 = new Employee("Игорь",
                new PhoneNumber("+7", 256, 1234567),
                new Seniority(945L, true));
        Employee employee3 = new Employee("Татьяна",
                new PhoneNumber("+7", 256, 1234567),
                new Seniority());

        Register register = new Register();
        register.addEmployee(employee1);
        register.addEmployee(employee2);
        register.addEmployee(employee3);

        System.out.println(register + "\n");

        register.addSeniority(employee3, LocalDate.parse("2018-04-11"),
                LocalDate.parse("2023-01-14"));
        register.addSeniority(employee3, LocalDate.parse("2023-02-11"));
        System.out.println(register + "\n");

        register.addSeniority(1, LocalDateTime.now().toLocalDate());

        register.addEmployee(new Employee("Игорь",
                new PhoneNumber("+7", 223, 1254567),
                new Seniority(4559L, true)));
        System.out.println(register + "\n");

        System.out.println("Тел., Игорь: " + register.findPhoneNumbersByName("Игорь") + "\n");
        System.out.print("0,0: ");
        printArray(register.findBySeniority(0, 0));
        System.out.print("5,4: ");
        printArray(register.findBySeniority(5, 4));
        System.out.print("1,0 - 6,0: ");
        printArray(register.findBySeniority(1, 0, 6, 0));
        System.out.print("5,0 - 40,0: ");
        printArray(register.findBySeniority(5, 0, 40, 0));

        System.out.println("Количество работников: " + register.size());
        System.out.println("ID 4: " + register.findByID(4));
        System.out.println("del 4: " + register.deleteEmployee(4));
        System.out.println("Количество работников: " + register.size());
        System.out.println(register + "\n");

    }

    private static void printArray(ArrayList<?> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("{ }");
            return;
        }
        System.out.print("{ ");
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println(list.get(i));
        }
        System.out.println(list.get(list.size() - 1) + " }\n");
    }

    /*
    Register:
    ID=1, Имя: Василий, Тел.: +71231234567, Опыт: 5 лет 4 мес.
    ID=2, Имя: Игорь, Тел.: +72561234567, Опыт: 2 лет 7 мес.
    ID=3, Имя: Татьяна, Тел.: +72561234567, Опыт: 0

    Register:
    ID=1, Имя: Василий, Тел.: +71231234567, Опыт: 5 лет 4 мес.
    ID=2, Имя: Игорь, Тел.: +72561234567, Опыт: 2 лет 7 мес.
    ID=3, Имя: Татьяна, Тел.: +72561234567, Опыт: 6 лет 3 мес.

    Register:
    ID=1, Имя: Василий, Тел.: +71231234567, Опыт: 5 лет 4 мес.
    ID=2, Имя: Игорь, Тел.: +72561234567, Опыт: 2 лет 7 мес.
    ID=3, Имя: Татьяна, Тел.: +72561234567, Опыт: 6 лет 3 мес.
    ID=4, Имя: Игорь, Тел.: +72231254567, Опыт: 12 лет 7 мес.

    Тел., Игорь: [+72561234567, +72231254567]

    0,0: { }
    5,4: { ID=1, Имя: Василий, Тел.: +71231234567, Опыт: 5 лет 4 мес. }

    1,0 - 6,0: { ID=1, Имя: Василий, Тел.: +71231234567, Опыт: 5 лет 4 мес.
    ID=2, Имя: Игорь, Тел.: +72561234567, Опыт: 2 лет 7 мес. }

    5,0 - 40,0: { ID=1, Имя: Василий, Тел.: +71231234567, Опыт: 5 лет 4 мес.
    ID=3, Имя: Татьяна, Тел.: +72561234567, Опыт: 6 лет 3 мес.
    ID=4, Имя: Игорь, Тел.: +72231254567, Опыт: 12 лет 7 мес. }

    Количество работников: 4
    ID 4: ID=4, Имя: Игорь, Тел.: +72231254567, Опыт: 12 лет 7 мес.
    del 4: true
    Количество работников: 3
    Register:
    ID=1, Имя: Василий, Тел.: +71231234567, Опыт: 5 лет 4 мес.
    ID=2, Имя: Игорь, Тел.: +72561234567, Опыт: 2 лет 7 мес.
    ID=3, Имя: Татьяна, Тел.: +72561234567, Опыт: 6 лет 3 мес.
     */
}
