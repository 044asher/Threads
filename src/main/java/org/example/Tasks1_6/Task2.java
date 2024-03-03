package org.example.Tasks1_6;

public class Task2 {
    public static class MyThread extends Thread {
        private volatile boolean running = true;

//        public void run() {
//            while (running) {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " still working");
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.err.println("Thread has been interrupted");
//                }
//            }
//        }

        public void run() {
            while (!isInterrupted()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " still working");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Thread has been interrupted");
                    return;
                }
            }
        }

        public void stopThread() {
            running = false;
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}
