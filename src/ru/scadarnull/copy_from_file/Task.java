package ru.scadarnull.copy_from_file;

public interface Task {
    void start();
    void interrupt();
    int getPercentProgress();
}
