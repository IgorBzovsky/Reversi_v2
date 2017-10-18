package Model;

public class Cell {
    private CellType cellType;

    public Cell() {
        cellType = CellType.Empty;
    }

    public CellType getCellType() {
        return cellType;
    }

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
