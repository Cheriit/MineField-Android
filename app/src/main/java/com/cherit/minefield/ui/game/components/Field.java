package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.graphics.Color;
import android.widget.GridView;

import androidx.appcompat.widget.AppCompatImageView;

import com.cherit.minefield.R;

public class Field extends AppCompatImageView {
    private boolean isMine;
    private boolean isDiscovered;
    private boolean isMarked;
    private int x_pos;
    private int y_pos;
    private int closeMines;
    private Context root;

    public Field(int x, int y, Context context) {
        super(context);
        root = context;
        isDiscovered = false;
        isMine = false;
        closeMines = 0;
        this.x_pos = x;
        this.y_pos = y;
        setImageResource(R.drawable.empty_field);
        System.out.println(getParent());
//        setScaleType(Field.ScaleType.CENTER_CROP);
        setBackgroundColor(Color.BLUE);
    }

    public int getX_pos() { return x_pos; }
    public int getY_pos() { return y_pos; }
    public int getCloseMines() { return closeMines; }
    public boolean isMine() { return isMine; }
    public boolean isDiscovered() { return isDiscovered; }
    public void setMine() { isMine = true; }
    public void setCloseMines(int closeMines) {
        if(!isMine)
        {
            this.closeMines = closeMines;
        }
    }
    public void increaseCloseMines() { setCloseMines(closeMines+1); }
    public boolean isMarked() { return isMarked; }

    public void discover() {
        isDiscovered = true;
        setImageResource(R.drawable.discovered_field);
        if(isMine)
        {
            setBackgroundColor(Color.RED);
        }
        else
        {
            setBackgroundColor(Color.BLACK);
        }
    }
    public void setMarked(boolean marked) {
        if(!isDiscovered)
        {
            isMarked = marked;
            if(marked)
            {
            }
            else
            {
            }
        }
    }
}
