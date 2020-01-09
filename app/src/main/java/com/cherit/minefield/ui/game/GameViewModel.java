package com.cherit.minefield.ui.game;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private boolean isGameRunning;
    private boolean isMarking;
    private int rows;
    private int cols;

    public GameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is game fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public boolean isGameRunning() { return isGameRunning; }
    public void setGameRunning(boolean gameRunning) { isGameRunning = gameRunning; }

    public boolean isMarking() { return isMarking; }
    public void setMarking(boolean marking) { isMarking = marking; }

    public int getCols() { return cols; }
    public void setCols(int cols) { this.cols = cols; }

    public int getRows() { return rows; }
    public void setRows(int rows) { this.rows = rows; }
}