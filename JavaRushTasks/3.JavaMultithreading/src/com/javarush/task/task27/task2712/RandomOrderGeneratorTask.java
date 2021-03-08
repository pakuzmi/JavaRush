package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private final List<Tablet> tablets;
    private final int ORDER_CREATING_INTERVAL;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.ORDER_CREATING_INTERVAL = interval;
    }

    @Override
    public void run() {
        Tablet tablet = tablets.get((int) (Math.random() * tablets.size()));
        try{
            while (true){
                Thread.sleep(ORDER_CREATING_INTERVAL);
                tablet.createTestOrder();
            }
        } catch (Exception e){
            Thread.currentThread().interrupt();
        }


    }
}
