package com.javarush.task.task25.task2512;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        List<Throwable> list = new LinkedList<>();
        Throwable throwable = (Throwable) e;
        while (throwable != null){
            list.add(throwable);
            throwable = throwable.getCause();
        }
        for (int i = list.size()-1; i >= 0; i-- ){
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        Exception myException = new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
        List<Throwable> list = new LinkedList<>();
        Throwable throwable = (Throwable) myException;
        while (throwable != null){
            list.add(throwable);
            throwable = throwable.getCause();
        }
        for (int i = list.size()-1; i >= 0; i-- ){
            System.out.println(list.get(i));
        }
    }
}
