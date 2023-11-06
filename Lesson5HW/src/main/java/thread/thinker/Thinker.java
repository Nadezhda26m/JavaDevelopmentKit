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
            try {
                if (state == State.THINKING && takeForks()) {
                    eating();
                } else {
                    thinking();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cdl.countDown();
    }

    public State getStateMeal() {
        return state;
    }

    private boolean takeForks() {
        synchronized (this) {
            if (leftFork.isAvailable() && rightFork.isAvailable()) {
                leftFork.setAvailable(!leftFork.isAvailable());
                rightFork.setAvailable(!rightFork.isAvailable());
                return true;
            }
        }
        return false;
    }

    private synchronized void eating() throws InterruptedException {
        state = State.EATING;
        countCompletedMeal++;
        System.out.println(Thread.currentThread().getName() + " кушает "
                + countCompletedMeal + " раз");
        Thread.sleep(1000);
        leftFork.setAvailable(!leftFork.isAvailable());
        rightFork.setAvailable(!rightFork.isAvailable());
    }

    private void thinking() throws InterruptedException {
        state = State.THINKING;
        System.out.println(Thread.currentThread().getName() + " думает");
        Thread.sleep(1000);
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
