package thread.thinker;

import java.util.concurrent.CountDownLatch;

public class Thinker extends Thread {
    private final int MAX_COUNT_MEAL = 3;
    private final int placeID;
    private int countCompletedMeal;
    private CountDownLatch cdl;

    enum State {
        EATING, THINKING
    }

    private State state;
    private final Fork leftFork;
    private final Fork rightFork;

    public Thinker(int placeID, Fork leftFork, Fork rightFork, CountDownLatch cdl) {
        super("Философ" + placeID);
        this.placeID = placeID;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.state = State.THINKING;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        while (countCompletedMeal < MAX_COUNT_MEAL) {
            if (state == State.THINKING && takeForks()) {
                eating();
            } else thinking();
        }
        cdl.countDown();
    }

    private boolean takeForks() {
        if (takeFork(leftFork)) {
            if (takeFork(rightFork)) {
                return true;
            } else putFork(leftFork);
        }
        return false;
    }

    private boolean takeFork(Fork fork) {
        synchronized (this) {
            if (fork.isAvailable()) {
                fork.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    private void putFork(Fork fork) {
        synchronized (this) {
            fork.setAvailable(true);
        }
    }

    private void eating() {
        state = State.EATING;
        countCompletedMeal++;
        System.out.println(Thread.currentThread().getName() + " кушает "
                + countCompletedMeal + " раз");
        sleepMS(1000);
        putFork(leftFork);
        putFork(rightFork);
    }

    private void thinking() {
        state = State.THINKING;
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
