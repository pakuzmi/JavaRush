package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if ((telNumber == null)||(telNumber == "")) {
            return false;
        }
        if (!telNumber.matches("(^|[^\\n()+\\-\\d])((?:\\+\\d{2})?(?:\\(\\d{3}\\)|\\d{3})\\d{3}-?\\d{2}-?\\d{2})($|[^\\n()+\\-\\d])")){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+38(050)1234567"));
    }
}
