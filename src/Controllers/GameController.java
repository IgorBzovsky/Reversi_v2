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
        mainView.createGameView(new GameView(this, game));
    }

    public void startPvAiEasyGame() {
        game = ReversiGame.createPvAiEasyGame();
        mainView.createGameView(new GameView(this, game));
    }

    public void startPvAiMediumGame() {
        game = ReversiGame.createPvAiMediumGame();
        mainView.createGameView(new GameView(this, game));
    }
}
