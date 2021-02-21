package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    FISH,
    STEAK,
    SOUP,
    JUICE,
    WATER;


    public static String allDishesToString(){
        StringBuilder result = new StringBuilder();
        for (Dish value : Dish.values()){
            result.append(value.toString() + " ");
        }
        return result.toString().trim();
    }
}
