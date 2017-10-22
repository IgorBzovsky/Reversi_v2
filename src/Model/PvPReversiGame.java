package Model;

import java.io.Serializable;

public class PvPReversiGame extends ReversiGame implements Serializable {

    @Override
    public void makeTurn(int row, int col) {
        move(row, col);
    }

    @Override
    public void start() {
        for (IGameObserver observer : observers) {
            observer.start();
            observer.updateGameBoard();
        }
    }
}
