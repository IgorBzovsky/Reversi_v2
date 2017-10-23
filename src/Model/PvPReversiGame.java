package Model;

import java.io.Serializable;

public class PvPReversiGame extends ReversiGame implements Serializable {

    public void makeTurn(int row, int col) {
        if(!isValidMove(row, col))
            return;
        move(row, col);
        if(isGameOver()) {
            gameOver();
            return;
        }
        if(isMissMove()) {
            missMove();
        }
    }

    public void start() {
        for (IGameObserver observer : observers) {
            observer.start();
            observer.updateGameBoard();
        }
    }
}
