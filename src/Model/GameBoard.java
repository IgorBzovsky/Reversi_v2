package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class GameBoard {
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final Cell[] startPosition = {
            new Cell(new CellCoord(3, 3), CellType.White),
            new Cell(new CellCoord(4, 3), CellType.Black),
            new Cell(new CellCoord(3, 4), CellType.Black),
            new Cell(new CellCoord(4, 4), CellType.White),
    };

    private Cell[][] cells = null;
    private Cell currentCell = null;

    public GameBoard() {
        cells = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cells[i][j] = new Cell(new CellCoord(i, j));
            }
        }
        for (int i = 0; i < startPosition.length; i++) {
            int row = startPosition[i].getRow();
            int column = startPosition[i].getColumn();
            CellType cellType = startPosition[i].getCellType();
            setCellType(row, column, cellType);
        }
    }

    public void setCellType(int row, int column, CellType cellType){
        cells[row][column].setType(cellType);
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
    public Cell[] getStartPosition() { return startPosition; }
}
