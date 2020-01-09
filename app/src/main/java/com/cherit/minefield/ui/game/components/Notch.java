package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.ui.game.GameViewModel;

import java.time.Instant;

import javax.xml.datatype.Duration;

public class Notch extends Fragment implements Runnable{
    private Context context;
    private TextView timer_time;
    private TextView timer_text;
    private GameViewModel gameViewModel;
    private Duration duration;
    private Instant startTime;
    Notch(){
        super();
        gameViewModel =
                ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        startTime = Instant.now();
        new Thread(this).start();
    }


    @Override
    public void run() {
        startTime = Instant.now();
        Duration runTime;
        Duration remainingTime;
        while (true) {
            runTime = Duration.between(startTime, Instant.now());
            if(isDecreasing)
            {
                remainingTime = duration.minus(runTime);
                timer_time.setText(remainingTime.toString());
            }
            else
            {
                remainingTime = runTime;
                timer_time.setText(remainingTime.toString());
            }
            if (remainingTime.isNegative()) {
                System.out.println("Out of time");
                timer_time.setText(remainingTime.toString());
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
