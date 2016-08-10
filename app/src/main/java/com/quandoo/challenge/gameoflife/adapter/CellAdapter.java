package com.quandoo.challenge.gameoflife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.quandoo.challenge.gameoflife.R;
import com.quandoo.challenge.gameoflife.util.ArrayUtil;
import com.quandoo.challenge.gameoflife.view.CellView;

import java.util.LinkedHashMap;

/**
 * Created by wsyssantos on 6/26/16.
 */
public class CellAdapter extends BaseAdapter {

    private int[][] cells;
    private Context context;
    private LinkedHashMap<Integer, CellView> viewList;

    public CellAdapter(Context context, int[][] cells) {
        this.context = context;
        this.cells = cells;
        this.viewList = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return 400;
    }

    public void updateCells(int[][] cells) {
        this.cells = cells;
        notifyDataSetChanged();
    }

    public void updateCell(int position) {
        int x = ArrayUtil.getXCoordinateFromPosition(position, 20);
        int y = ArrayUtil.getYCoordinateFromPosition(x, position, 20);

        if(this.cells[x][y] == 0)
            this.cells[x][y] = 1;
        else
            this.cells[x][y] = 0;

        CellView view = this.viewList.get(position);
        view.updateColor(x, y, cells);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CellView view = this.viewList.get(position);

        if (view == null) {
            view = new CellView(context);
            float dimen = context.getResources().getDimension(R.dimen.cell_view_size);
            view.setLayoutParams(new GridView.LayoutParams((int)dimen, (int)dimen));
            this.viewList.put(position, view);
        }

        int x = ArrayUtil.getXCoordinateFromPosition(position, 20);
        int y = ArrayUtil.getYCoordinateFromPosition(x, position, 20);


        view.updateColor(x, y, cells);
        return view;
    }

    public int[][] getGeneration() {
        return this.cells;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
