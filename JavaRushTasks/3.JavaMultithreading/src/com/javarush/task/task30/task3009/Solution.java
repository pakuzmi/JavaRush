package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number){
        Set<Integer> result = new HashSet<>();
        int i = 2;
        while (i <= 36){
            try {
                BigInteger bigInteger = new BigInteger(number, 10);
                String val = bigInteger.toString(i);
                StringBuilder stringBuilder = new StringBuilder(val);
                StringBuilder reversedStringBuilder = (new StringBuilder(val)).reverse();
                if (stringBuilder.toString().equals(reversedStringBuilder.toString())) {
                    result.add(i);
                }
            } catch (NumberFormatException e){

            } finally {
                i++;
            }
        }

        return result;
    }


}