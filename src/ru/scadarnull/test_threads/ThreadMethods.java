package ru.scadarnull.test_threads;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ThreadMethods {
    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.dumpStack();
            }
        }, "runnable-thread");
        System.out.println("ThreadState = " + th.getState()); // Текущее состояние потока
        th.start();
        System.out.println("ThreadState = " + th.getState());
        Thread.sleep(1000); //Останавливает текущий поток
        System.out.println("ThreadId = " + th.getId());
        System.out.println("ThreadState = " + th.getState());
        System.out.println("ThreadState = " + Arrays.toString(th.getStackTrace()));
        Thread.dumpStack();//Формирует dump стека
        System.out.println("isAlive = " + th.isAlive());//Работает поток или нет
        th.join();//Останавливает поток из которого вызван метод join до тех пор, пока объект th не закончит работу
        System.out.println("Completed");
    }
}
