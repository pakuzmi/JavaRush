package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        String fileName = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            fileName = reader.readLine();
        }
        try (BufferedReader br = new BufferedReader( new FileReader(fileName))){
            while (br.ready()) {
                sb.append(br.readLine());
                sb.append(" ");
            }
        }
        String[] strings = sb.toString().split("\\s");
        List<String> list = new ArrayList<String>(Arrays.asList(strings));
        while (list.size()>0){
            String val1 = list.get(0);
            list.remove(0);
            Iterator<String> it = list.iterator();
            while (it.hasNext()){
                String val = it.next();
                StringBuilder sbTest = new StringBuilder(val);
                String val2 = sbTest.reverse().toString();
                if (val1.equals(val2)){
                    result.add(new Pair(val1,val));
                    it.remove();
                    break;
                }
            }
        }
        System.out.println(result);
    }


    public static class Pair {
        String first;
        String second;

        public Pair(){

        }

        public Pair(String first, String second){
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
