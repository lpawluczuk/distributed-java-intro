package exercise3;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; ++i) {
            threads[i] = new Thread(new MyRunnable(), "thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("FINISHED");
    }

}
