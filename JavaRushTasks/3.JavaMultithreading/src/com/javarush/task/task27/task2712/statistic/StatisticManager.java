package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


public class StatisticManager {
    private static StatisticManager instance;
    private StatisticManager.StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    public static StatisticManager getInstance(){
        if (instance == null){
            instance = new StatisticManager();
        }
        return instance;
    }

    public Map<String, Long> getVideoSelectedData(){
        Map<String, Long> result = new TreeMap<>(Collections.reverseOrder());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        for (EventDataRow eventDataRow : statisticStorage.getEventDataRow(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            String dateKey = format.format(videoSelectedEventDataRow.getDate());
            long amount = videoSelectedEventDataRow.getAmount();
            if (!result.containsKey(dateKey)) result.put(dateKey, amount);
            else result.put(dateKey, result.get(dateKey) + amount);
        }
        return result;
    }

    public Map<LocalDate, Map<String, Integer>> getCookedOrderData(){
        /*// Фрагмент для теста, потом удалить!!!!!
        List<EventDataRow> list = new ArrayList<>();
        try {
            list.add (new CookedOrderEventDataRow("tablet1", "Pasha", 545, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2016")));
            list.add (new CookedOrderEventDataRow("tablet1", "Pasha", 73, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2016")));
            list.add (new CookedOrderEventDataRow("tablet1", "Vasya", 222, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2016")));
            list.add (new CookedOrderEventDataRow("tablet1", "Igor", 111, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2016")));
            list.add (new CookedOrderEventDataRow("tablet1", "Igor", 545, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2019")));
            list.add (new CookedOrderEventDataRow("tablet1", "Vasya", 70, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2019")));
            list.add (new CookedOrderEventDataRow("tablet1", "Vasya", 212, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2019")));
            list.add (new CookedOrderEventDataRow("tablet1", "Pasha", 100, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2019")));
            list.add (new CookedOrderEventDataRow("tablet1", "Vasya", 222, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.2011")));
            list.add (new CookedOrderEventDataRow("tablet1", "Pasha", 133, new ArrayList<Dish>(),
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("28.12.1988")));
        } catch (Exception e){
            e.printStackTrace();
        }*/

        //Формируем list из архива
        List<EventDataRow> list = statisticStorage.getEventDataRow(EventType.COOKED_ORDER);
        // Объявляем Map результата
        Map<LocalDate, Map<String, Integer>> result = new HashMap<>();
        // Группируем входной список по дате
        Map <LocalDate, List<EventDataRow>> groupingMap = list.stream().collect(Collectors.groupingBy(request ->
                request.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        // Идем по сгруппированной Map и для каждой даты:
        for (Map.Entry<LocalDate, List<EventDataRow>> entry : groupingMap.entrySet()){
            //Формируем Map группировкой по имени повара
            Map <String, List<EventDataRow>> groupedMap = entry.getValue().stream().collect(Collectors.groupingBy(reqest ->
                    ((CookedOrderEventDataRow) reqest).getCookName()));
            int sumTime = 0;
            Map <String, Integer> mapCookAtDay = new HashMap<>();
            // Идем по сгруппированной Map и для каждго повара
            for (Map.Entry<String, List<EventDataRow>> entryAtDay : groupedMap.entrySet()){
                // Считаем его суммарное время работы
                sumTime = entryAtDay.getValue().stream().mapToInt((s) -> ((CookedOrderEventDataRow) s).getTime()).sum();
                // Добавлем в ежедневную Map имя повара и общее время его работы
                mapCookAtDay.put(entryAtDay.getKey(), sumTime);
            }
            // Формируем итоговый Map длбаляя дату и для кажой даты map с именами поваров и их суммарным временем
            result.put(entry.getKey(), mapCookAtDay);
        }
     return result;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()){
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            EventType type = data.getType();
            if (storage.containsKey(type)){
                List<EventDataRow> list = storage.get(type);
                list.add(data);
            }
        }

        public List<EventDataRow> getEventDataRow (EventType eventType){
            if (storage.containsKey(eventType)){
                return storage.get(eventType);
            }
            return null;
        }
    }

}
