package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    int score; //текущий счет
    int maxTile; //максимальный вес плитки

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
    }

    public void down(){
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void right(){
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void up(){
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    private void rotate(){
       /*Tile[][] testTiles = {{new Tile(1), new Tile(2), new Tile(3), new Tile(4)},
                            {new Tile(0), new Tile(0), new Tile(0), new Tile(6)},
                            {new Tile(3), new Tile(1), new Tile(2), new Tile(7)},
                            {new Tile(5), new Tile(6), new Tile(3), new Tile(2)}};*/
        Tile[][] rotatedTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int y = 0; y < FIELD_WIDTH; y++){
            for (int x = 0; x < FIELD_WIDTH; x++){
                rotatedTiles[y][x] = gameTiles[FIELD_WIDTH - 1 - x][y];
            }
        }
        gameTiles = rotatedTiles;
     }
}
