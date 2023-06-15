package tracker;

public class PetitionTrackerImp {

    private int count;

    public PetitionTrackerImp() {
        this.count = 0;
    }

    public synchronized void increaseCount() {
        count ++;
    }

    public synchronized void decreaseCount() {
        count --;
    }

    public int getCount() {
        return this.count;
    }

    public synchronized void setCount(int count) {
        this.count = count;
    }

}
