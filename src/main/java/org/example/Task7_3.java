package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Task7_3 {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        List<Integer> newList = new CopyOnWriteArrayList<>();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                newList.add(random.nextInt());
            }
        });

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                newList.add(random.nextInt());
            }
        });

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
        System.out.println(newList.size());

    }
}
