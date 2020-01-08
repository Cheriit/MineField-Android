package com.cherit.minefield.ui.game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.home.HomeViewModel;

import java.util.Objects;

public class GameFragment extends Fragment {

    private GameViewModel gameViewModel;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(HomeViewModel.class);
        gameViewModel =
                ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.fragment_game, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        gameViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(homeViewModel.getSomeText().getValue());
            }
        });
        return root;
    }
}