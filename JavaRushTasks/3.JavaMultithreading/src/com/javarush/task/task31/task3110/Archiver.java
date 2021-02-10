package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.Command;
import com.javarush.task.task31.task3110.command.ExitCommand;
import com.javarush.task.task31.task3110.exception.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args) {
        Operation operation = null;
        while (operation != Operation.EXIT){
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

        }
/*        System.out.println("Введите полный путь архива в который будем архивировать.");
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
        Command exitCommand = new ExitCommand();
        try {
            exitCommand.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static Operation askOperation() throws IOException{
        ConsoleHelper.writeMessage("Выберите операцию:");
        ConsoleHelper.writeMessage(Operation.CREATE.ordinal() + " - упаковать файлы в архив");
        ConsoleHelper.writeMessage(Operation.ADD.ordinal() + " - добавить файл в архив");
        ConsoleHelper.writeMessage(Operation.REMOVE.ordinal() + " - удалить файл из архива");
        ConsoleHelper.writeMessage(Operation.EXTRACT.ordinal() + " - распаковать архив");
        ConsoleHelper.writeMessage(Operation.CONTENT.ordinal() + " - просмотреть содержимое архива");
        ConsoleHelper.writeMessage(Operation.EXIT.ordinal() + " - выход");
        int numOperation = ConsoleHelper.readInt();
        for (Operation operation: Operation.values()){
            if (operation.ordinal() == numOperation) return operation;
        }
        return null;
    }

}
