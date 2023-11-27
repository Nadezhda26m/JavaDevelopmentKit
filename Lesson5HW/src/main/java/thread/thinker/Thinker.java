package thread.thinker;

import java.util.concurrent.CountDownLatch;

public class Thinker extends Thread {
    private final int MAX_COUNT_MEAL = 3;
    private final int placeID;
    private int countCompletedMeal;
    private CountDownLatch cdl;
    private final int leftFork;
    private final int rightFork;
    private final Table table;

    public Thinker(int placeID, Table table, int leftFork, int rightFork, CountDownLatch cdl) {
        super("Философ" + placeID);
        this.placeID = placeID;
        this.table = table;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        while (countCompletedMeal < MAX_COUNT_MEAL) {
            thinking();
            eating();
        }
        System.out.println(Thread.currentThread().getName() + " наелся");
        cdl.countDown();
    }

    private void eating() {
        if (table.tryGetForks(leftFork, rightFork)) {
            countCompletedMeal++;
            System.out.println(Thread.currentThread().getName() + " кушает "
                    + countCompletedMeal + " раз вилками L:" + (leftFork + 1)  + " и R:" + (rightFork + 1));
            sleepMS(1000);
            table.putForks(leftFork, rightFork);
        }
    }

    private void thinking() {
        System.out.println(Thread.currentThread().getName() + " думает");
        sleepMS(1000);
    }

    private void sleepMS(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Thinker{" +
                "ID" + placeID +
                ", lFork=" + leftFork +
                ", rFork=" + rightFork +
                '}';
    }
}