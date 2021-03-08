package com.javarush.task.task27.task2712.kitchen;
import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
        //initDishes();
    }

    @Override
    public String toString() {
        if (dishes.size() > 0){
        StringBuilder result = new StringBuilder();
        result.append("Your order: [");
        int i = 1;
        for (Dish dish : dishes){
            result.append(dish.name());
            if (i < dishes.size()){
                result.append(", ");
            }
            i++;
        }
        result.append("] of ");
        result.append(tablet.toString());
        result.append(", cooking time ");
        result.append(getTotalCookingTime());
        result.append("min");

        return result.toString();
        }
        return "";
    }

    public int getTotalCookingTime(){
        int result = 0;
        for (Dish dish : dishes){
            result += dish.getDuration();
        }
        return result;
    }

    public boolean isEmpty(){
        return dishes.size() == 0;
    }

    protected void initDishes() throws IOException{
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
