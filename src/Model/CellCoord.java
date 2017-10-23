package Model;

import java.io.Serializable;

public class CellCoord implements Serializable {
    private int row;
    private int column;

    public CellCoord(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public CellCoord(CellCoord cellCoord){
        this.row = cellCoord.getRow();
        this.column = cellCoord.getColumn();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
