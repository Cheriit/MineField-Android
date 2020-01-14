package com.cherit.minefield.ui.settings.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.settings.SettingsViewModel;

public class GraphicSetRadio extends Fragment {
    private SettingsViewModel settingsViewModel;
    private RadioGroup radioGroup;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.graphic_set_radio, container, false);
        this.root = root;
        settingsViewModel =
                ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);
        getComponents(root);
        setListener();
        return root;
    }

    private void getComponents(View root) {
        radioGroup = root.findViewById(R.id.radioGroup);
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.findViewById(R.id.mines).setBackgroundResource(settingsViewModel.getImageSet(2)[1]);
                group.findViewById(R.id.forest).setBackgroundResource(settingsViewModel.getImageSet(0)[1]);
                group.findViewById(R.id.sea).setBackgroundResource(settingsViewModel.getImageSet(1)[1]);
                if(checkedId == R.id.mines){
                    settingsViewModel.getActiveTheme().setValue(2);
                    group.findViewById(R.id.mines).setBackgroundResource(settingsViewModel.getImageSet(2)[2]);
                }else if(checkedId == R.id.forest){
                    settingsViewModel.getActiveTheme().setValue(0);
                    group.findViewById(R.id.forest).setBackgroundResource(settingsViewModel.getImageSet(0)[2]);

                }else{
                    settingsViewModel.getActiveTheme().setValue(1);
                    group.findViewById(R.id.sea).setBackgroundResource(settingsViewModel.getImageSet(1)[2]);
                }
            }
        });
    }
}
