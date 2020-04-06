package ru.scadarnull.test_threads;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class SyncExampleTest {
    public static void main(String[] args) throws InterruptedException {
        final SyncExample syncExample = new SyncExample();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                syncExample.addString(String.valueOf(i));
            }
        }, "Producer");

        Thread printer = new Thread(() -> {
            while (!Thread.interrupted()){
                String str = syncExample.getString();
                if(str != null){
                    System.out.println(str);
                }else{
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        },
                "Printer");

        producer.start();
        printer.start();
        System.out.println("Threads started");
        TimeUnit.SECONDS.sleep(5);
        printer.interrupt();
        System.out.println("Threads stopped");
    }
}
