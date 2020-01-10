package com.cherit.minefield.ui.game.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.game.GameViewModel;

public class FlagSwitch extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private GameViewModel gameViewModel;
    private CompoundButton switchFlagBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gameViewModel =
                ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.flag_switch, container, false);
        switchFlagBtn = root.findViewById(R.id.flag_switch_btn);
        gameViewModel.setMarking(switchFlagBtn.isChecked());
        switchFlagBtn.setOnCheckedChangeListener(this);
        return root;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        gameViewModel.setMarking(isChecked);
    }
}
