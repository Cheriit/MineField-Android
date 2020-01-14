package com.cherit.minefield.ui.game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.settings.SettingsViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class GameFragment extends Fragment {

    private GameViewModel gameViewModel;
    private SettingsViewModel settingsViewModel;
//    private static int[][] image_set = {{R.drawable.}};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SettingsViewModel.class);
        gameViewModel =
                ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        gameViewModel.setGameRunning(true);
        gameViewModel.setImageSet(settingsViewModel.getImageSet(settingsViewModel.getActiveTheme().getValue()));
        View root = inflater.inflate(R.layout.fragment_game, container, false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}