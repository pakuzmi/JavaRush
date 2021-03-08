package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){
        try {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            if (!order.isEmpty()) {
                setChanged();
                notifyObservers(order);
            }
            AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime()*60);
            try{
                advertisementManager.processVideos();
            } catch (NoVideoAvailableException e){
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
            return order;
        } catch (IOException e){
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }



    public void createTestOrder(){
        try {
            TestOrder order = new TestOrder(this);
            prepareOrder(order);
        } catch (IOException e){
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void prepareOrder(Order order) {
        ConsoleHelper.writeMessage(order.toString());
        if (!order.isEmpty()) {
            setChanged();
            notifyObservers(order);
        }
        AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime()*60);
        try{
            advertisementManager.processVideos();
        } catch (NoVideoAvailableException e){
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }


}
