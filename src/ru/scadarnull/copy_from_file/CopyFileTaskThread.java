package ru.scadarnull.copy_from_file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class CopyFileTaskThread implements Task, Runnable{
    private Thread thread;
    private volatile long copied;
    private long sourceSize;
    Path sourcePath;
    Path destinationPath;

    public CopyFileTaskThread(Path sourcePath, Path destinationPath) throws IOException {
        copied = 0;
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
        this.sourceSize = Files.size(sourcePath);
        this.thread = new Thread(this, "Copy");
    }

    @Override
    public void start() {
        if(thread.getState() != Thread.State.NEW){
            throw new IllegalArgumentException("already");
        }
        this.thread.start();
    }

    @Override
    public void interrupt() {
        this.thread.interrupt();
    }

    @Override
    public int getPercentProgress() {
        return (int)(copied * 100 / sourceSize);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[8192];
        try(InputStream in = new FileInputStream(sourcePath.toFile());
            OutputStream out = new FileOutputStream(destinationPath.toFile())){
            while(!Thread.interrupted()){
                int read = in.read(buffer);
                if(read == -1){
                    break;
                }
                out.write(buffer, 0, read);
                copied += read;
            }
        } catch (IOException e) {
            System.err.println("Copy failed " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            CopyFileTaskThread test = new CopyFileTaskThread(Paths.get("test.txt"), Paths.get("test4.txt"));
            test.start();
            System.out.println(test.getPercentProgress());
            TimeUnit.MILLISECONDS.sleep(30);
            System.out.println(test.getPercentProgress());
            test.interrupt();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
