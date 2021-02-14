package com.javarush.task.task28.task2805;

public class MyThread extends Thread{
    static int priority = 1;

    public MyThread() {
        setPriority(priority);
        priority++;
        if (priority > 10) priority = 1;
    }

    public MyThread(Runnable target) {
        super(target);
        setPriority(priority);
        priority++;
        if (priority > 10) priority = 1;
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPriority(Math.min(priority, group.getMaxPriority()));
        priority++;
        if (priority > 10) priority = 1;
    }

    public MyThread(String name) {
        super(name);
        setPriority(priority);
        priority++;
        if (priority > 10) priority = 1;
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPriority(Math.min(priority, group.getMaxPriority()));
        priority++;
        if (priority > 10) priority = 1;
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPriority(priority);
        priority++;
        if (priority > 10) priority = 1;
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPriority(Math.min(priority, group.getMaxPriority()));
        priority++;
        if (priority > 10) priority = 1;
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPriority(Math.min(priority, group.getMaxPriority()));
        priority++;
        if (priority > 10) priority = 1;
    }

}
