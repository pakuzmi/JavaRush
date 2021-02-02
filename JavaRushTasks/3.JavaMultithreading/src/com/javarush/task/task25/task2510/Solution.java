package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
*/

import java.io.IOException;

public class Solution extends Thread {

    public Solution() {
        this.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //System.out.println("Поймано исключение:");
                //System.out.println(e.getClass());
                if (e instanceof Error){
                    System.out.println("Нельзя дальше работать");
                } else if (e instanceof Exception){
                    System.out.println("Надо обработать");
                } else if (e instanceof Throwable){
                    System.out.println("Поживем - увидим");
                }
            }
        });
    }

    public static void main(String[] args) {
        Thread thread = new Solution();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Thread started");
        System.out.println("Throw exception:");
        //throw new Exception();
    }
}
