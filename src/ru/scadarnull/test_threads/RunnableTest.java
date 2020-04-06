package ru.scadarnull.test_threads;

import java.util.concurrent.TimeUnit;

public class RunnableTest implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Run test finished");
    }

    public static void main(String[] args) {
        Thread th = new Thread(new RunnableTest(), "RunnableTest");
        th.start();

        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main finished");
    }
}
