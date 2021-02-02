 package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
Машину на СТО не повезем!
*/

public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            String[] valuesFromDB = loadWheelNamesFromDB();
            if (valuesFromDB == null){
                throw new UnsupportedOperationException();
            }
            boolean hasBACK_LEFT = false;
            boolean hasBACK_RIGHT = false;
            boolean hasFRONT_LEFT = false;
            boolean hasFRONT_RIGHT = false;
            wheels = new ArrayList<>();
            for (String value : valuesFromDB){
                if (value.equals(Wheel.BACK_LEFT.name()) && !hasBACK_LEFT){
                    wheels.add(Wheel.BACK_LEFT);
                    hasBACK_LEFT = true;
                } else if (value.equals(Wheel.BACK_RIGHT.name()) && !hasBACK_RIGHT){
                    wheels.add(Wheel.BACK_RIGHT);
                    hasBACK_RIGHT = true;
                } else if (value.equals(Wheel.FRONT_LEFT.name()) && !hasFRONT_LEFT){
                    wheels.add(Wheel.FRONT_LEFT);
                    hasFRONT_LEFT = true;
                } else if (value.equals(Wheel.FRONT_RIGHT.name()) && !hasFRONT_RIGHT){
                    wheels.add(Wheel.FRONT_RIGHT);
                    hasFRONT_RIGHT = true;
                } else {
                    throw new UnsupportedOperationException();
                }
            }
            if (!hasBACK_LEFT || !hasBACK_RIGHT || !hasFRONT_LEFT || !hasFRONT_RIGHT){
                throw new UnsupportedOperationException();
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
            //return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car =  new Car();

    }
}
