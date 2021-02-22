package com.javarush.task.task27.task2712.kitchen;
import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
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
        return result.toString();
        }
        return "";
    }
}
