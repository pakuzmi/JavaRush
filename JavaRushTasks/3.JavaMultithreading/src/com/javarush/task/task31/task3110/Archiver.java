package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) {
        System.out.println("Введите полный путь архива в который будем архивировать.");
        String archivePath = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            archivePath = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        ZipFileManager fileManager = new ZipFileManager(Paths.get(archivePath));
        System.out.println("Введите полный путь к файлу, который будем архивировать.");
        String filePath = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            filePath = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            fileManager.createZip(Paths.get(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
