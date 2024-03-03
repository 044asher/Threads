package org.example.Tasks1_6;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Task4 {
    public static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }

        public synchronized void decrement() {
            count--;
        }

        public synchronized int getCount() {
            return count;
        }
    }



        public static void main(String[] args) {
            ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    map.put(String.valueOf(i), "one" + i);
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 10; i < 20; i++) {
                    map.put(String.valueOf(i), "two" + i);
                }
            });


            thread1.start();
            thread2.start();


            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            for (int i = 0; i < 20; i++) {
                System.out.println("Key: " + i + ", Value: " + map.get(String.valueOf(i)));
            }




            Counter counter = new Counter();


            Thread thread3 = new Thread(() -> {
                for (int i = 0; i < 15; i++) {
                    counter.increment();
                }
            });

            Thread thread4 = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    counter.decrement();
                }
            });


            thread3.start();
            thread4.start();


            try {
                thread3.join();
                thread4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println("Current count: " + counter.getCount());
        }
}




