package Controllers;

import Model.CellCoord;
import Model.ReversiGame;
import Views.GameView;
import Views.MainView;

import java.util.LinkedList;

public class GameController {
    private ReversiGame game;
    private MainView mainView;
    private GameView gameView;

    public GameController() {
        mainView = new MainView(this);
    }

    public void startPvPGame() {
        game = ReversiGame.createPvPGame();
        startGame();
    }

    public void startPvAiEasyGame() {
        game = ReversiGame.createPvAiEasyGame();
        startGame();
    }

    public void startPvAiMediumGame() {
        game = ReversiGame.createPvAiMediumGame();
        startGame();
    }

    private void startGame() {
        gameView = new GameView(this, game);
        mainView.displayGameView(gameView);
    }

    public void showDisksToUpturn(int row, int col) {
        if(game == null)
            return;
        LinkedList<CellCoord> cellCoords = game.getDisksToUpturn(row, col);
        gameView.showDisksToUpturn(cellCoords);

    }
}
