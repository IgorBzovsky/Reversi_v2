package Model;

import java.util.LinkedList;

public interface IGameBoard {
    LinkedList<CellCoord> putDisk(int row, int column, boolean isWhite);

    LinkedList<CellCoord> getDiscsToUpturn(int row, int column, boolean isWhitePlayer);

    boolean isValidMove(int row, int column, boolean isWhitePlayer);

    LinkedList<CellCoord> getAvailableMoves(boolean isWhitePlayer);

    int getRows();

    int getCols();

    CellCoord[] getWhiteStartPosition();

    CellCoord[] getBlackStartPosition();
}
