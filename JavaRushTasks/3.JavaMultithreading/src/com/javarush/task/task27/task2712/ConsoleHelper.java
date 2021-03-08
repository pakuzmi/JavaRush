package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> order = new ArrayList<>();
        writeMessage("Меню:");
        writeMessage("=============================================================");
        writeMessage(Dish.allDishesToString());
        writeMessage("=============================================================");
        writeMessage("Введите блюдо из меню для добавления его в заказ (exit для завершения выбора).");
        List<String> enumEntryString = new ArrayList<>();
        for (Dish dish : Dish.values()){
            enumEntryString.add(dish.name());
        }
        while (true) {
            String choice = readString();
            if (choice.equals("exit")) {
                break;
            }
            if (enumEntryString.contains(choice)){
                order.add(Dish.valueOf(choice));
            } else {
                writeMessage("Блюда " + choice + " нет в списке, повторите ввод.");
            }
        }
        return order;
    }
}
