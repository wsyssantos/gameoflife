package com.quandoo.challenge.gameoflife;


import com.quandoo.challenge.gameoflife.business.GenerationService;
import com.quandoo.challenge.gameoflife.delegate.GenerationDelegate;
import com.quandoo.challenge.gameoflife.util.ArrayUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by wsyssantos on 6/28/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameOfLifeTest {
    GenerationService generationService;

    @Test
    public void testGameOfLifeWithOneGeneration() {
        final int[][] result = ArrayUtil.getNewMatrixWitZeroes(20, 20);
        generationService = new GenerationService(new GenerationDelegate() {
            @Override
            public void updateGenerationCount(int value) { }

            @Override
            public void updateGrid(int[][] matrix) {
                ArrayUtil.copyMatrixValues(matrix, result);
            }
        });

        int[][] matrix = ArrayUtil.getNewMatrixWitZeroes(20, 20);
        matrix[0][0] = 1;
        generationService.play(matrix);
        generationService.calculateGeneration();

        assertThat(result[0][0], is(0));
    }

    @Test
    public void testGameOfLifeWithInfiniteForm() {
        final int[][] result = ArrayUtil.getNewMatrixWitZeroes(20, 20);
        generationService = new GenerationService(new GenerationDelegate() {
            @Override
            public void updateGenerationCount(int value) {

            }

            @Override
            public void updateGrid(int[][] matrix) {
                ArrayUtil.copyMatrixValues(matrix, result);
            }
        });

        int[][] matrix = ArrayUtil.getNewMatrixWitZeroes(20, 20);
        matrix[0][1] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;

        generationService.play(matrix);
        generationService.calculateGeneration();

        assertThat(result[0][0], is(1));
    }
}
