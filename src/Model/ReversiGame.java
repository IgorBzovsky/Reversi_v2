package Model;

import Model.Strategy.AiEasyStrategy;
import Model.Strategy.AiMediumStrategy;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class ReversiGame implements IReversiGame, Serializable {

    private GameBoard gameBoard;
    private GameBoard savedGameBoard;
    protected LinkedList<IGameObserver> observers;
    protected LinkedList<IGameObserver> savedObservers;
    private boolean isCurrentPlayerWhite = false;
    private LinkedList<CellCoord> updatedCells;
    private LinkedList<CellCoord> statistics;
    private GameResult gameResult = GameResult.NotFinished;

    public ReversiGame() {
        observers = new LinkedList<IGameObserver>();
        gameBoard = new GameBoard();
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

    @Override
    public void setSimulationMode() {
        if(gameBoard != null){
            savedGameBoard = gameBoard;
            gameBoard = new GameBoard(gameBoard);
        }
        if(observers != null) {
            savedObservers = observers;
            observers = new LinkedList<IGameObserver>();
        }
    }

    @Override
    public void setGameMode() {
        if(savedGameBoard != null){
            gameBoard = savedGameBoard;
            savedGameBoard = null;
        }
        if(savedObservers != null) {
            observers = savedObservers;
            savedObservers = null;
        }
    }

    public void addObserver(IGameObserver observer){
        if(observer != null)
            observers.add(observer);
    }

    public boolean isGameOver() {
        return gameBoard.getAvailableMoves(isCurrentPlayerWhite).size() == 0
                && gameBoard.getAvailableMoves(!isCurrentPlayerWhite).size() == 0;
    }

    @Override
    public void move(int row, int col) {
        if(!isValidMove(row, col))
            return;
        updatedCells = gameBoard.putDisk(row, col, isCurrentPlayerWhite);
        writeStatistics();
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
        }
    }

    public LinkedList<CellCoord> getStatistics() {
        return statistics;
    }

    private void writeStatistics() { statistics.add(new CellCoord(getBlackDiscsCount(), getWhiteDiscsCount())); }

    private void changePlayer() {
        isCurrentPlayerWhite = !isCurrentPlayerWhite;
    }

    private boolean isMissMove() {
        return gameBoard.getAvailableMoves(isCurrentPlayerWhite).size() == 0;
    }

    private void gameOver(){
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

    private void missMove(){
        writeStatistics();
        changePlayer();
        for (IGameObserver observer : observers) {
            observer.missMove();
        }
    }
}
