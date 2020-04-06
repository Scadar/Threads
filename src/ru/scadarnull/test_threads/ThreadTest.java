package ru.scadarnull.test_threads;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ThreadTest extends Thread{

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
        Thread th = new ThreadTest();
        th.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main finished");
    }
}
