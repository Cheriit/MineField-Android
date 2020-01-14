package com.cherit.minefield.ui.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cherit.minefield.R;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<Integer> board_size;
    private MutableLiveData<Integer> mines_number;
    private MutableLiveData<Integer> time_limit;
    private MutableLiveData<Integer> active_theme;
    private static int[][] image_sets = {
            {R.drawable.mine_forest, R.drawable.empty_field_forest, R.drawable.discovered_field_forest},
            {R.drawable.mine_sea, R.drawable.empty_field_sea, R.drawable.discovered_field_sea},
            {R.drawable.mine_mines, R.drawable.empty_field_mines, R.drawable.discovered_field_mines}
    };

    public SettingsViewModel() {
        board_size = new MutableLiveData<>();
        mines_number = new MutableLiveData<>();
        time_limit = new MutableLiveData<>();
        active_theme = new MutableLiveData<>();
        board_size.setValue(8);
        mines_number.setValue(20);
        time_limit.setValue(0);
        active_theme.setValue(0);
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

    public int[] getImageSet(int i) {
        return image_sets[i];
    }
}
