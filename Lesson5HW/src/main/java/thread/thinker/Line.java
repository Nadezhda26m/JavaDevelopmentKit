package thread.thinker;

public class Line extends Thread {
    private boolean stop;

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            while (!stop) {
                System.out.println("--------------------");
                Thread.sleep(1000);
            }
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
