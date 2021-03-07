package com.javarush.task.task27.task2712.ad;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static final StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private final AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();


    private StatisticAdvertisementManager(){
    }

    public static StatisticAdvertisementManager getInstance(){
        return instance;
    }

    /*public List<Advertisement> getVideo(boolean isActive){
        List<Advertisement> fullList = advertisementStorage.list();

*//*        //Хардкодим
        List<Advertisement> fullList = new ArrayList<>();
        Object someContent = new Object();
        fullList.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        fullList.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        fullList.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
        fullList.add(new Advertisement(someContent, "четвертое видео", 5000, 100, 4 * 60));
        fullList.add(new Advertisement(someContent, "пятое видео", 5000, 23, 4 * 60));
        fullList.add(new Advertisement(someContent, "fourth Video", 400, 0, 10 * 60));
        fullList.add(new Advertisement(someContent, "five видео", 5000, 0, 4 * 60));
        fullList.add(new Advertisement(someContent, "шестое видео", 5000, 0, 4 * 60));*//*

        List<Advertisement> result;
        if (isActive) {
            result = fullList.stream().filter(x -> (x.getHits() > 0)).collect(Collectors.toList());
        } else {
            result = fullList.stream().filter(x -> (x.getHits() <= 0)).collect(Collectors.toList());
        }
        return result;
    }*/

    public Map<String,Integer> getVideoSet()
    {
        Map<String,Integer> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        for(Advertisement a : advertisementStorage.list())
        {
            map.put(a.getName(),a.getHits());
        }
        return map;
    }
}
