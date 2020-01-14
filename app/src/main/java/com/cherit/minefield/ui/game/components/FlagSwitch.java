package com.cherit.minefield.ui.game.components;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.game.GameViewModel;

public class FlagSwitch extends Fragment implements CompoundButton.OnCheckedChangeListener, Runnable {
    private GameViewModel gameViewModel;
    private CompoundButton switchFlagBtn;
    private ImageView flag;
    private int counter = 0;
    private Handler animation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gameViewModel =
                ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.flag_switch, container, false);
        switchFlagBtn = root.findViewById(R.id.flag_switch_btn);
        flag = root.findViewById(R.id.flag_img);
        gameViewModel.setMarking(switchFlagBtn.isChecked());
        switchFlagBtn.setOnCheckedChangeListener(this);
        animation = new Handler();
        animation.post(this);
        return root;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        gameViewModel.setMarking(isChecked);
    }

    @Override
    public void onDestroyView() {
        animation.removeCallbacks(this);
        super.onDestroyView();
    }


    @Override
    public void run() {
        switch (counter++) {
            case 0:
                flag.setImageResource(R.drawable.flag_01);
            case 1:
                flag.setImageResource(R.drawable.flag_02);
                break;
            case 2:
                flag.setImageResource(R.drawable.flag_03);
                break;
            case 3:
                flag.setImageResource(R.drawable.flag_04);
                break;
            case 4:
                flag.setImageResource(R.drawable.flag_05);
                break;
        }
        counter = counter % 5;
        animation.postDelayed(this, 200);
    }
}
