package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Task7_2 {
    private static final Map<Integer, String> users = new HashMap<>();

    public static void main(String[] args) {
        users.put(1, "Anna");
        users.put(2, "Lucas");
        users.put(3, "Tina");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user number: ");
        int userId = scanner.nextInt();

        UserNameCallable userNameCallable = new UserNameCallable(userId);
        FutureTask<String> future = new FutureTask<>(userNameCallable);
        Thread userThread = new Thread(future);

        Thread loadingThread = new Thread(() -> {
            try {
                while (!future.isDone()) {
                    for (int i = 0; i < 4; i++) {
                        System.out.print(".");
                        Thread.sleep(500);
                    }
                    System.out.print("\r      \r");
                }
            } catch (InterruptedException ignored) {

            }
        });
        loadingThread.setDaemon(true);

        userThread.start();
        loadingThread.start();

        try {
            String userName = future.get();
            System.out.println("\n User name: " + userName);
        } catch (InterruptedException | ExecutionException ignored) {
        }
    }

    static class UserNameCallable implements Callable<String> {
        private final int userId;

        public UserNameCallable(int userId) {
            this.userId = userId;
        }

        @Override
        public String call() throws InterruptedException {
            Thread.sleep(6000);
            return users.get(userId);
        }
    }
}
