package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.game.GameViewModel;
import com.cherit.minefield.ui.settings.SettingsViewModel;

import java.time.Duration;
import java.time.Instant;

public class Notch extends Fragment implements Runnable {
    private TextView timer_time;
    private TextView timer_text;
    private ImageButton retryBtn;
    private GameViewModel gameViewModel;
    private Duration duration;
    private Instant startTime;
    private OnGameRetry listener;
    private Handler handler;
    private SettingsViewModel settingsViewModel;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Notch.OnGameRetry) {
            listener = (Notch.OnGameRetry) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnGameEndedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.notch, container, false);
        gameViewModel =
                ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        settingsViewModel =
                ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);

        startTime = Instant.now();
        getComponents(root);
        setListener();
        duration = Duration.ofSeconds(settingsViewModel.getTimeLimit().getValue());

        if (duration.isZero()){
            timer_text.setText("Time");
        }

        handler = new Handler();
        handler.post(this);
        return root;
    }

    @Override
    public void onDestroyView() {
        handler.removeCallbacks(this);
        super.onDestroyView();
    }

    private void getComponents(View root) {
        timer_text = root.findViewById(R.id.timer_text);
        timer_time = root.findViewById(R.id.remaining_time);
        retryBtn = root.findViewById(R.id.retryBtn);
    }

    private void setListener() {
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.retryGame();
            }
        });
    }

    @Override
    public void run() {
        if (gameViewModel.isGameRunning()) {
            Duration runTime;
            Duration remainingTime;
            runTime = Duration.between(startTime, Instant.now());
            if (duration.getSeconds()>0) {
                remainingTime = duration.minus(runTime);
                if(remainingTime.getSeconds() == 0){
                    gameViewModel.setGameRunning(false);
                    Dialog dialog = new Dialog(false);
                    dialog.show(getFragmentManager(), "Dialog");
                }
            } else {
                remainingTime = runTime;
            }
            timer_time.setText(String.format("%02d:%02d", remainingTime.toMinutes(), remainingTime.getSeconds() % 60));
            if ((remainingTime.getSeconds() > 0 && duration.getSeconds()>0) || duration.getSeconds() == 0) {
                handler.postDelayed(this, 1000);
            }
        }
    }

    public interface OnGameRetry {
        public void retryGame();
    }
}
