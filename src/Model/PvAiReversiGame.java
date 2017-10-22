package Model;

import Model.Strategy.AiStrategy;

import java.io.Serializable;

public class PvAiReversiGame extends ReversiGame implements Serializable {

    private AiStrategy strategy;
    private boolean isWhitePlayer;
    public PvAiReversiGame(AiStrategy strategy, boolean isWhitePlayer) {
        this.isWhitePlayer = isWhitePlayer;
        this.strategy = strategy;
    }

    @Override
    public void makeTurn(int row, int col) {
        if(!isValidMove(row, col))
            return;
        move(row, col);
        strategy.move(this);
    }

    @Override
    public void start() {
        for (IGameObserver observer : observers) {
            observer.start();
            observer.updateGameBoard();
        }
        if(isWhitePlayer){
            strategy.move(this);
        }
    }

    public boolean isWhitePlayer() {
        return isWhitePlayer;
    }
}
