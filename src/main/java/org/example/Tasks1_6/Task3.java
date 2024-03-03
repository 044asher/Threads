package org.example.Tasks1_6;

public class Task3 {
    static class DataProcessingRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("Processing data in background...");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Thread has been interrupted");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread daemonThread = new Thread(new DataProcessingRunnable());
        daemonThread.setDaemon(true);
        daemonThread.start();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
             try {
                    Thread.sleep(500);
             } catch (InterruptedException ignored) {}
                }


        };
        Thread thread1 = new Thread(task);
        thread1.start();

    }
}

