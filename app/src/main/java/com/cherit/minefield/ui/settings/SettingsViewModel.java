package com.cherit.minefield.ui.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<Integer> board_size;
    private MutableLiveData<Integer> mines_number;
    private MutableLiveData<Integer> time_limit;
    private MutableLiveData<Integer> active_theme;

    public SettingsViewModel() {
        board_size = new MutableLiveData<>();
        mines_number = new MutableLiveData<>();
        time_limit = new MutableLiveData<>();
        active_theme = new MutableLiveData<>();
        board_size.setValue(8);
        mines_number.setValue(20);
        time_limit.setValue(10);
        active_theme.setValue(0);
        System.out.println("Test");
    }

    public MutableLiveData<Integer> getBoardSize() {
        return board_size;
    }

    public MutableLiveData<Integer> getMinesNumber() {
        return mines_number;
    }

    public MutableLiveData<Integer> getTimeLimit() {
        return time_limit;
    }

    public MutableLiveData<Integer> getActiveTheme() {
        return active_theme;
    }
}
