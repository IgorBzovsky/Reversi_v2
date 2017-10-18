package Model;

import java.util.LinkedList;

public abstract class ReversiGame {
    private GameBoard gameBoard;
    private LinkedList<IGameObserver> observers;
    public ReversiGame() {
        observers = new LinkedList<IGameObserver>();
        gameBoard = new GameBoard();
    }

    public static PvPReversiGame createPvPGame(){
        return new PvPReversiGame();
    }
    public static PvAiReversiGame createPvAiEasyGame(){
        return new PvAiReversiGame(new AiEasyStrategy());
    }
    public static PvAiReversiGame createPvAiMediumGame(){
        return new PvAiReversiGame(new AiMediumStrategy());
    }

    public void start() {
        for (IGameObserver observer : observers) {
            observer.start(gameBoard.getStartPosition());
        }
    }

    public void addObserver(IGameObserver observer){
        if(observer != null)
            observers.add(observer);
    }
}
