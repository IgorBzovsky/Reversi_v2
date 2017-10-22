package Model.Strategy;

import Model.CellCoord;
import Model.ReversiGame;

import java.io.Serializable;
import java.util.LinkedList;

public class AiMediumStrategy implements AiStrategy, Serializable {
    private int[][] boardMatrix;
    private LinkedList<CellCoord> availableMoves;

    @Override
    public void move(ReversiGame reversiGame) {
        reversiGame.setSimulationMode();
    }

    private void simulateMoves(ReversiGame reversiGame){

    }
}
