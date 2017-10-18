package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class GameBoard {
    private static final int ROWS = 8;
    private static final int COLS = 8;

    private Cell[][] cells = null;
    private Cell currentCell = null;

    public GameBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cells[i][j] = new Cell();
            }
        }
        this.cells = cells;
    }

    public void putWhiteCell(int row, int column) {
        cells[row][column].setWhite();
    }

    public void putBlackCell(int row, int column) {
        cells[row][column].setBlack();
    }

    public void emptyCell(int row, int column) {
        cells[row][column].setEmpty();
    }

    public void putWhiteDisk(int row, int column) {
    }

    public void putBlackDisk(int row, int column) {
    }

    public LinkedList<CellCoord> getDiscsToUpturn(){
        throw new NotImplementedException();
    }
    public LinkedList<CellCoord> getAvailableMoves(int row, int column){
        throw new NotImplementedException();
    }
}
