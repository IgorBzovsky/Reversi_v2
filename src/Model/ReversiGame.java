package Model;

import Model.Strategy.AiEasyStrategy;
import Model.Strategy.AiMediumStrategy;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class ReversiGame implements Serializable {

    protected GameBoard gameBoard;
    protected LinkedList<IGameObserver> observers;
    protected boolean isCurrentPlayerWhite = false;
    protected LinkedList<CellCoord> updatedCells;
    private LinkedList<CellCoord> statistics;
    private GameResult gameResult = GameResult.NotFinished;

    public ReversiGame() {
        gameBoard = new GameBoard();
        observers = new LinkedList<IGameObserver>();
        updatedCells = new LinkedList<CellCoord>();
        statistics = new LinkedList<CellCoord>();
        writeStatistics();
    }

    /**
     * Factory methods for creating different types of games
     */
    public static PvPReversiGame createPvPGame(){
        return new PvPReversiGame();
    }
    public static PvAiReversiGame createPvAiEasyGame(boolean isPlayerWhite){
        return new PvAiReversiGame(new AiEasyStrategy(), isPlayerWhite);
    }
    public static PvAiReversiGame createPvAiMediumGame(boolean isPlayerWhite){
        return new PvAiReversiGame(new AiMediumStrategy(), isPlayerWhite);
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
    public int getWhiteDiscsCount() { return gameBoard.getDiscs(true).size(); }
    public int getBlackDiscsCount() { return gameBoard.getDiscs(false).size(); }
    public LinkedList<CellCoord> getDisks(boolean isWhite) {
        return gameBoard.getDiscs(isWhite);
    }
    public GameResult getGameResult() { return gameResult; }
    boolean isValidMove(int row, int col) { return gameBoard.isValidMove(row, col, isCurrentPlayerWhite); }
    public GameBoard cloneGameBoard(){
        return new GameBoard(gameBoard);
    }
    public boolean isGameOver() {
        return gameBoard.getAvailableMoves(isCurrentPlayerWhite).size() == 0
                && gameBoard.getAvailableMoves(!isCurrentPlayerWhite).size() == 0;
    }

    public LinkedList<CellCoord> getStatistics() {
        return statistics;
    }

    /**
     * Game logic
     */
    public abstract void start();
    public abstract void makeTurn(int row, int col);
    public void move(int row, int col) {
        updatedCells = gameBoard.putDisk(row, col, isCurrentPlayerWhite);
        writeStatistics();
        changePlayer();
        for (IGameObserver observer : observers) {
            observer.updateGameBoard();
        }
    }


    public void addObserver(IGameObserver observer){
        if(observer != null)
            observers.add(observer);
    }

    /**
     * Helper methods
     */
    public boolean isMissMove() {
        return gameBoard.getAvailableMoves(isCurrentPlayerWhite).size() == 0;
    }
    void gameOver(){
        int whiteDisks = getWhiteDiscsCount();
        int blackDisks = getBlackDiscsCount();
        if(whiteDisks > blackDisks)
            gameResult = GameResult.WhiteWinner;
        else if(whiteDisks < blackDisks)
            gameResult = GameResult.BlackWinner;
        else
            gameResult = GameResult.Draw;
        for (IGameObserver observer : observers) {
            observer.gameOver();
        }
    }
    void missMove(){
        writeStatistics();
        changePlayer();
        for (IGameObserver observer : observers) {
            observer.missMove();
        }
    }
    private void writeStatistics() { statistics.add(new CellCoord(getBlackDiscsCount(), getWhiteDiscsCount())); }
    private void changePlayer() { isCurrentPlayerWhite = !isCurrentPlayerWhite; }

}
