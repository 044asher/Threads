package org.example;

public class Task7_1 {
        private static final Object monitor = new Object();
        private static volatile int currentNumber = 1;

        public static void main(String[] args) {
            Thread threadA = new Thread(() -> printLetter("A", 1));
            Thread threadB = new Thread(() -> printLetter("B", 2));
            Thread threadC = new Thread(() -> printLetter("C", 3));

            threadA.start();
            threadB.start();
            threadC.start();
        }

        private static void printLetter(String letter, int threadNumber) {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (monitor) {
                        while (currentNumber != threadNumber) {
                            monitor.wait();
                        }
                        System.out.print(letter);
                        currentNumber = currentNumber % 3 + 1;
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

