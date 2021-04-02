package locks;

class Worker extends Thread {
  private final static int DELAY = 100;

  private final int limit;
  private final int id;
  private final Counter counter;

  Worker(Counter counter, int id, int limit) {
    this.limit = limit;
    this.counter = counter;
    this.id = id;
  }

  public void run() {
    int i = 0;

    while (i < limit) {
      i = counter.getandinc();
      System.out.println(String.format("Thread %d: %d", id, i));
      try {
        Thread.sleep(DELAY);
      } catch (Exception e) {
      }
    }
  }
}
