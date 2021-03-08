package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;


import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) throws InterruptedException {
/*        Tablet tablet = new Tablet(12);
        Cook cook = new Cook("Amigo");
        cook.addObserver(new Waiter());
        tablet.addObserver(cook);

        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();
        tablet.createOrder();*/
        Cook firstCook = new Cook("Amigo");
        Cook secondCook = new Cook("Pavel");
        firstCook.addObserver(new Waiter());
        secondCook.addObserver(new Waiter());
        StatisticManager manager = StatisticManager.getInstance();
        manager.register(firstCook);
        manager.register(secondCook);
        List<Tablet> tablets = new ArrayList<>();
        OrderManager orderManager = new OrderManager();
        for (int i = 1; i <= 5; i++){
            Tablet tablet = new Tablet(i);
            tablet.addObserver(orderManager);
            tablets.add(tablet);
        }
        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
