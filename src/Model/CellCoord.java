package Model;

import java.io.Serializable;

public class CellCoord implements Serializable {
    private int row;
    private int column;

    public CellCoord(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
