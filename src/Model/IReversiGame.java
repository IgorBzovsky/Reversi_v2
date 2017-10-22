package Model;

import java.io.Serializable;
import java.util.LinkedList;

public interface IReversiGame extends Serializable {
    LinkedList<CellCoord> getAvailableMoves();
    void makeTurn(int row, int col);
    void move(int row, int col);
    void start();
    boolean isGameOver();
    void setSimulationMode();
    void setGameMode();
}
