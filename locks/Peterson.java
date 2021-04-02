package locks;

class Peterson implements Lock {
  private final int n;

  private boolean[] flag = new boolean[2];
  private volatile int victim;

  Peterson(int n) { this.n = n; }

  @Override
  public void lock() {
    int i = (int)Thread.currentThread().getId() % n;
    int j = 1 - i; // other thread
    flag[i] = true;
    victim = i;
    while (flag[j] && victim == i) {
    } // wait
  }

  @Override
  public void unlock() {
    int i = (int)Thread.currentThread().getId() % n + 1;
    flag[i] = false;
  }
}