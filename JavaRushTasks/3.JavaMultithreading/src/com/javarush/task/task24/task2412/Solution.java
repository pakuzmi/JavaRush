package com.javarush.task.task24.task2412;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Знания - сила!
*/

public class Solution {
    public static void main(String[] args) {
        List<Stock> stocks = getStocks();
        sort(stocks);
        Date actualDate = new Date();
        printStocks(stocks, actualDate);
    }

    public static void printStocks(List<Stock> stocks, Date actualDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        double[] filelimits = {0d, actualDate.getTime()};
        String[] filepart = {"change {4}", "open {2} and last {3}"};

        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        //Format[] testFormats = {null, dateFormat, fileform};
        Format[] testFormats = {null,null,dateFormat, fileform};

        MessageFormat pattform = new MessageFormat("{0}   {1} | {5} {6}");

        pattform.setFormats(testFormats);

        for (Stock stock : stocks) {
            String name = ((String) stock.get("name"));
            String symbol = (String) stock.get("symbol");
            double open = !stock.containsKey("open") ? 0 : ((double) stock.get("open"));
            double last = !stock.containsKey("last") ? 0 : ((double) stock.get("last"));
            double change = !stock.containsKey("change") ? 0 : ((double) stock.get("change"));
            Date date = (Date) stock.get("date");
            Object[] testArgs = { name, symbol, open, last, change, date, date.getTime()};
            System.out.println(pattform.format(testArgs));
        }
    }

    public static void sort(List<Stock> list) {
        list.sort(new Comparator<Stock>() {
            public int compare(Stock stock1, Stock stock2) {
                int result;
                result = stock1.get("name").toString().compareTo(stock2.get("name").toString());
                if (result == 0){
                    Date date1 = (Date) stock1.get("date");
                    Date date2 = (Date) stock2.get("date");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date1);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    long long1 = cal.getTimeInMillis();
                    cal.setTime(date2);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    long long2 = cal.getTimeInMillis();
                    if (long1 < long2){
                        result = 1;
                    } else if (long1 > long2){
                        result = -1;
                    } else {
                        double profit1, profit2;
                        profit1 = stock1.containsKey("change")?(double) stock1.get("change"):(double) stock1.get("last") - (double) stock1.get("open");
                        profit2 = stock2.containsKey("change")?(double) stock2.get("change"):(double) stock2.get("last") - (double) stock2.get("open");
  /*                      if (stock1.containsKey("change")){
                            profit1 = (double) stock1.get("change");
                        } else {
                            profit1 = (double) stock1.get("last") - (double) stock1.get("open");
                        }*/
/*                        if (stock2.containsKey("change")){
                            profit2 = (double) stock2.get("change");
                        } else {
                            profit2 = (double) stock2.get("last") - (double) stock2.get("open");
                        }*/
                      //  result = profit1==profit2?0:(profit1<profit2?1:-1);
                        result = Double.compare(profit2, profit1);
        /*                if (profit1 < profit2){
                            result = 1;
                        } else if (profit1 > profit2){
                            result = -1;
                        } else {
                            result = 0;
                        }*/
                    };
                }

                return result;
            }
        });
    }

    public static class Stock extends HashMap<String, Object> {
        public Stock(String name, String symbol, double open, double last) {
            put("name", name);
            put("symbol", symbol);
            put("open", open);
            put("last", last);
            put("date", getRandomDate(2020));
/*            GregorianCalendar cal = new GregorianCalendar();
            cal.set(1988,12,12);
            put("date", cal.getTime());*/
        }

        public Stock(String name, String symbol, double change, Date date) {
            put("name", name);
            put("symbol", symbol);
            put("date", date);
            put("change", change);
        }
    }

    public static List<Stock> getStocks() {
        List<Stock> stocks = new ArrayList<>();

        stocks.add(new Stock("Fake Apple Inc.", "AAPL", 125.64, 123.43));
        stocks.add(new Stock("Fake Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
        stocks.add(new Stock("Fake Google Inc.", "GOOG", 516.2, 512.6));
        stocks.add(new Stock("Fake Intel Corporation", "INTC", 21.36, 21.53));
        stocks.add(new Stock("Fake Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
        stocks.add(new Stock("Fake Microsoft Corporation", "MSFT", 29.56, 29.72));

        stocks.add(new Stock("Fake Nokia Corporation (ADR)", "NOK", .1, getRandomDate()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, getRandomDate()));
/*

        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .15, new GregorianCalendar(1988,12,12).getTime()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", -.12, new GregorianCalendar(1988,12,12).getTime()));
        stocks.add(new Stock("Fake Oracle Corporation", "ORCL", .17, new GregorianCalendar(1988,12,12).getTime()));
*/

        stocks.add(new Stock("Fake Starbucks Corporation", "SBUX", .03, getRandomDate()));
        stocks.add(new Stock("Fake Yahoo! Inc.", "YHOO", .32, getRandomDate()));
        stocks.add(new Stock("Fake Applied Materials, Inc.", "AMAT", .26, getRandomDate()));
        stocks.add(new Stock("Fake Comcast Corporation", "CMCSA", .5, getRandomDate()));
        stocks.add(new Stock("Fake Sirius Satellite", "SIRI", -.03, getRandomDate()));

        return stocks;
    }

    public static Date getRandomDate() {
        return getRandomDate(1970);
    }

    public static Date getRandomDate(int startYear) {
        int year = startYear + (int) (Math.random() * 30);
        int month = (int) (Math.random() * 12);
        int day = (int) (Math.random() * 28);
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTime();
    }
}

