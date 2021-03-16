package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];


    public Model() {
        resetGameTiles();
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
}
