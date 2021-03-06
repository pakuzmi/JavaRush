package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;

    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        if (hits != 0){
            amountPerOneDisplaying = initialAmount / hits;
        } else {
            amountPerOneDisplaying = 0;
        }

    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void revalidate() throws NoVideoAvailableException{
        if (hits <= 0){
            throw new NoVideoAvailableException();
        }
        hits--;
    }

    public long getAmountPerOneSecondDisplaying(){
        return amountPerOneDisplaying * 1000 / duration;
    }

    @Override
    public String toString() {
        return name + " is displaying... " + amountPerOneDisplaying + ", " + getAmountPerOneSecondDisplaying();
    }

    public int getHits() {
        return hits;
    }
}
