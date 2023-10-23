package pair;

/*
Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из
составляющих пары, а также переопределение метода toString(), возвращающее строковое
представление пары.
 */

public class Main {
    public static void main(String[] args) {
        Pair pair1 = new Pair("qwer", 4);
        Pair pair2 = new Pair("qwer", "abc");
        Pair pair3 = new Pair("qwer", null);
        Pair pair4 = new Pair(pair1, 'v');

        System.out.println(pair1);
        System.out.println("first: " + pair1.getFirst());
        System.out.println("second: " + pair1.getSecond() + "\n");

        System.out.println(pair2);
        System.out.println("first: " + pair2.getFirst());
        System.out.println("second: " + pair2.getSecond() + "\n");

        System.out.println(pair3);
        System.out.println("first: " + pair3.getFirst());
        System.out.println("second: " + pair3.getSecond() + "\n");

        System.out.println(pair4);
        System.out.println("first: " + pair4.getFirst());
        System.out.println("second: " + pair4.getSecond() + "\n");
    }
}
