package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue queue;

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order){
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order.toString());
        try {
            Thread.sleep(order.getTotalCookingTime() * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public void run() {
        StatisticManager manager = StatisticManager.getInstance();
        while (true) {
            if (!isBusy() && !queue.isEmpty()) {
                startCookingOrder((Order) queue.poll());
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
