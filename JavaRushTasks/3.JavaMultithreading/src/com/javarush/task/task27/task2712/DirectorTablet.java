package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class DirectorTablet {
    public void printAdvertisementProfit() throws ParseException {
        Map<String, Long> map = StatisticManager.getInstance().getVideoSelectedData();
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Long total = 0L;
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            Date date = oldFormat.parse(entry.getKey());
            total += entry.getValue();
            if (entry.getValue() > 0) {
                ConsoleHelper.writeMessage(String.format("%s - %.2f", newFormat.format(date), entry.getValue() / 100d).replaceAll(",","."));
            }
        }
        if (total > 0) ConsoleHelper.writeMessage(String.format("Total - %.2f", total / 100d).replaceAll(",", "."));

    }

    public void printCookWorkloading(){
        Map <LocalDate, Map <String, Integer>> map = StatisticManager.getInstance().getCookedOrderData();
        LinkedHashMap<LocalDate, Map <String, Integer>> sortedMap = map
                .entrySet()
                .stream()
                .sorted(comparingByKey(new Comparator<LocalDate>() {
                    @Override
                    public int compare(LocalDate o1, LocalDate o2) {
                        return o2.compareTo(o1);
                    }
                }))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        for (Map.Entry<LocalDate, Map<String, Integer>> entry : sortedMap.entrySet()){
            LocalDate localDate = entry.getKey();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            ConsoleHelper.writeMessage(simpleDateFormat.format(date).toString());
            Map<String, Integer> mapAtDay = entry.getValue();
            SortedSet<String> keys = new TreeSet<String>(mapAtDay.keySet());
            for (String key : keys) {
                Integer value = Math.round( mapAtDay.get(key) / 60f);
                if (value > 0) ConsoleHelper.writeMessage(String.format("%s - %d min", key, value));
            }
        }
    }

    public void printActiveVideoSet(){
/*        StatisticAdvertisementManager manager = StatisticAdvertisementManager.getInstance();
        List<Advertisement> list = manager.getVideo(true);
        list.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        list.forEach(x -> ConsoleHelper.writeMessage(String.format("%s - %s", x.getName(), x.getHits())));*/

        Map<String,Integer> map = StatisticAdvertisementManager.getInstance().getVideoSet();
        for(Map.Entry<String,Integer> entry : map.entrySet())
        {
            if(entry.getValue() != 0)
                ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void printArchivedVideoSet(){
/*        StatisticAdvertisementManager manager = StatisticAdvertisementManager.getInstance();
        List<Advertisement> list = manager.getVideo(false);
        list.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        list.forEach(x -> ConsoleHelper.writeMessage(x.getName()));*/

        ConsoleHelper.writeMessage("");
        Map<String,Integer> map = StatisticAdvertisementManager.getInstance().getVideoSet();
        for(Map.Entry<String,Integer> entry : map.entrySet())
        {
            if(entry.getValue() == 0)
                ConsoleHelper.writeMessage(entry.getKey());
        }
    }
}
