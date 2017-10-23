package Model;

import Model.Strategy.AiStrategy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class PvAiReversiGame extends ReversiGame implements Serializable {

    private AiStrategy strategy;
    private boolean isWhitePlayer;
    public PvAiReversiGame(AiStrategy strategy, boolean isWhitePlayer) {
        this.isWhitePlayer = isWhitePlayer;
        this.strategy = strategy;
    }

    public void start() {
        for (IGameObserver observer : observers) {
            observer.start();
            observer.updateGameBoard();
        }
        if(isWhitePlayer){
            strategy.move(this);
        }
    }
    //True if player is white, AI is black
    public boolean isWhitePlayer() {
        return isWhitePlayer;
    }
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
            return;
        }
        Timer timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aiMove();
            }
        });
        timer.setRepeats(false);
        timer.start();

    }

    private void aiMove(){
        strategy.move(this);
        if(isGameOver()) {
            gameOver();
            return;
        }
        while(isMissMove()) {
            missMove();
            if(isGameOver()) {
                gameOver();
                return;
            }
            strategy.move(this);
        }
    }
}
