package thread.thinker;

public class Line extends Thread {
    private boolean stop;

    @Override
    public void run() {
        sleepMS(100);
        while (!stop) {
            System.out.println("--------------------");
            sleepMS(1000);
        }
    }

    private void sleepMS(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
