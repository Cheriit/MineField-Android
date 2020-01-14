package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import androidx.appcompat.widget.AppCompatImageView;

import com.cherit.minefield.R;

public class Field extends AppCompatImageView {
    private boolean isMine;
    private boolean isDiscovered;
    private boolean isMarked;
    private int x_pos;
    private int y_pos;
    private int closeMines;
    private int descriptionImage;
    private int emptyImage;
    private int discoverImage;

    public Field(int x, int y, int discoverImage, int emptyImage, Context context) {
        super(context);
        isDiscovered = false;
        isMine = false;
        isMarked = false;
        closeMines = 0;
        this.x_pos = x;
        this.y_pos = y;
        this.discoverImage = discoverImage;
        this.emptyImage = emptyImage;
        setImageResource(emptyImage);
        setScaleType(ScaleType.FIT_XY);
        int padding = (int) getResources().getDimension(R.dimen.field_padding);
        setPadding(padding, padding, padding, padding);
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public int getCloseMines() {
        return closeMines;
    }

    public void setCloseMines(int closeMines) {
        if (!isMine) {
            this.closeMines = closeMines;
        }
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setMine() {
        isMine = true;
    }

    public void increaseCloseMines() {
        setCloseMines(closeMines + 1);
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        if (!isDiscovered) {
            isMarked = marked;
            if (marked) {
                setBackgroundImage(emptyImage, R.drawable.flag);
            } else {
                setImageResource(emptyImage);
            }
        }
    }

    public void discover() {
        isDiscovered = true;
        if (isMine || closeMines>0) {
            setBackgroundImage(discoverImage, descriptionImage);
        } else {
            setImageResource(discoverImage);
        }
    }
    private void setBackgroundImage(int res1, int res2)
    {
        Bitmap bigImage = BitmapFactory.decodeResource(getResources(), res1);
        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), res2);
        Bitmap result = Bitmap.createBitmap(bigImage.getWidth(), bigImage.getHeight(), bigImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(bigImage, 0f, 0f, null);
        canvas.drawBitmap(smallImage, 0f, 0f, null);
        setImageBitmap(result);
    }

    public void setDescriptionImage(int descriptionImage) {
        this.descriptionImage = descriptionImage;
    }
}
