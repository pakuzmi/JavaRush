package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    Thread thread;

    @Override
    public void run() {
        while (!thread.isInterrupted()){
            try {
                System.out.println(thread.getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public void start(String threadName) {
        Thread thread = new Thread(this,threadName);
        this.thread = thread;
        thread.start();
        //run();

    }
}
