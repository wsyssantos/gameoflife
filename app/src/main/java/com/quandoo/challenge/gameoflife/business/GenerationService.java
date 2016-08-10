package com.quandoo.challenge.gameoflife.business;

import android.os.Handler;

import com.quandoo.challenge.gameoflife.delegate.GenerationDelegate;
import com.quandoo.challenge.gameoflife.model.Cell;
import com.quandoo.challenge.gameoflife.util.ArrayUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wsyssantos on 6/26/16.
 */
public class GenerationService {

    private GenerationDelegate delegate;
    private int[][] currentGeneration;
    private int[][] nextGeneration;
    private Timer timer;
    private TimerTask timerTask;
    private Handler timerHandler;
    private int generationCount;
    private boolean running = false;

    public GenerationService(GenerationDelegate delegate) {
        this.delegate = delegate;

        this.nextGeneration = ArrayUtil.getNewMatrixWitZeroes(20, 20);
    }

    private void configureTimer() {
        timerHandler = new Handler();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timerHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        calculateGeneration();
                    }
                });
            }
        };
    }

    public void calculateGeneration() {
        for(int x = 0; x < currentGeneration.length; x++) {
            for(int y = 0; y < currentGeneration[x].length; y++) {
                Cell cell = new Cell(x, y, currentGeneration);
                nextGeneration[x][y] = cell.nextGeneration();
            }
        }
        generationCount++;
        ArrayUtil.copyMatrixValues(nextGeneration, currentGeneration);
        ArrayUtil.renewMatrix(nextGeneration);

        delegate.updateGrid(currentGeneration);
        delegate.updateGenerationCount(generationCount);
    }

    public void play(int[][] currentGeneration) {
        this.currentGeneration = currentGeneration;
        generationCount = 1;
        configureTimer();
        timer.schedule(timerTask, 0, 1000);
        running = true;
    }

    public void stop() {
        if(timer != null){
            timer.cancel();
            timer.purge();
            running = false;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
