package Controllers;

import Model.ReversiGame;
import Views.GameView;
import Views.MainView;

public class GameController {
    private ReversiGame game;
    private MainView mainView;

    public GameController() {
        mainView = new MainView(this);
    }

    public void startPvPGame() {
        game = ReversiGame.createPvPGame();
        mainView.displayGameView(new GameView(this, game));
        game.start();
    }

    public void startPvAiEasyGame() {
        game = ReversiGame.createPvAiEasyGame();
        mainView.displayGameView(new GameView(this, game));
        game.start();
    }

    public void startPvAiMediumGame() {
        game = ReversiGame.createPvAiMediumGame();
        mainView.displayGameView(new GameView(this, game));
        game.start();
    }
}
