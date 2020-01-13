package com.cherit.minefield.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.settings.SettingsViewModel;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private Button startGameBtn;
    private Button settingsBtn;
    private Button exitBtn;
    private SettingsViewModel settingsViewModel;

//    private void connectHomeViewModel() {
//        homeViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(HomeViewModel.class);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getComponents(root);
        setListeners();
        settingsViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SettingsViewModel.class);
        root.setBackgroundResource(R.color.material_bg);
        return root;
    }
    private void getComponents(View root) {
        startGameBtn = root.findViewById(R.id.start_game);
        settingsBtn = root.findViewById(R.id.settings);
        exitBtn = root.findViewById(R.id.exit);
    }
    private void setListeners(){
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment).navigate(R.id.action_nav_home_to_nav_game);
            }});
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment).navigate(R.id.action_nav_home_to_nav_settings);
            }});
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                System.exit(0);
            }});
    }
}