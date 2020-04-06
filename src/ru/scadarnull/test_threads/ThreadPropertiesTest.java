package ru.scadarnull.test_threads;

import javax.management.RuntimeErrorException;
import java.util.concurrent.TimeUnit;

public class ThreadPropertiesTest {
    public static void main(String[] args) {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException();
            }
        });

        th.setDaemon(false); //Деманичный поток - неполноценный (Программа завершается, когда не остается не деманических потоков)
        th.setName("Test thread");
        th.setPriority(Thread.MAX_PRIORITY);//Приоритет потока
        th.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { //Обрабатывает исключения, которые выюрасывает поток
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println("Exception in the thread: " + t);
                e.printStackTrace();
            }
        });
        th.start();
        System.out.println("Started");
    }
}
