package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    public static Hippodrome game;
    private List<Horse> horses;

    public List<Horse> getHorses(){
        return this.horses;
    }

    public Hippodrome(List<Horse> horses){
        this.horses = horses;
    }

    public void run() throws InterruptedException {
        for (int i=0; i < 20; i++){
            move();
            print();
            Thread.sleep(200);
        }
    }
    public void move(){
        for (Horse horse : horses){
            horse.move();
        }
    }
    public void print(){
        for (Horse horse : horses){
            horse.print();
        }
        for (int i = 0; i < 10; i++){
            System.out.println();
        }
    }
    public Horse getWinner(){
        Horse result = null;
        double d = Double.MIN_VALUE;
        for (Horse horse : horses){
            if (horse.distance > d){
                result = horse;
            }
        }
        return result;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().name + "!");
    }
    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Вороная",3,0));
        horses.add(new Horse("Ласточка",3,0));
        horses.add(new Horse("Жеребец",3,0));
        Hippodrome.game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
