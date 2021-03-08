package com.javarush.task.task30.task3010;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        String str = "";
        int i =2;
        try {
            str = args[0];
            //str = "12AS08Z";
        } catch (Exception e){

        }
        while(i <= 36){
            try{
                BigInteger bigInteger = new BigInteger(str, i);
                list.add(i);
            } catch (Exception e){

            } finally {
                i++;
            }
        }
        if (list.size()>0){
            Collections.sort(list);
            System.out.println(list.get(0));
        } else {
            System.out.println("incorrect");
        }

    }
}