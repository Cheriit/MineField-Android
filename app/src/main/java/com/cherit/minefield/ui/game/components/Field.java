package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import androidx.appcompat.widget.AppCompatImageView;

import com.cherit.minefield.R;

public class Field extends AppCompatImageView {
    private boolean isMine;
    private boolean isDiscovered;
    private boolean isMarked;
    private int x_pos;
    private int y_pos;
    private int closeMines;

    public Field(int x, int y, Context context) {
        super(context);
        isDiscovered = false;
        isMine = false;
        closeMines = 0;
        this.x_pos = x;
        this.y_pos = y;
        setImageResource(R.drawable.empty_field);
        setScaleType(ScaleType.FIT_XY);
        int padding = (int) getResources().getDimension(R.dimen.field_padding);
        setPadding(padding, padding, padding, padding);
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
        if(isMine)
        {
            Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.discovered_field);
            Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.mine);
            Bitmap result = Bitmap.createBitmap(bigImage.getWidth(), bigImage.getHeight(), bigImage.getConfig());
            Canvas canvas = new Canvas(result);
            canvas.drawBitmap(bigImage, 0f, 0f, null);
            canvas.drawBitmap(smallImage, 10, 10, null);
            setImageBitmap(result);
        }
        else
        {
            setImageResource(R.drawable.discovered_field);
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
