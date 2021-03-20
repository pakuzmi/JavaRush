package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public Move getMove() {
        return move;
    }

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        int result = 0;
        if (this.numberOfEmptyTiles > o.numberOfEmptyTiles){
            result = 1;
        } else if (this.numberOfEmptyTiles < o.numberOfEmptyTiles) {
            result = -1;
        } else if (this.score > o.score){
            result = 1;
        } else if (this.score < o.score){
            result = -1;
        }

        return result;
    }
}
