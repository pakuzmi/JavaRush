package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String fileName = f.getName();
        return f.isDirectory() || fileName.toLowerCase().endsWith(".html") || fileName.toLowerCase().endsWith(".htm");
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
