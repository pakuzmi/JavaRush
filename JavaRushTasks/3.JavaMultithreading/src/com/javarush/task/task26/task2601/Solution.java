package com.javarush.task.task26.task2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
        //Integer[] arrays = {13, 8, 15, 5, 17};
        //Integer[] result = sort(arrays);
    }

    public static Integer[] sort(Integer[] array) {
        Double median;
        int length = array.length;
        Arrays.sort(array);
        if (length%2!=0){
            int index = length/2;
            median =  array[index].doubleValue();
        } else {
            int index = length/2;
            median =  array[length/2 - 1] * 0.5 + array[length/2] * 0.5;
        }

        Comparator<Integer> comparator = new Comparator<Integer>() {
             @Override
             public int compare(Integer o1, Integer o2) {
                 Double val1 = Math.abs (o1.doubleValue() - median);
                 Double val2 = Math.abs (o2.doubleValue() - median);
                 //val1 = Math.abs(val1);
                 //val2 = Math.abs(val2);
                 int result = 0;
                 if (val1 > val2) {
                     result = 1;
                 } else if (val1 < val2){
                     result = -1;
                 } else {
                     result = o1 - o2;
                 }
                 return result;
             }
         };
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
        Collections.sort(list, comparator);
        Integer[] result = list.toArray(new Integer[length]);
        return result;
    }
}
