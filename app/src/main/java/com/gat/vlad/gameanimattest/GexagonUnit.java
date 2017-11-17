package com.gat.vlad.gameanimattest;

/**
 * Класс модели описания одного поля (гексагона)
 * @author Vladyslav_Koveha
 */

public class GexagonUnit  {

    private int numGexagon;
   // private int sizeBoard;

    private int column;
    private int row;

    private CoordGexRect coord;

    public GexagonUnit(int numGexagon, int column, int row) {
        this.numGexagon = numGexagon;
        this.column = column;
        this.row = row;

        coord = new CoordGexRect(150,column,row);
    }

    private int [] calcColumRow (int numGexagon, int sizeBoard){

        return null;
    }

    public int getNumGexagon() {
        return numGexagon;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public CoordGexRect getCoord() {
        return coord;
    }
}
