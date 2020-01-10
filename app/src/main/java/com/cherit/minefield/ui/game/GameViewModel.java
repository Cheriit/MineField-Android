package com.cherit.minefield.ui.game;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private boolean isGameRunning;
    private boolean isMarking;
    private int size = 10;
    private int minesNumber = 20;

    public GameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is game fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

    public boolean isMarking() {
        return isMarking;
    }

    public void setMarking(boolean marking) {
        isMarking = marking;
    }

    public int getMinesNumber() {
        return minesNumber;
    }

    public void setMinesNumber(int minesNumber) {
        this.minesNumber = minesNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}