package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cherit.minefield.R;

public class Dialog extends DialogFragment {
    private OnGameEndedListener listener;
    private Button retryBtn;
    private Button exitBtn;
    private TextView textContent;
    private boolean isGameWon;

    Dialog(boolean isGameWon){
        this.isGameWon = isGameWon;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGameEndedListener) {
            listener = (OnGameEndedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnGameEndedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
        getUI(root);
        setListeners();
        if (isGameWon){
            textContent.setTextColor(Color.GREEN);
            textContent.setText("You won!");
        }
        else{
            textContent.setTextColor(Color.RED);
            textContent.setText("You lost!");
        }
        return root;
    }

    public void getUI(View root){
        retryBtn = root.findViewById(R.id.retryBtn);
        exitBtn = root.findViewById(R.id.exitBtn);
        textContent = root.findViewById(R.id.dialog_content);
    }

    public void setListeners(){
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.retryGame();
                getDialog().dismiss();
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.endGame();
                getDialog().dismiss();
            }
        });
    }

    public interface OnGameEndedListener {
        public void endGame();
        public void retryGame();
    }
}
