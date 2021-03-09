package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order{
    @Override
    protected void initDishes() {
        /*dishes = new ArrayList<>();
        int count = (int) (Math.random() * 5);
        while (count > 0){
            int type = (int) (Math.random() * 5);
            count--;
            switch (type){
                case 1:{
                    dishes.add(Dish.WATER);
                    break;
                }
                case 2:{
                    dishes.add(Dish.JUICE);
                    break;
                }
                case 3:{
                    dishes.add(Dish.SOUP);
                    break;
                }
                case 4:{
                    dishes.add(Dish.FISH);
                    break;
                }
                case 5:{
                    dishes.add(Dish.STEAK);
                    break;
                }
            }
        }*/

            dishes = new ArrayList<>();
            int countOfDishes = (int) (Math.random() * Dish.values().length);
            for (int i = 0; i < countOfDishes; i++) {
                dishes.add(Dish.values()[(int) (Math.random() * Dish.values().length)]);
            }

    }

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

}
