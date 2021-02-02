package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap();
        map.put("name", null);
        map.put("country", null);
        map.put("city" , null);
        map.put("age", null);
        String string = getQuery(map);
        System.out.println(string);
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String, String> pair : params.entrySet()){
            if (pair.getValue() != null){
                result.append(pair.getKey() + " = '" + pair.getValue() + "'");
                result.append(" and ");
            }
        }
        int i = result.lastIndexOf(" and ");
        if (i > -1){
            result.delete(i, result.length());
        }
        return result.toString();
    }
}
