import java.util.Random;

public class Main {
    static int x = 0;
    static final int ITERATIONS = 1000;

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new Peterson();

        Thread t0 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                lock.requestCS(0);
                x = x + 1;
                lock.releaseCS(0);
            }
        });

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                lock.requestCS(1);
                x = x + 1;
                lock.releaseCS(1);
            }
        });

        t0.start();
        t1.start();
        t0.join();
        t1.join();

        System.out.println("Final value of x: " + x); // Expected: 2000
    }
}
