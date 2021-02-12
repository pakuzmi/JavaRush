package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipAddCommand extends ZipCommand{
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Добавление файла в архив.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите путь к файлу который необходимо добавить в архив:");
            Path addFilePath = Paths.get(ConsoleHelper.readString());
            zipFileManager.addFile(addFilePath);

            ConsoleHelper.writeMessage("Требуемый файл был добавлен в архив.");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Неверный путь для добавлени файла в архив.");
        }
    }
}
