package com.quandoo.challenge.gameoflife.util;

/**
 * Created by wsyssantos on 6/26/16.
 */
public class ArrayUtil {

    public static int[][] getNewMatrixWitZeroes(int sizeX, int sizeY) {
        int[][] matrix = new int[sizeX][sizeY];
        for(int x = 0; x < sizeX; x++) {
            for(int y = 0; y < sizeY; y++) {
                matrix[x][y] = 0;
            }
        }
        return matrix;
    }

    public static void renewMatrix(int[][] matrix) {
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[x].length; y++) {
                matrix[x][y] = 0;
            }
        }
    }

    public static void copyMatrixValues(int[][] from, int[][] to) {
        for(int x = 0; x < to.length; x++) {
            for(int y = 0; y < to[x].length; y++) {
                to[x][y] = from[x][y];
            }
        }
    }

    public static int getXCoordinateFromPosition(int position, int columnCount) {
        return (position) / columnCount;
    }

    public static int getYCoordinateFromPosition(int x, int position, int columnCount) {
        return position - (x * columnCount);
    }
}
