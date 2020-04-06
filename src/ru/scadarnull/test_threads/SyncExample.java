package ru.scadarnull.test_threads;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SyncExample {
    private List<String> list= new LinkedList<>();
    private int count = 0;
    public synchronized void addString(String str){
        count++;
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add(str);
    }

    public synchronized String getString(){
        if(count > 0){
            --count;
            return list.remove(0);
        }else{
            return null;
        }
    }
}
