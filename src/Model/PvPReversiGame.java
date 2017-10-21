package Model;

public class PvPReversiGame extends ReversiGame {
    public PvPReversiGame() {

    }

    /**
     * Game business logic
     */
    public void move(int row, int col) {
        if(!isValidMove(row, col))
            return;
        updatedCells = gameBoard.putDisk(row, col, isCurrentPlayerWhite);
        changePlayer();
        for (IGameObserver observer : observers) {
            observer.updateGameBoard();
        }
        if(isGameOver()) {
            gameOver();
            return;
        }
        if(isMissMove()) {
            missMove();
            return;
        }
    }
}
