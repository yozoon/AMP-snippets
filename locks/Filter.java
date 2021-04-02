package locks;

class Filter implements Lock {
  private final int n;

  private int[] level;
  private int[] victim;

  Filter(int n) {
    this.n = n;
    level = new int[n];
    victim = new int[n];
  }

  @Override
  public void lock() {
    int i = (int) Thread.currentThread().getId() % n;

    for (int j = 1; j < n; j++) {
      level[i] = j;
      victim[j] = i;
      for (int k = 0; k < n; k++) {
        if (k == i)
          continue;
        while (level[k] >= j && victim[j] == i)
          ;
      }
    }

  }

  @Override
  public void unlock() {
    int i = (int) Thread.currentThread().getId() % n;
    level[i] = 0;
  }
}
