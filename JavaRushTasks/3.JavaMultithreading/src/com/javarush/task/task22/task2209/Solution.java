package com.javarush.task.task22.task2209;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/* 
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            fileName = reader.readLine();
        }
        StringBuilder string = new StringBuilder();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))){
            while(fileReader.ready()){
                string.append(fileReader.readLine());
                string.append(" ");
            }
        }
        String[] words = string.toString().trim().split("\\s");
        StringBuilder result = getLine(words);
        System.out.println(result.toString().trim());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length < 1){ return new StringBuilder( );}
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(words));
        ArrayList<String> result = new ArrayList<String>();
        Iterator <String> it = list.iterator();
        while (it.hasNext()) {
            String word = it.next();
            result = analize(list, word, result, list.size());
            if (result.size() == list.size()){ break; }
        }
        for (String val : result){
            sb.append(val + " ");
        }
        return sb;
    }

    public static ArrayList<String> analize(ArrayList<String> listWords, String word, ArrayList<String> result, int len){
        result.add(word);
        ArrayList<String> listCopy = new ArrayList<String>(listWords);
        char letter = word.toLowerCase().charAt(word.length()-1);
        listCopy.remove(word);
        Iterator<String> it = listCopy.iterator();
        while (it.hasNext()&&(result.size()!=len)){
            String testWord = it.next();
            if (letter == testWord.toLowerCase().charAt(0)){ analize(listCopy, testWord, result, len); }
        }
        if (result.size() != len){ result.remove(result.size()-1); }
        return result;
    }
}
