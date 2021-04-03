package locks;

class Peterson implements Lock {
  private final int n;

  private volatile boolean[] flag = new boolean[2];
  private volatile int victim;

  Peterson(int n) { 
    if(n != 2) {
      throw new IllegalArgumentException("Peterson lock only supports 2 threads!");
    }
    this.n = n; 
  }

  @Override
  public void lock() {
    int i = (int) (Thread.currentThread().getId() % n);
    int j = 1 - i; // other thread
    flag[i] = true;
    victim = i;
    while (flag[j] && victim == i) {
    } // wait
  }

  @Override
  public void unlock() {
    int i = (int) (Thread.currentThread().getId() % n);
    flag[i] = false;
  }
}