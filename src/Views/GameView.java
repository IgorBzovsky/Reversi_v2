package Views;

import Controllers.GameController;
import Model.ReversiGame;

import javax.swing.*;

public class GameView extends JPanel {
    private GameController gameController;
    private ReversiGame reversiGame;

    public GameView(GameController gameController, ReversiGame reversiGame) {
        this.gameController = gameController;
        this.reversiGame = reversiGame;
    }
}
