package com.quandoo.challenge.gameoflife.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

/**
 * Created by wsyssantos on 6/27/16.
 */
public class CellView extends View {

    public CellView(Context context) {
        super(context);
    }

    public void updateColor(int x, int y, int[][] cells) {
        GradientDrawable gd = new GradientDrawable();
        gd.setStroke(1, 0xFF000000);

        int cell = cells[x][y];
        if(cell == 0) {
            gd.setColor(0xFFFFFFFF);
        } else {
            gd.setColor(Color.BLUE);
        }

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackgroundDrawable(gd);
        } else {
            this.setBackground(gd);
        }
    }
}
