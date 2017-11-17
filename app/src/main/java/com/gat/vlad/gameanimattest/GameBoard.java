package com.gat.vlad.gameanimattest;

import java.util.ArrayList;

/**
 * @author Vladyslav_Koveha
 */

public class GameBoard {

    private  int sizeBoard;
    private  int columns;
    private  int rows;

    private ArrayList<GexagonUnit> gexUnits = new ArrayList<>();

    public GameBoard(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        sizeBoard = columns * rows;
        createGexBoard();
    }

    private void createGexBoard (){
        for (int i = 0; i < sizeBoard; i++){
            int r = (int) i/rows;
            int c = (int) i - r * columns;
            gexUnits.add(new GexagonUnit(i,c,r));
        }
    }


    public ArrayList<GexagonUnit> getGexUnits() {
        return gexUnits;
    }
}
