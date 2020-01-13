package com.cherit.minefield.ui.settings.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.settings.SettingsViewModel;

public class TimeLimitSeekbar extends Fragment {
    private SettingsViewModel settingsViewModel;
    private SeekBar seekBar;
    private TextView seekBarStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.time_limit_seekbar, container, false);
        settingsViewModel =
                ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);
        getComponents(root);
        seekBar.setProgress(settingsViewModel.getTimeLimit().getValue());
        setProgressText(seekBar.getProgress());
        setListener();
        return root;
    }

    private void getComponents(View root) {
        seekBar = root.findViewById(R.id.timeLimit);
        seekBarStatus = root.findViewById(R.id.currentTimeLimit);
    }

    private void setListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    settingsViewModel.getTimeLimit().setValue(progress);
                    setProgressText(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setProgressText(int progress){
        if(progress <= 0){
            seekBarStatus.setText("There's no time limit");
        }else{
            seekBarStatus.setText("Time limit: "+Integer.toString(progress)+" s.");
        }

    }
}
