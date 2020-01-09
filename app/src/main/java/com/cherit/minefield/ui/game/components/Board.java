package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.cherit.minefield.R;

import java.util.Random;

public class Board extends Fragment implements AdapterView.OnClickListener {
    private Field[] fields;
    private int mines_number;
    private int mines_discovered;
    private int discovered_fields;
    private int size;
    private int fields_marked;
    private boolean marking;
    private Context context;
    private GridLayout layout;
    private boolean isGameRunning;

    public Board(int size, int mines_number, GridLayout layout, Context context) {
        mines_discovered = 0;
        fields_marked = 0;
        discovered_fields = 0;
        marking = false;
        isGameRunning = true;
        this.context = context;
        this.size = size;
        this.mines_number = mines_number;
        this.layout = layout;
        layout.setColumnCount(size);
        layout.setRowCount(size);
        generateFields();
        generateMines(mines_number);
    }

    private void generateMines(int mines_number) {
        Random random = new Random();
        int rand_x, rand_y;
        for (int i=0; i<mines_number; i++)
        {
            do {
                rand_x = random.nextInt(size);
                rand_y = random.nextInt(size);
            } while (fields[rand_x* size +rand_y] .isMine());
            fields[rand_x* size +rand_y].setMine();
            addNeighborhood(rand_x, rand_y);
        }
    }

    private void generateFields(){
        Field newField;
        int container_width = (int) context.getResources().getDimension(R.dimen.board_size) - 2 * ((int) context.getResources().getDimension(R.dimen.board_padding));
        fields = new Field[size * size];
        for (int i = 0; i< size; i++)
        {
            for (int j = 0; j< size; j++)
            {
                newField = new Field(i, j, context);
                fields[i* size +j] = newField;
                newField.setOnClickListener(this);
                newField.setLayoutParams(new LinearLayout.LayoutParams(container_width/size, container_width/size));

//                System.out.println(R.dimen.board_size);
                layout.addView(newField);
            }
        }
    };

    private boolean isValidNeighbor(int x, int y){
        return x >=0 && x< size && y>=0 && y< size;
    }
    private void addNeighborhood(int x, int y) {
        if(isValidNeighbor(x-1, y-1)) fields[(x-1)* size +y-1].increaseCloseMines();
        if(isValidNeighbor(x, y-1)) fields[x* size +y-1].increaseCloseMines();
        if(isValidNeighbor(x+1, y-1)) fields[(x+1)* size +y-1].increaseCloseMines();
        if(isValidNeighbor(x-1, y)) fields[(x-1)* size +y].increaseCloseMines();
        if(isValidNeighbor(x+1, y)) fields[(x+1)* size +y].increaseCloseMines();
        if(isValidNeighbor(x-1, y+1)) fields[(x-1)* size +y+1].increaseCloseMines();
        if(isValidNeighbor(x, y+1)) fields[x* size +y+1].increaseCloseMines();
        if(isValidNeighbor(x+1, y+1)) fields[(x+1)* size +y+1].increaseCloseMines();
    }
    public void discoverField(int x, int y){
        if(!fields[x* size +y].isDiscovered() && !fields[x* size +y].isMine() && !fields[x* size +y].isMarked())
        {
            fields[x* size +y].discover();
            discovered_fields++;
            if(fields[x* size +y].getCloseMines() == 0)
            {
                if(isValidNeighbor(x, y-1)) discoverField(x, y-1);
                if(isValidNeighbor(x-1, y)) discoverField(x-1, y);
                if(isValidNeighbor(x+1, y)) discoverField(x+1, y);
                if(isValidNeighbor(x, y+1)) discoverField(x, y+1);
            }
        }
    }
    public void discoverAll() {
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j< size; j++)
            {
                if(!fields[i* size +j].isDiscovered())
                {
                    fields[i* size +j].discover();
                }
            }
        }
    }

    public void onClick(View v) {
        if(isGameRunning) {
            if(v instanceof Field)
            {
                Field field = (Field) v;
                if(!marking){
                    if(!field.isMarked())
                    {
                        if(field.isMine())
                        {
                            field.discover();
                            System.out.println("You lost");
                            isGameRunning = false;
                        }
                        else
                        {
                            discoverField(field.getX_pos(), field.getY_pos());
                            if(size*size == discovered_fields + mines_number || size*size == discovered_fields + mines_discovered + fields_marked)
                            {
                                System.out.println("You won!");
                                discoverAll();
                                isGameRunning = false;
                            }
                        }
                    }
                }
                else {
                    if(!field.isDiscovered()) {
                        fields_marked++;
                        field.setMarked(!field.isMarked());
                    }
                }
            }
        }
    }
}
