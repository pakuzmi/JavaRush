package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.ParseException;

public class Restaurant {
    public static void main(String[] args) throws ParseException {
/*        DirectorTablet tablet = new DirectorTablet();
        tablet.printActiveVideoSet();
        tablet.printArchivedVideoSet();
        System.out.println("fin");*/

        Tablet tablet = new Tablet(12);
        Cook cook = new Cook("Amigo");

        tablet.addObserver(cook);

        tablet.createOrder();

        cook.addObserver(new Waiter());

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
