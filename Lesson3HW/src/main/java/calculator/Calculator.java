package calculator;

public class Calculator {
    public static <T extends Number> double sum(T num1, T num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static <T extends Number> double multiply(T num1, T num2) {
        return num1.doubleValue() * num2.doubleValue();
    }

    // деление
    public static <T extends Number> double divide(T num1, T num2) {
        if (num2.doubleValue() == 0)
            throw new ArithmeticException("Divide by zero");
        return num1.doubleValue() / num2.doubleValue();
    }

    // вычитание
    public static <T extends Number> double subtract(T num1, T num2) {
        return num1.doubleValue() - num2.doubleValue();
    }
}
