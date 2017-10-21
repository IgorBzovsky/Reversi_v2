package Model;

public interface IGameObserver {
    void updateGameBoard();
    void gameOver();
    void missMove();
}
