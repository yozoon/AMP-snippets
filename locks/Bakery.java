package locks;

class Bakery implements Lock {
    private volatile boolean[] flag;
    // MRSW Label
    private volatile int[] label;

    private final int n;

    Bakery(int n) {
        this.n = n;
        label = new int[n];
        flag = new boolean[n];
    }

    @Override
    public void lock() {
        int i = (int) (Thread.currentThread().getId() % n);
        flag[i] = true;

        // Create a ticket that has a value larger than all current labels
        int max = label[0];
        for(int k=0; k<n; k++) {
            if(label[k] > max)
            max = label[k];
        }
        label[i] = max + 1;

        for (int k=0; k<n; k++) {
            if (k==i) continue;
            while (flag[k] && (label[k] < label[i] || (label[k] == label[i] && k<i)));
        }
    }

    @Override
    public void unlock() {
        int i = (int) (Thread.currentThread().getId() % n);
        flag[i] = false;
    }
}