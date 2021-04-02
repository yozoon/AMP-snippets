package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
  static final int numThreads = 4;
  static final int limit = 100;

  public static void main(String[] args) {
    // Lock lock = new Peterson(numThreads); // Only works with numThreads=2
    Lock lock = new Filter(numThreads);

    Counter counter = new Counter(lock);

    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    for (int i = 0; i < numThreads; i++) {
      executor.execute(new Worker(counter, i, limit));
    }

    executor.shutdown();
  }
}
