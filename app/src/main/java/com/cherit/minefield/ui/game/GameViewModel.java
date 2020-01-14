package com.cherit.minefield.ui.game;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {

    private boolean isGameRunning;
    private boolean isMarking;
    private int[] imageSet;

    public GameViewModel() {
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

    public int[] getImageSet() {
        return imageSet;
    }

    public void setImageSet(int[] imageSet) {
        this.imageSet = imageSet;
    }
}