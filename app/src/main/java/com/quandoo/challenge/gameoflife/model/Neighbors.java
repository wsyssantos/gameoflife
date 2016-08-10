package com.quandoo.challenge.gameoflife.model;

/**
 * Created by wsyssantos on 6/26/16.
 */
public class Neighbors {
    private Cell cell;
    private int[] neighbors;

    public Neighbors(Cell cell) {
        this.cell = cell;
        setNeighbors();
    }

    public void setNeighbors() {
        neighbors =  new int[] {
            getTopNeighbor(), getTopRightNeighbor(), getRightNeighbor(), getBottomRightNeighbor(), getBottomNeighbor(), getBottomLeftNeighbor(), getLeftNeighbor(), getTopLeftNeighbor()
        };
    }

    private int getTopNeighbor() {
        int neighborXCalculated = cell.getX() - 1;
        int neighborYCalculated = cell.getY();

        return checkNeighbor(neighborXCalculated, neighborYCalculated);
    }

    private int getRightNeighbor() {
        int neighborXCalculated = cell.getX();
        int neighborYCalculated = cell.getY() + 1;

        return checkNeighbor(neighborXCalculated, neighborYCalculated);
    }

    private int getBottomNeighbor() {
        int neighborXCalculated = cell.getX() + 1;
        int neighborYCalculated = cell.getY();

        return checkNeighbor(neighborXCalculated, neighborYCalculated);
    }

    private int getLeftNeighbor() {
        int neighborXCalculated = cell.getX();
        int neighborYCalculated = cell.getY() - 1;

        return checkNeighbor(neighborXCalculated, neighborYCalculated);
    }

    private int getTopRightNeighbor() {
        int neighborXCalculated = cell.getX() - 1;
        int neighborYCalculated = cell.getY() + 1;

        return checkNeighbor(neighborXCalculated, neighborYCalculated);
    }

    private int getBottomRightNeighbor() {
        int neighborXCalculated = cell.getX() + 1;
        int neighborYCalculated = cell.getY() + 1;

        return checkNeighbor(neighborXCalculated, neighborYCalculated);
    }

    private int getBottomLeftNeighbor() {
        int neighborXCalculated = cell.getX() + 1;
        int neighborYCalculated = cell.getY() - 1;

        return checkNeighbor(neighborXCalculated, neighborYCalculated);
    }

    private int getTopLeftNeighbor() {
        int neighborXCalculated = cell.getX() - 1;
        int neighborYCalculated = cell.getY() - 1;

        return checkNeighbor(neighborXCalculated, neighborYCalculated);
    }

    private int checkNeighbor(int x, int y) {
        int neighborX = (x >= 0 && x < 20) ? x : -1;
        int neighborY = (y >= 0 && y < 20) ? y : -1;

        if(neighborX != -1 && neighborY != -1) {
            return cell.getSource()[neighborX][neighborY];
        } else {
            return 0;
        }
    }

    public int getAliveNeighborsCount() {
        int count = 0;
        for(int i = 0; i < this.neighbors.length; i++) {
            count += this.neighbors[i];
        }
        return count;
    }

}
