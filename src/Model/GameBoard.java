package Model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class GameBoard implements IGameBoard {
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final CellCoord[] START_WHITE_POSITION = {
            new CellCoord(3, 3),
            new CellCoord(4, 4)
    };
    private static final CellCoord[] START_BLACK_POSITION = {
            new CellCoord(4, 3),
            new CellCoord(3, 4)
    };
    /**
     * Directions to check game board cells
     */
    private static final CellCoord[] DIRECTIONS = {
            new CellCoord(0, 1),
            new CellCoord(0, -1),
            new CellCoord(1, 0),
            new CellCoord(-1, 0),
            new CellCoord(1, -1),
            new CellCoord(-1, -1),
            new CellCoord(1, 1),
            new CellCoord(-1, 1)
    };

    private Cell[][] cells = null;

    public GameBoard() {
        cells = new Cell[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                cells[i][j] = new Cell(new CellCoord(i, j));
            }
        }
        setStartPosition();
    }

    /**
     * Business logic API
     */
    @Override
    //Returns list of newly changed cells
    public LinkedList<CellCoord> putDisk(int row, int column, boolean isWhite) {
        LinkedList<CellCoord> discsToUpturn = getDiscsToUpturn(row, column, isWhite);
        if(discsToUpturn.size() == 0)
            return discsToUpturn;
        discsToUpturn.add(new CellCoord(row, column));
        for (CellCoord disk : discsToUpturn) {
            Cell cell = cells[disk.getRow()][disk.getColumn()];
            if (isWhite)
                cell.setWhite();
            else
                cell.setBlack();
        }
        return discsToUpturn;
    }

    @Override
    public LinkedList<CellCoord> getDiscsToUpturn(int row, int column, boolean isWhitePlayer) {
        CellType playerDiscType = isWhitePlayer ? CellType.White : CellType.Black;
        CellType opponentDiscType = isWhitePlayer ? CellType.Black : CellType.White;
        LinkedList<CellCoord> discsToUpturn = new LinkedList<>();
        if(cells[row][column].getCellType() != CellType.Empty)
            return discsToUpturn;
        for (CellCoord direction: DIRECTIONS) {
            LinkedList<CellCoord> discsToUpturnInDirection = new LinkedList<>();
            int deltaRow = direction.getRow();
            int deltaColumn = direction.getColumn();
            int currentRow = row + deltaRow;
            int currentColumn = column + deltaColumn;
            boolean foundOpponentDiscType = false;
            while(currentRow >= 0 && currentRow < ROWS && currentColumn >= 0 && currentColumn < COLS) {
                CellType currentCellType = cells[currentRow][currentColumn].getCellType();
                if(currentCellType == CellType.Empty)
                    break;
                else if(currentCellType == playerDiscType && discsToUpturnInDirection.size() > 0) {
                    discsToUpturn.addAll(discsToUpturnInDirection);
                    break;
                }

                else if(currentCellType == opponentDiscType) {
                    discsToUpturnInDirection.add(new CellCoord(currentRow, currentColumn));
                }
                else break;
                currentRow += deltaRow;
                currentColumn += deltaColumn;
            }
        }
        return discsToUpturn;
    }

    @Override
    public boolean isValidMove(int row, int column, boolean isWhitePlayer) {
        if(cells[row][column].getCellType() != CellType.Empty)
            return false;
        CellType playerDiscType = isWhitePlayer ? CellType.White : CellType.Black;
        CellType opponentDiscType = isWhitePlayer ? CellType.Black : CellType.White;
        for (CellCoord direction: DIRECTIONS) {
            int deltaRow = direction.getRow();
            int deltaColumn = direction.getColumn();
            int currentRow = row + deltaRow;
            int currentColumn = column + deltaColumn;
            boolean foundOpponentDiscType = false;
            while(currentRow >= 0 && currentRow < ROWS && currentColumn >= 0 && currentColumn < COLS) {
                CellType currentCellType = cells[currentRow][currentColumn].getCellType();
                if(currentCellType == CellType.Empty)
                    break;
                else if(currentCellType == playerDiscType && foundOpponentDiscType)
                    return true;
                else if(currentCellType == opponentDiscType)
                    foundOpponentDiscType = true;
                else break;
                currentRow += deltaRow;
                currentColumn += deltaColumn;
            }
        }
        return false;
    }

    @Override
    public LinkedList<CellCoord> getAvailableMoves(boolean isWhitePlayer){
        LinkedList<CellCoord> cellCoords = new LinkedList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(isValidMove(i, j, isWhitePlayer))
                    cellCoords.add(new CellCoord(i, j));
            }
        }
        return cellCoords;
    }

    @Override
    public LinkedList<CellCoord> getDiscs(boolean isWhitePlayer) {
        CellType discType = isWhitePlayer ? CellType.White : CellType.Black;
        LinkedList<CellCoord> cellCoords = new LinkedList<CellCoord>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(cells[i][j].getCellType() == discType)
                    cellCoords.add(new CellCoord(i, j));
            }
        }
        return cellCoords;
    }

    /**
     * Board parameters API
     */
    @Override
    public int getRows(){ return ROWS; }
    @Override
    public int getCols(){ return COLS; }
    @Override
    public CellCoord[] getWhiteStartPosition() { return START_WHITE_POSITION; }
    @Override
    public CellCoord[] getBlackStartPosition() { return START_BLACK_POSITION; }

    /**
     * Set initial disks position
     */
    private void setStartPosition() {
        for (int i = 0; i < START_WHITE_POSITION.length; i++) {
            int row = START_WHITE_POSITION[i].getRow();
            int column = START_WHITE_POSITION[i].getColumn();
            setCellType(row, column, CellType.White);
        }
        for (int i = 0; i < START_BLACK_POSITION.length; i++) {
            int row = START_BLACK_POSITION[i].getRow();
            int column = START_BLACK_POSITION[i].getColumn();
            setCellType(row, column, CellType.Black);
        }
    }

    private void setCellType(int row, int column, CellType cellType){
        cells[row][column].setType(cellType);
    }
}
