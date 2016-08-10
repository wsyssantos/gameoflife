package com.quandoo.challenge.gameoflife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.quandoo.challenge.gameoflife.adapter.CellAdapter;
import com.quandoo.challenge.gameoflife.business.GenerationService;
import com.quandoo.challenge.gameoflife.delegate.GenerationDelegate;
import com.quandoo.challenge.gameoflife.util.ArrayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameOfLifeActivity extends AppCompatActivity implements GenerationDelegate {

    @BindView(R.id.cellGrid) GridView cellGrid;
    @BindView(R.id.txtGenerationCounter) TextView txtGenerationCounter;
    @BindView(R.id.imgButtonPlay) ImageButton imgButtonPlay;
    @BindView(R.id.imgButtonStop) ImageButton imgButtonStop;
    @BindView(R.id.imgButtonClear) ImageButton imgButtonClear;

    private CellAdapter gridCellAdapter;
    private GenerationService generationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_of_life);
        ButterKnife.bind(this);

        generationService = new GenerationService(this);

        int[][] cells = ArrayUtil.getNewMatrixWitZeroes(20, 20);
        gridCellAdapter = new CellAdapter(this, cells);
        cellGrid.setAdapter(gridCellAdapter);

        cellGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(!generationService.isRunning()) {
                    gridCellAdapter.updateCell(position);
                }
            }
        });
    }


    @OnClick(R.id.imgButtonPlay)
    public void playGeneration() {
        generationService.play(this.gridCellAdapter.getGeneration());
        imgButtonPlay.setVisibility(View.GONE);
        imgButtonClear.setVisibility(View.GONE);
        imgButtonStop.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.imgButtonStop)
    public void stopGeneration() {
        generationService.stop();
        this.txtGenerationCounter.setText("1");
        imgButtonPlay.setVisibility(View.VISIBLE);
        imgButtonClear.setVisibility(View.VISIBLE);
        imgButtonStop.setVisibility(View.GONE);
    }

    @OnClick(R.id.imgButtonClear)
    public void clear() {
        int[][] matrix = gridCellAdapter.getGeneration();
        ArrayUtil.renewMatrix(matrix);
        this.gridCellAdapter.updateCells(matrix);
    }

    @Override
    public void updateGenerationCount(int value) {
        this.txtGenerationCounter.setText(String.valueOf(value));
    }

    @Override
    public void updateGrid(int[][] matrix) {
        this.gridCellAdapter.updateCells(matrix);
    }
}

