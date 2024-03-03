package org.example.Tasks1_6;

public class Task1 {
    public static class MyThread extends Thread {
        MyThread(String name){
            super(name);
        }
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + " started");
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                System.err.println("Thread has been interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }


    public static void main(String[] args) {
        Thread thread = new MyThread("MyThread");
        thread.start();




        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        Thread thread1 = new Thread(task);
        thread1.start();


        Runnable task1 = () -> System.out.println("Hello Everybody");
        Thread thread2 = new Thread(task1);
        thread2.start();



    }
}
