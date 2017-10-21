package Model;

import java.util.LinkedList;

public abstract class ReversiGame {

    protected IGameBoard gameBoard;
    protected LinkedList<IGameObserver> observers;
    protected boolean isCurrentPlayerWhite = false;
    protected LinkedList<CellCoord> updatedCells;
    private GameResult gameResult = GameResult.NotFinished;

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
    public boolean isValidMove(int row, int col) { return gameBoard.isValidMove(row, col, isCurrentPlayerWhite); }
    public boolean getIsCurrentPlayerWhite() { return isCurrentPlayerWhite; }
    public int getWhiteDiscsCount() { return gameBoard.getDiscs(true).size(); }
    public int getBlackDiscsCount() { return gameBoard.getDiscs(false).size(); }
    public int getCurrentPlayerDiscsCount() { return gameBoard.getDiscs(isCurrentPlayerWhite).size(); }
    public GameResult getGameResult() { return gameResult; }

    public abstract void move(int row, int col);

    public void addObserver(IGameObserver observer){
        if(observer != null)
            observers.add(observer);
    }

    protected void changePlayer() {
        isCurrentPlayerWhite = !isCurrentPlayerWhite;
    }

    protected boolean isGameOver() {
        if(gameBoard.getAvailableMoves(isCurrentPlayerWhite).size() == 0
                && gameBoard.getAvailableMoves(!isCurrentPlayerWhite).size() == 0) {
            return true;
        }
        return false;
    }

    protected boolean isMissMove() {
        if(gameBoard.getAvailableMoves(isCurrentPlayerWhite).size() == 0) {
            return true;
        }
        return false;
    }

    protected void gameOver(){
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

    protected void missMove(){
        changePlayer();
        for (IGameObserver observer : observers) {
            observer.missMove();
        }
    }
}
