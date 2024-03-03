package org.example.Tasks1_6;
import java.util.concurrent.atomic.AtomicInteger;
public class Task5 {
    public static class AtomicExample {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public void decrement() {
            count.decrementAndGet();
        }

        public int getCount() {
            return count.get();
        }
    }
    public static void main(String[] args) {
        AtomicExample atomicExample = new AtomicExample();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                atomicExample.increment();
            }
        });

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                atomicExample.decrement();
            }
        });

        thread.start();
        thread1.start();

        try{
            thread.join();
            thread1.join();
        }catch (InterruptedException ignored){
        }
        System.out.println(atomicExample.getCount());
    }
}

