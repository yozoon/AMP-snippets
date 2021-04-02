package locks;

class Counter {
  private int value;
  private Lock lock; // use lock for CS

  public Counter(Lock lock) { // constructor
    value = 0;
    this.lock = lock;
  }
  public int getandinc() {
    int temp;
    lock.lock(); // enter CS
    try {
      temp = value; // increment alone
      value = temp + 1;
      return temp;
    } finally {
      lock.unlock(); // leave CS
    }
  }
}