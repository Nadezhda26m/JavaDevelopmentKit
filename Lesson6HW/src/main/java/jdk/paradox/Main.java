package jdk.paradox;

/*
В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
(Парадокс Монти Холла — Википедия) и наглядно убедиться в верности парадокса (запустить
игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
Создать свой Java Maven или Gradle проект;
Самостоятельно реализовать прикладную задачу;
Сохранить результат в HashMap<шаг теста, результат>
Вывести на экран статистику по победам и поражениям
 */

import java.util.HashMap;
import java.util.Random;

public class Main {
    static final int NUMBER_OF_ROUNDS = 1000;
    static Random rand = new Random();
    static HashMap<Integer, Boolean> withoutChange = new HashMap<>();
    static HashMap<Integer, Boolean> withChange = new HashMap<>();
    static HashMap<Integer, Integer[]> gameDetails = new HashMap<>();

    public static void main(String[] args) {
        startGame();
        System.out.printf("Процент побед без смены выбора: %.2f%%\n", countWins(withoutChange));
        System.out.printf("Процент побед при смене выбора: %.2f%%\n", countWins(withChange));
        // Процент побед без смены выбора: 33,60%
        // Процент побед при смене выбора: 66,40%
        // printGameDetails();
        // printAllGameDetails();
    }

    static float countWins(HashMap<Integer, Boolean> game) {
        int count = 0;
        for (Boolean win : game.values()) {
            if (win) count++;
        }
        return  (float) count * 100 / NUMBER_OF_ROUNDS;
    }

    static void startGame() {
        int choice1, indPrize, choiceMaster;
        for (int i = 1; i <= NUMBER_OF_ROUNDS; i++) {
            indPrize = chooseFromAll();
            choice1 = chooseFromAll();
            choiceMaster = getChoiceMaster(choice1, indPrize);
            withoutChange.put(i, choice1 == indPrize);
            withChange.put(i, choice1 != indPrize);
            gameDetails.put(i, new Integer[]{indPrize + 1, choice1 + 1, choiceMaster + 1});
        }
    }

    static int chooseFromAll() {
        return rand.nextInt(3);
    }

    static int getChoiceMaster(int choice1, int indPrize) {
        if (choice1 != indPrize) {
            return 3 - (choice1 + indPrize); // 0 + 1 + 2 = 3
        }
        int choice = rand.nextInt(2);
        if (indPrize == 0 || (indPrize == 1 && choice == 1)) choice++;
        return choice;
    }

    static void printGameDetails() {
        Integer[] details;
        System.out.println("Детали игры:");
        for (var round : gameDetails.entrySet()) {
            details = round.getValue();
            System.out.printf("%d раунд. Приз в %d, игрок выбрал %d, ведущий убрал %d\n",
                    round.getKey(), details[0], details[1], details[2]);
        }
    }

    static void printAllGameDetails() {
        Integer[] details;
        System.out.println("Детали игры:");
        for (int i = 1; i <= NUMBER_OF_ROUNDS; i++) {
            details = gameDetails.get(i);
            System.out.printf("%d раунд. Приз в %d, игрок выбрал %d, ведущий убрал %d. " +
                            "Без смены: %s, при смене: %s\n",
                    i, details[0], details[1], details[2],
                    (withoutChange.get(i) ? "ПОБЕДА!!!" : "Поражение"),
                    (withChange.get(i) ? "ПОБЕДА!!!" : "Поражение"));
        }
    }
}
