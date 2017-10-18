package Model;

public class CellCoord {
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
