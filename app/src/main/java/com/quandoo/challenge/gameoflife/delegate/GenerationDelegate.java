package com.quandoo.challenge.gameoflife.delegate;

/**
 * Created by wsyssantos on 6/26/16.
 */
public interface GenerationDelegate {
    void updateGenerationCount(int value);
    void updateGrid(int[][] matrix);
}
