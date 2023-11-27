package thread.thinker;

import java.util.concurrent.CountDownLatch;

public class Table extends Thread {
    private final int COUNT_ELEMENTS = 5;
    private Fork[] forks;
    private Thinker[] thinkers;
    private CountDownLatch cdl;

    public Table() {
        cdl = new CountDownLatch(COUNT_ELEMENTS);
        init();
    }

    @Override
    public void run() {
        for (Thinker thinker : thinkers) {
            thinker.start();
        }
        Line thread = new Line();
        thread.start();

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Все поели");
        thread.setStop(!thread.isStop());
    }

    public synchronized boolean tryGetForks(int leftFork, int rightFork) {
        if (forks[leftFork].isAvailable() && forks[rightFork].isAvailable()) {
            forks[leftFork].setAvailable(false);
            forks[rightFork].setAvailable(false);
            return true;
        }
        return false;
    }

    public void putForks(int leftFork, int rightFork){
        forks[leftFork].setAvailable(true);
        forks[rightFork].setAvailable(true);
    }

    private void init() {
        putForksOnTable();
        placePeople();
    }

    private void putForksOnTable() {
        forks = new Fork[COUNT_ELEMENTS];
        for (int i = 0; i < COUNT_ELEMENTS; i++) {
            forks[i] = new Fork(i + 1);
        }
    }

    private void placePeople() {
        thinkers = new Thinker[COUNT_ELEMENTS];
        for (int i = 0; i < COUNT_ELEMENTS; i++) {
            thinkers[i] = new Thinker(i + 1, this,
                    i, (i + 1) % COUNT_ELEMENTS, cdl);
        }
    }
}