package Model;

import java.io.Serializable;

public interface IGameObserver extends Serializable {
    void updateGameBoard();
    void gameOver();
    void missMove();
    void start();
}
