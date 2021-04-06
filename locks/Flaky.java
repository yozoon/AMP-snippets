package locks;

class Flaky implements Lock {
    private volatile int turn;
    private volatile boolean busy = false;
    private int n;

    Flaky(int n) {
        this.n = n;
    }

    @Override
    public void lock() {
        int me = (int) (Thread.currentThread().getId() % n);
        do {
            do {
                turn = me;
            } while (busy);
                busy = true;
        } while (turn != me);
    }
    @Override
    public void unlock() {
        busy = false;
    }
}