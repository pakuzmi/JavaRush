package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Cook firstCook = new Cook("Amigo");
        Cook secondCook = new Cook("Pavel");
        firstCook.setQueue(ORDER_QUEUE);
        secondCook.setQueue(ORDER_QUEUE);
        firstCook.addObserver(new Waiter());
        secondCook.addObserver(new Waiter());
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            Tablet tablet = new Tablet(i);
            tablet.setQueue(ORDER_QUEUE);
            tablets.add(tablet);
        }
        Thread threadFirstCook = new Thread(firstCook);
        threadFirstCook.setDaemon(true);
        Thread threadSecondCook = new Thread(secondCook);
        threadSecondCook.setDaemon(true);
        threadFirstCook.start();
        threadSecondCook.start();
        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
