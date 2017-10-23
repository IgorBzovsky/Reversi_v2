package Model;

import java.io.Serializable;

public class Cell implements Serializable {
    private CellType cellType;
    private CellCoord cellCoord;

    public Cell(CellCoord cellCoord) {
        this.cellCoord = cellCoord;
        cellType = CellType.Empty;
    }

    public Cell(CellCoord cellCoord, CellType cellType) {
        this(cellCoord);
        this.cellType = cellType;
    }

    public Cell(Cell another){
        cellType = another.cellType;
        cellCoord = new CellCoord(another.cellCoord);
    }

    public CellType getCellType() {
        return cellType;
    }
    public int getRow() { return cellCoord.getRow(); }
    public int getColumn() { return cellCoord.getColumn(); }

    public void setType(CellType cellType) { this.cellType = cellType; }
    public void setWhite() {
        cellType = CellType.White;
    }
    public void setBlack() {
        cellType = CellType.Black;
    }
    public void setEmpty() {
        cellType = CellType.Empty;
    }
}
