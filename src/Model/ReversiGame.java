package Model;

import java.util.LinkedList;

public abstract class ReversiGame {

    private IGameBoard gameBoard;
    private LinkedList<IGameObserver> observers;
    private boolean isCurrentPlayerWhite = false;
    private LinkedList<CellCoord> updatedCells;

    public ReversiGame() {
        observers = new LinkedList<IGameObserver>();
        gameBoard = new GameBoard();
        updatedCells = new LinkedList<CellCoord>();
    }

    /**
     * Factory methods for creating different types of games
     */
    public static PvPReversiGame createPvPGame(){
        return new PvPReversiGame();
    }
    public static PvAiReversiGame createPvAiEasyGame(){
        return new PvAiReversiGame(new AiEasyStrategy());
    }
    public static PvAiReversiGame createPvAiMediumGame(){
        return new PvAiReversiGame(new AiMediumStrategy());
    }

    /**
     * API to get board parameters and game information
     */
    public int getBoardLength() { return gameBoard.getRows(); }
    public int getBoardHeight() { return gameBoard.getCols(); }
    public CellCoord[] getWhiteDisksStartPosition() { return gameBoard.getWhiteStartPosition(); }
    public CellCoord[] getBlackDisksStartPosition() { return gameBoard.getBlackStartPosition(); }
    public LinkedList<CellCoord> getAvailableMoves() { return gameBoard.getAvailableMoves(isCurrentPlayerWhite); }
    public LinkedList<CellCoord> getDisksToUpturn(int row, int col) {
        return gameBoard.getDiscsToUpturn(row, col, isCurrentPlayerWhite);
    }
    public LinkedList<CellCoord> getUpdatedCells() { return updatedCells; }
    public boolean getIsCurrentPlayerWhite() { return isCurrentPlayerWhite; }

    /**
     * Game business logic
     */
    public void move(int row, int col) {
        if(!gameBoard.isValidMove(row, col, isCurrentPlayerWhite))
            return;
        updatedCells = gameBoard.putDisk(row, col, isCurrentPlayerWhite);
        for (IGameObserver observer : observers) {
            observer.updateGameBoard();
        }
    }

    public void addObserver(IGameObserver observer){
        if(observer != null)
            observers.add(observer);
    }
}
