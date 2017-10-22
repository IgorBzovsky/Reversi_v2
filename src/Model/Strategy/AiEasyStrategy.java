package Model.Strategy;

import Model.CellCoord;
import Model.IReversiGame;
import Model.ReversiGame;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class AiEasyStrategy implements AiStrategy, Serializable {

    private CellCoord calculateMove(IReversiGame reversiGame) {
        LinkedList<CellCoord> availableCells = reversiGame.getAvailableMoves();
        Random rand = new Random();
        return availableCells.get(rand.nextInt(availableCells.size()));
    }

    @Override
    public void move(ReversiGame reversiGame) {
        if(!reversiGame.isGameOver()){
            CellCoord moveCoord = calculateMove(reversiGame);
            reversiGame.move(moveCoord.getRow(), moveCoord.getColumn());
        }
    }
}
