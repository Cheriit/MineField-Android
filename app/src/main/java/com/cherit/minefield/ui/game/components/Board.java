package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cherit.minefield.R;
import com.cherit.minefield.ui.game.GameViewModel;
import com.cherit.minefield.ui.settings.SettingsViewModel;

import java.util.ArrayList;
import java.util.Random;

import static android.os.SystemClock.sleep;

public class Board extends Fragment implements AdapterView.OnClickListener {
    private GameViewModel gameViewModel;
    private SettingsViewModel settingsViewModel;
    private Field[] fields;
    private int mines_number;
    private int mines_discovered;
    private int discovered_fields;
    private int size;
    private int fields_marked;
    private Context context;
    private GridLayout layout;
    private int[] numbers = {
            R.drawable.num_1, R.drawable.num_2, R.drawable.num_3, R.drawable.num_4,
            R.drawable.num_5, R.drawable.num_6, R.drawable.num_7, R.drawable.num_8,
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gameViewModel =
                ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        settingsViewModel =
                ViewModelProviders.of(getActivity()).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.board, container, false);
        getUI(root);
        setParams();
        generateFields();
        generateMines();
        setFields();
        return root;
    }

    private void getUI(View root) {
        layout = root.findViewById(R.id.game_board);
    }

    private void setParams() {
        mines_discovered = 0;
        fields_marked = 0;
        discovered_fields = 0;
        this.context = getActivity();
        size = settingsViewModel.getBoardSize().getValue();
        mines_number = settingsViewModel.getMinesNumber().getValue();
        layout.setColumnCount(size);
        layout.setRowCount(size);
    }

    private void generateMines() {
        Random random = new Random();
        int rand_x, rand_y;
        for (int i = 0; i < mines_number; i++) {
            do {
                rand_x = random.nextInt(size);
                rand_y = random.nextInt(size);
            } while (fields[rand_x * size + rand_y].isMine());
            fields[rand_x * size + rand_y].setMine();
            addNeighborhood(rand_x, rand_y);
        }
    }

    private void generateFields() {
        Field newField;
        int container_width = (int) context.getResources().getDimension(R.dimen.board_size) - 2 * ((int) context.getResources().getDimension(R.dimen.board_padding));
        fields = new Field[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newField = new Field(i, j, gameViewModel.getImageSet()[2], gameViewModel.getImageSet()[1], context);
                fields[i * size + j] = newField;
                newField.setOnClickListener(this);
                newField.setLayoutParams(new LinearLayout.LayoutParams(container_width / size, container_width / size));
                layout.addView(newField);
            }
        }
    }

    ;

    private boolean isValidNeighbor(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private void addNeighborhood(int x, int y) {
        if (isValidNeighbor(x - 1, y - 1)) fields[(x - 1) * size + y - 1].increaseCloseMines();
        if (isValidNeighbor(x, y - 1)) fields[x * size + y - 1].increaseCloseMines();
        if (isValidNeighbor(x + 1, y - 1)) fields[(x + 1) * size + y - 1].increaseCloseMines();
        if (isValidNeighbor(x - 1, y)) fields[(x - 1) * size + y].increaseCloseMines();
        if (isValidNeighbor(x + 1, y)) fields[(x + 1) * size + y].increaseCloseMines();
        if (isValidNeighbor(x - 1, y + 1)) fields[(x - 1) * size + y + 1].increaseCloseMines();
        if (isValidNeighbor(x, y + 1)) fields[x * size + y + 1].increaseCloseMines();
        if (isValidNeighbor(x + 1, y + 1)) fields[(x + 1) * size + y + 1].increaseCloseMines();
    }

    private void discoverField(int x, int y) {
        if (!fields[x * size + y].isDiscovered() && !fields[x * size + y].isMine() && !fields[x * size + y].isMarked()) {
            fields[x * size + y].discover();
            discovered_fields++;
            if (fields[x * size + y].getCloseMines() == 0) {
                if (isValidNeighbor(x + 1, y - 1)) discoverField(x + 1, y - 1);
                if (isValidNeighbor(x + 1, y)) discoverField(x + 1, y);
                if (isValidNeighbor(x + 1, y + 1)) discoverField(x + 1, y + 1);
                if (isValidNeighbor(x, y - 1)) discoverField(x, y - 1);
                if (isValidNeighbor(x, y + 1)) discoverField(x, y + 1);
                if (isValidNeighbor(x - 1, y - 1)) discoverField(x - 1, y - 1);
                if (isValidNeighbor(x - 1, y)) discoverField(x - 1, y);
                if (isValidNeighbor(x - 1, y + 1)) discoverField(x - 1, y + 1);
            }
        }
    }

    private void discoverAll() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!fields[i * size + j].isDiscovered()) {
                    fields[i * size + j].discover();
                }
            }
        }
    }

    public void onClick(View v) {
        if (gameViewModel.isGameRunning()) {
            if (v instanceof Field) {
                Field field = (Field) v;
                if (!gameViewModel.isMarking()) {
                    if (!field.isMarked()) {
                        if (field.isMine()) {
                            field.discover();
                            gameViewModel.setGameRunning(false);
                            showDialog(false);
                        } else {
                            discoverField(field.getX_pos(), field.getY_pos());
                            if (size * size == discovered_fields + mines_number || size * size == discovered_fields + mines_discovered + fields_marked) {
                                discoverAll();
                                gameViewModel.setGameRunning(false);
                                showDialog(true);
                            }
                        }
                    }
                } else {
                    if (!field.isDiscovered()) {
                        fields_marked++;
                        field.setMarked(!field.isMarked());
                    }
                }
            }
        }
    }
    private void setFields(){
        for (int i=0; i<fields.length; i++){
            if(fields[i].isMine()){
                fields[i].setDescriptionImage(gameViewModel.getImageSet()[0]);
            }else if(fields[i].getCloseMines()>0) {
                fields[i].setDescriptionImage(numbers[fields[i].getCloseMines() - 1]);
            }
        }
    }

    public void showDialog(boolean isGameWon){
        Dialog dialog = new Dialog(isGameWon);
        sleep(10);
        dialog.show(getFragmentManager(), "Dialog");
    }
}
