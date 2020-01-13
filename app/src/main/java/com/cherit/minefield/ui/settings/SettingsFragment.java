package com.cherit.minefield.ui.settings;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cherit.minefield.R;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private EditText mapSize;
    private EditText minesNumber;
    private Button saveBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        settingsViewModel = ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);
        getComponents(root);
        minesNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        mapSize.setText(Integer.toString(settingsViewModel.getBoardSize().getValue()));
        minesNumber.setText(Integer.toString(settingsViewModel.getMinesNumber().getValue()));
        setListener();
        return root;
    }

    private void getComponents(View root) {
        mapSize = root.findViewById(R.id.mapSize);
        minesNumber = root.findViewById(R.id.minesNumber);
        saveBtn = root.findViewById(R.id.saveBtn);
    }

    private void setListener() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minesNumber.setTextColor(Color.BLACK);
                mapSize.setTextColor(Color.BLACK);
                if(mapSize.getText().toString().length()>0 && minesNumber.getText().toString().length() >0){
                    int size = Integer.parseInt(mapSize.getText().toString());
                    int mines = Integer.parseInt(minesNumber.getText().toString());
                    if(size > 0){
                        if(mines >= (size*size) && mines > 0){
                            minesNumber.setTextColor(Color.RED);
                        }else{
                            settingsViewModel.getBoardSize().postValue(size);
                            settingsViewModel.getBoardSize().setValue(size);
                            settingsViewModel.getMinesNumber().setValue(mines);
                            getActivity().getSupportFragmentManager().popBackStack();
                            Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment).navigate(R.id.action_nav_settings_to_nav_home);
                            getActivity().getSupportFragmentManager().popBackStack();
                        }
                    }
                }else{
                    minesNumber.setTextColor(Color.RED);
                    mapSize.setTextColor(Color.RED);
                }
            }
        });
    }

}
