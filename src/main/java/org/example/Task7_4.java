package org.example;

import java.util.concurrent.*;
import java.util.Random;

public class Task7_4 {

    private static final int NUM_CARS = 10;
    private static final int TUNNEL_CAPACITY = 3;
    private static final Random random = new Random();

    private static final Semaphore tunnelSemaphore = new Semaphore(TUNNEL_CAPACITY);
    private static final CyclicBarrier startBarrier = new CyclicBarrier(NUM_CARS + 1);
    private static final CountDownLatch finishLatch = new CountDownLatch(NUM_CARS);
    private static long startTime;
    private static final long[] finishTimes = new long[NUM_CARS];

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_CARS);

        for (int i = 0; i < NUM_CARS; i++) {
            executorService.submit(new Car(i));
        }

        // Ждем подготовки
        try {
            startBarrier.await();
        } catch (InterruptedException | BrokenBarrierException ignored) {
        }
        startTime = System.nanoTime();

        // Ждем финиша
        try {
            finishLatch.await();
        } catch (InterruptedException ignored) {
        }

        // Результаты
        System.out.println("Results:");
        for (int i = 0; i < NUM_CARS; i++) {
            long raceTime = (finishTimes[i] - startTime) / 1_000_000;
            System.out.println("Auto " + i + ": " + raceTime + " ms");
        }

        executorService.shutdown();
    }

    private static class Car implements Runnable {

        private final int id;

        public Car(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            // Подготовка
            long preparationTime = random.nextInt(5000) + 2000;
            try {
                Thread.sleep(preparationTime);
                System.out.println("Auto " + id + " prepared");
            } catch (InterruptedException ignored) {
            }

            // ждем начала
            try {
                startBarrier.await();
            } catch (InterruptedException | BrokenBarrierException ignored) {
            }



            // обычная дорога
            long roadTime = random.nextInt(3000);
            try {
                Thread.sleep(roadTime);
            } catch (InterruptedException ignored){
            }

            // пытаемся въехать в туннель
            try {
                tunnelSemaphore.acquire();
                System.out.println("Auto " + id + " entered the tunnel");
            } catch (InterruptedException ignored) {
            }

            // едем через туннель
            long tunnelTime = random.nextInt(2000) + 1000; // добавляем случайность, чтобы увеличить разрыв
            try {
                Thread.sleep(tunnelTime);
            } catch (InterruptedException ignored) {
            }

            // освобождаем туннель
            System.out.println("Auto " + id + " has exited the tunnel");
            tunnelSemaphore.release();


            // обычная дорога перед финишем
            long roadToFinishTime = random.nextInt(3000);
            try {
                Thread.sleep(roadToFinishTime);
            } catch (InterruptedException ignored) {
            }

            // Финиш
            finishTimes[id] = System.nanoTime();
            finishLatch.countDown();
            System.out.println("Auto " + id + " finished");
        }
    }
}
