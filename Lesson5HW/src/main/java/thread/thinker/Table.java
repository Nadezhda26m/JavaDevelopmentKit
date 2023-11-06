package thread.thinker;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Table extends Thread {
    private final int COUNT_ELEMENTS = 5;
    private ArrayList<Fork> forks;
    private ArrayList<Thinker> thinkers;
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
        thread.setStop(!thread.isStop());
    }

    private void init() {
        putForksOnTable();
        placePeople();
    }

    private void putForksOnTable() {
        forks = new ArrayList<>(COUNT_ELEMENTS);
        for (int i = 1; i <= COUNT_ELEMENTS; i++) {
            forks.add(new Fork(i));
        }
    }

    private void placePeople() {
        thinkers = new ArrayList<>(COUNT_ELEMENTS);
        thinkers.add(new Thinker(1, forks.get(0), forks.get(COUNT_ELEMENTS - 1), cdl));
        for (int i = 1; i < COUNT_ELEMENTS; i++) {
            thinkers.add(new Thinker(i + 1, forks.get(i), forks.get(i - 1), cdl));
        }
    }
}
