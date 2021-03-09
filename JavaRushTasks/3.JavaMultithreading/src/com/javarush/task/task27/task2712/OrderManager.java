package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    @Override
    public void update(Observable o, Object arg) {
        orderQueue.add((Order) arg);
    }

    public OrderManager() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StatisticManager manager = StatisticManager.getInstance();
                while(true){
                        for (Cook cook : manager.getCooks()) {
                            if (!cook.isBusy() && !orderQueue.isEmpty()) {
                                cook.startCookingOrder(orderQueue.poll());
                            }
                        }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
