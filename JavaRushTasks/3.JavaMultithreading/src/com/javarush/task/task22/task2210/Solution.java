package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/

public class Solution {
    public static void main(String[] args) {
        String[] result = getTokens("level22.lesson13.task01", ".");
    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        ArrayList<String> list = new ArrayList<>();
        while (tokenizer.hasMoreElements()){
            list.add(tokenizer.nextToken());
        }
        String[] result = list.toArray(new String[list.size()]);
        return result;
    }
}
