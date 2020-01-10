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

import java.time.Duration;
import java.time.Instant;

public class Notch extends Fragment implements Runnable {
    private TextView timer_time;
    private ImageButton retryBtn;
    private GameViewModel gameViewModel;
    private Duration duration;
    private Instant startTime;
    private OnGameRetry listener;
    private Handler handler;

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
        gameViewModel =
                ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        View root = inflater.inflate(R.layout.notch, container, false);
        startTime = Instant.now();
        getUI(root);
        setListener();

        handler = new Handler();
        handler.post(this);
        return root;
    }

    @Override
    public void onDestroyView() {
        handler.removeCallbacks(this);
        super.onDestroyView();
    }

    private void getUI(View root) {
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
            if (false) {
                remainingTime = duration.minus(runTime);
            } else {
                remainingTime = runTime;
            }
            if (remainingTime.isNegative()) {
                timer_time.setText("00:00");
            } else {
                timer_time.setText(String.format("%02d:%02d", remainingTime.toMinutes(), remainingTime.getSeconds() % 60));
                handler.postDelayed(this, 1000);
            }
        }
    }

    public interface OnGameRetry {
        public void retryGame();
    }
}
