package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<Advertisement> bestAdvertisement;
    private long maxAmount = 0;
    private int maxDuration;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException{
        List<Advertisement> ads = new ArrayList<>();
        if(storage.list().isEmpty()){
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(maxDuration));
            throw new NoVideoAvailableException();
        }

        for (Advertisement tempAd : storage.list()) {
            if(tempAd.getAmountPerOneDisplaying() > 0 && tempAd.getDuration() <= timeSeconds){
                ads.add(tempAd);
            }
        }

        makeAllLists(ads);
        sortByAmountDuration(bestAdvertisement);
        long amount = getMaxAmount(bestAdvertisement);
        int duration = getMaxDuration(bestAdvertisement);

        StatisticManager statisticManager = StatisticManager.getInstance();
        VideoSelectedEventDataRow videoSelectedEventDataRow = new VideoSelectedEventDataRow(bestAdvertisement, amount, duration);
        statisticManager.register(videoSelectedEventDataRow);

        for (Advertisement bestAd: bestAdvertisement) {
            ConsoleHelper.writeMessage(bestAd.toString());
            bestAd.revalidate();
        }
    }

    private long getMaxAmount(List<Advertisement> ads){
        long maxAmount = 0;
        for (Advertisement ad: ads) {
            maxAmount += ad.getAmountPerOneDisplaying();
        }
        return maxAmount;
    }

    private int getMaxDuration(List<Advertisement> ads){
        int maxDuration = 0;
        for (Advertisement ad: ads) {
            maxDuration += ad.getDuration();
        }
        return maxDuration;
    }

    private void checkList(List<Advertisement> ads){
        int currentDuration = getMaxDuration(ads);
        long currentAmount = getMaxAmount(ads);

        if(bestAdvertisement == null && currentDuration <= timeSeconds) {
            bestAdvertisement = ads;
            maxAmount = currentAmount;
            maxDuration = currentDuration;
        } else {
            if(currentDuration <= timeSeconds && ( currentAmount > maxAmount ||
                    (currentAmount == maxAmount && currentDuration > maxDuration) ||
                    (currentAmount == maxAmount && currentDuration == maxDuration) &&
                            ads.size() < bestAdvertisement.size())) {
                bestAdvertisement = ads;
                maxAmount = currentAmount;
                maxDuration = currentDuration;
            }
        }
    }

    public void makeAllLists(List<Advertisement> ads){
        if(!ads.isEmpty()) {
            checkList(ads);
        }
        for (int i = 0; i < ads.size(); i++) {
            List<Advertisement> newAds = new ArrayList<>(ads);
            newAds.remove(i);
            makeAllLists(newAds);
        }
    }

    public void sortByAmountDuration(List<Advertisement> list){
        Collections.sort(list, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long c;
                c = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if(c == 0) {
                    c = o1.getAmountPerOneSecondDisplaying()  - o2.getAmountPerOneSecondDisplaying();
                }
                return (int)c;
            }
        });
    }
}
