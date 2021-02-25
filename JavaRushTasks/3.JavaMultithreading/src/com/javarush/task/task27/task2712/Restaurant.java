package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(12);
        Cook cook = new Cook("Amigo");
        tablet.addObserver(cook);
        tablet.createOrder();
        cook.addObserver(new Waiter());
    }
}
