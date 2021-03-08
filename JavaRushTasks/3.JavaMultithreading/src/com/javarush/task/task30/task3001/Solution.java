package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/* 
Конвертер систем счислений
*/

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        String srcValString = number.getDigit();
        int srcNumberSystem = number.getNumberSystem().getNumberSystemIntValue();
        int destNumberSystem = expectedNumberSystem.getNumberSystemIntValue();
        Number result;
        try {
            BigInteger srcValInteger = new BigInteger(srcValString, srcNumberSystem);
            if (srcValInteger.compareTo(new BigInteger("0", destNumberSystem)) < 0) throw new NumberFormatException();
            String destValString = srcValInteger.toString(destNumberSystem);
            result = new Number(expectedNumberSystem, destValString);
        } catch (Exception e){
            throw new NumberFormatException();
        }

        return result;
    }
}
