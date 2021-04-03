package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
  private static int numThreads = 4;
  private static int limit = 100;

  public static void main(String[] args) {
    if(args.length > 0) {
      try {
        numThreads = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        System.out.println(e.toString());
        System.exit(-1);
      }
    }

    if(args.length > 1) {
      try {
        limit = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.out.println(e.toString());
        System.exit(-1);
      }
    }
  
    //Lock lock = new Peterson(numThreads); // Only works with numThreads=2
    Lock lock = new Filter(numThreads);

    Counter counter = new Counter(lock);

    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    for (int i = 0; i < numThreads; i++) {
      executor.execute(new Worker(counter, i, limit));
    }

    executor.shutdown();
  }
}
