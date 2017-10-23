package Views;

import Controllers.GameController;
import Model.CellCoord;
import Model.ReversiGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AiGameView extends GameView {
    private boolean isWhitePlayer;
    public AiGameView(GameController gameController, ReversiGame reversiGame, boolean isWhitePlayer) {
        super(gameController, reversiGame);
        this.isWhitePlayer = isWhitePlayer;
        if(isWhitePlayer)
            infoPanel.setMessage("You are playing White");
        else
            infoPanel.setMessage("You are playing Black");
    }

    public void updateGameBoard() {
        if(isCurrentPlayerWhite == isWhitePlayer){
            super.updateGameBoard();
        }
        else {
            LinkedList<CellCoord> updatedCells = reversiGame.getUpdatedCells();
            for (CellCoord cell : updatedCells) {
                board.putDisk(cell.getRow(), cell.getColumn(), isCurrentPlayerWhite);
            }
            deactivateButtons();
            board.resetBackgroundHighlighting();
            showDisksToUpturn(updatedCells);
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(reversiGame.isGameOver()){
                        board.resetBackgroundHighlighting();
                        board.resetHighlighting();
                        gameOver();
                    }

                    else if(reversiGame.isMissMove())
                        missMove();
                    else {
                        activateButtons();
                        writeStatistics();
                        board.resetBackgroundHighlighting();
                        showAvailableMoves(reversiGame.getAvailableMoves());
                        switchPlayer();
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    @Override
    void switchPlayer() {
        super.switchPlayer();
        if(isCurrentPlayerWhite == isWhitePlayer){
            activateButtons();
        }
        else {

            deactivateButtons();
            board.resetHighlighting();
            board.resetBackgroundHighlighting();
        }
    }
}
