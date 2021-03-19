package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH]; //Состояние игрового поля
    int score; //текущий счет
    int maxTile; //максимальный вес плитки
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public Model() {
        resetGameTiles();
        score = 0;
        maxTile = 0;
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> list = new ArrayList<>();
        for (int y = 0; y < FIELD_WIDTH; y++){
            for (int x = 0; x < FIELD_WIDTH; x++){
                if (gameTiles[y][x].value == 0){
                    list.add(gameTiles[y][x]);
                }
            }
        }
        return list;
    }

    private void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() == 0) return;
        Tile randomTile = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
        randomTile.value = Math.random() < 0.9 ? 2 : 4;
    }

    public void resetGameTiles(){
        for (int y = 0; y < FIELD_WIDTH; y++){
            for (int x = 0; x < FIELD_WIDTH; x++){
                gameTiles[y][x] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles){
        boolean result = false;
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles.length - 1; j++){
                if (tiles[j].value == 0 && tiles[j+1].value != 0){
                    tiles[j].value = tiles[j+1].value;
                    tiles[j+1].value = 0;
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean mergeTiles(Tile[] tiles){
        boolean result = false;
        for (int i = 0; i < tiles.length - 1; i++){
            if ((tiles[i].value !=0) && (tiles[i].value == tiles[i+1].value)){
                int sum = tiles[i].value * 2;
                tiles[i].value = sum;
                tiles[i+1].value = 0;
                score += sum;
                maxTile = Math.max(sum, maxTile);
                result = true;
            }
        }
        result |= compressTiles(tiles);
        return result;
    }

    public void left(){
        boolean isChanged = false;
        if (isSaveNeeded){
            saveState(gameTiles);
            isSaveNeeded = false;
        }
        for (int y = 0; y < gameTiles.length; y++){
            if (compressTiles(gameTiles[y])){
                isChanged = true;
            }
            if (mergeTiles(gameTiles[y])){
                isChanged = true;
            }
        }
        if (isChanged){
            addTile();
        }
        isSaveNeeded = true;
    }

    public void down(){
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void right(){
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void up(){
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    private void rotate(){
        Tile[][] rotatedTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int y = 0; y < FIELD_WIDTH; y++){
            for (int x = 0; x < FIELD_WIDTH; x++){
                rotatedTiles[y][x] = gameTiles[FIELD_WIDTH - 1 - x][y];
            }
        }
        gameTiles = rotatedTiles;
     }

     public boolean canMove(){
        boolean result = false;
        if (getEmptyTiles().size() > 0) {
            result = true;
        }
        for (int y = 0; y < FIELD_WIDTH - 1; y++){
            for (int x = 0; x < FIELD_WIDTH - 1; x++){
                if (gameTiles[y][x].value == gameTiles[y][x+1].value || gameTiles[y][x].value == gameTiles[y+1][x].value){
                    result = true;
                }
            }
        }
                
        return result;
     }

    private void saveState(Tile[][] tiles){
        Tile[][] tilesToSave = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int y = 0; y < FIELD_WIDTH; y++){
            for (int x = 0; x < FIELD_WIDTH; x++){
                tilesToSave[y][x] = new Tile(tiles[y][x].value);
            }
        }
        previousStates.push(tilesToSave);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0:{
                left();
                break;
            }
            case 1:{
                right();
                break;
            }
            case 2:{
                up();
                break;
            }
            case 3:{
                down();
                break;
            }
        }
    }

}
