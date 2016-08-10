package com.quandoo.challenge.gameoflife.model;

/**
 * Created by wsyssantos on 6/26/16.
 */
public class Cell {

    private int x;
    private int y;
    private int[][] source;
    private Neighbors neighbors;

    public Cell(int x, int y, int[][] source) {
        this.x = x;
        this.y = y;
        this.source = source;

        neighbors = new Neighbors(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getSource() {
        return source;
    }

    public int nextGeneration() {
        int aliveNeighbors = neighbors.getAliveNeighborsCount();

        if(this.source[x][y] == 0 && aliveNeighbors == 3) {
            return 1;
        } else if(this.source[x][y] == 1 && aliveNeighbors == 2 || aliveNeighbors == 3) {
            return 1;
        }

        return 0;
    }
}
