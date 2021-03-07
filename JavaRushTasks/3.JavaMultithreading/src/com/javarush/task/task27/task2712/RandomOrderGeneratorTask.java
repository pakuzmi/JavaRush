package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private final List<Tablet> tablets;
    private final int ORDER_CREATING_INTERVAL;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int ORDER_CREATING_INTERVAL) {
        this.tablets = tablets;
        this.ORDER_CREATING_INTERVAL = ORDER_CREATING_INTERVAL;
    }

    @Override
    public void run() {
        Tablet tablet = tablets.get((int) (Math.random() * tablets.size()));
        try{
            while (true){
                Thread.sleep(ORDER_CREATING_INTERVAL);
                tablet.createOrder();
            }
        } catch (Exception e){
            Thread.currentThread().interrupt();
        }


    }
}
