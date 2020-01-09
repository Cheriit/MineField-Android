package com.cherit.minefield.ui.game.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;

import com.cherit.minefield.R;

import java.util.Random;

public class Board extends GridLayout implements AdapterView.OnClickListener {
    private Field[] fields;
    private int mines_number;
    private int mines_discovered;
    private int fields_marked;
    private int discovered_fields;
    private int size;
    private Context root;
    private int grid_id;

    public Board(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.board, this, true);
        init(context);
    }
    public Board(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.board, this, true);
        init(context);
    }

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.board, this, true);
        init(context);
    }

    public void init(Context context)
    {
        mines_discovered = 0;
        fields_marked = 0;
        discovered_fields = 0;
        root = context;
    }

    public void setup(int size, int mines_number)
    {
        this.size = size;
        setColumnCount(size);
        setRowCount(size);
        this.mines_number = mines_number;
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
        fields = new Field[size * size];
        for (int i = 0; i< size; i++)
        {
            for (int j = 0; j< size; j++)
            {
                fields[i* size +j] = new Field(i,j, root);
                addView(fields[i* size +j]);
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
//        Object source = event.getSource();
//        if(controller.isGameRunning())
//        {
//            if(source.getClass().getName() == "com.cherit.Field")
//            {
//                Field field = (Field) source;
//                if(SwingUtilities.isLeftMouseButton(mouseEvent)){
//                    if(!field.isMarked())
//                    {
//                        if(field.isMine())
//                        {
//                            field.discover();
//                            controller.stopGame(false);
//                        }
//                        else
//                        {
//                            discoverField(field.getX_pos(), field.getY_pos());
//                            if(size*size == discovered_fields + mines_number || size*size == discovered_fields + mines_discovered + fields_marked)
//                            {
//                                discoverAll();
//                                controller.stopGame(true);
//                            }
//                        }
//                    }
//                }
//                else if (SwingUtilities.isRightMouseButton(mouseEvent))
//                {
//                    if(!field.isDiscovered())
//                    {
//                        fields_marked++;
//                        field.setMarked(!field.isMarked());
//                    }
//                }
//
//            }
//        }
    }


}
