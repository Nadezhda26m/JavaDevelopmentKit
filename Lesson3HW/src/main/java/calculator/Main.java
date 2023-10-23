package calculator;

/*
Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа
(но необязательно разного между собой), над которыми должна быть произведена операция.
 */

public class Main {
    public static void main(String[] args) {
        System.out.println(Calculator.sum(0, 4)); // 4.0
        System.out.println(Calculator.sum(2, 7.7)); // 9.7
        System.out.println(Calculator.sum(6.1f, 1.1f)); // 7.199999928474426
        System.out.println(Calculator.sum(2.3, 4.12f)); // 6.419999885559082
        System.out.println(Calculator.sum(12L, 4)); // 16.0

        System.out.println(Calculator.subtract(0, 4)); // -4.0
        System.out.println(Calculator.subtract(2, 7.7)); // -5.7
        System.out.println(Calculator.subtract(6.1f, 1.1f)); // 4.9999998807907104
        System.out.println(Calculator.subtract(2.3, 4.12f)); // -1.8199998855590822
        System.out.println(Calculator.subtract(12L, 4)); // 8.0

        System.out.println(Calculator.multiply(0, 4)); // 0.0
        System.out.println(Calculator.multiply(2, 7.7)); // 15.4
        System.out.println(Calculator.multiply(6.1f, 1.1f)); // 6.710000040531156
        System.out.println(Calculator.multiply(2.3, 4.12f)); // 9.475999736785887
        System.out.println(Calculator.multiply(12L, 4)); // 48.0

        System.out.println(Calculator.divide(0, 4)); // 0.0
        System.out.println(Calculator.divide(2, 7.7)); // 0.2597402597402597
        System.out.println(Calculator.divide(6.1f, 1.1f)); // 5.5454543385623944
        System.out.println(Calculator.divide(2.3, 4.12f)); // 0.5582524426910005
        System.out.println(Calculator.divide(12L, 4)); // 3.0
        try {
            System.out.println(Calculator.divide(8.2, 0)); // Exception
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); // Divide by zero
        }

    }
}
