package thread.thinker;

public class Fork {
    // private volatile boolean available;
    private boolean available; // доступный
    private final int placeID;

    public Fork(int placeID) {
        this.placeID = placeID;
        available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "{fID" + placeID +
                ", " + available + '}';
    }
}
