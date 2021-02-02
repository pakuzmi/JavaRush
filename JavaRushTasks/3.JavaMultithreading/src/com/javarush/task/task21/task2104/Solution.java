package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof Solution)) return false;

        Solution solution = (Solution) o;

        if (first != null ? !first.equals(solution.first) : solution.first != null) return false;
        return last != null ? last.equals(solution.last) : solution.last == null;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
    }

/*@Override
    public boolean equals(Object n) {
        if (n == this){
            return true;
        }
        if (n == null){
            return false;
        }
        if (!(n instanceof Solution)){
            return false;
        }

        Solution s = (Solution) n;

        if (s.first == null){
            if (first == null){
                return true;
            } else {
                return false;
            }
        }
        if (s.last == null){
            if (last == null){
                return true;
            } else {
                return false;
            }
        }

        return  ( s.first.equals(first) && (s.last.equals(last)));


    }
    @Override
    public int hashCode() {
        if (first == null || last == null){
            return 0;
        }
        return 31 * first.hashCode() + last.hashCode();
    }*/

    public static void main(String[] args) {
/*        Solution solution1 = new Solution("Donald", "Duck");
        Solution solution2 = new Solution("Donald", "Duck");
        System.out.println(solution2.equals(solution1));
        System.out.println(solution1.hashCode());
        System.out.println(solution2.hashCode());*/
        /*Object obj = null;
        System.out.println(obj.hashCode());*/
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        s.contains(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
