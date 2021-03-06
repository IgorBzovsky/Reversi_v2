package Controllers;

import Model.CellCoord;
import Model.ReversiGame;
import Views.AiGameView;
import Views.GameView;
import Views.MainView;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class GameController implements Serializable {
    private ReversiGame game;
    private MainView mainView;
    private GameView gameView;

    public GameController() {
        mainView = new MainView(this);
    }

    /**
     * Game initialization
     */
    public void startPvPGame() {
        game = ReversiGame.createPvPGame();
        initPvPGame();
    }
    public void startPvAiEasyGame() {
        boolean isPlayerWhite = new Random().nextBoolean();
        game = ReversiGame.createPvAiEasyGame(isPlayerWhite);
        initAiGame(isPlayerWhite);
    }
    public void startPvAiMediumGame() {
        boolean isPlayerWhite = new Random().nextBoolean();
        game = ReversiGame.createPvAiMediumGame(isPlayerWhite);
        initAiGame(isPlayerWhite);
    }
    private void initPvPGame() {
        gameView = new GameView(this, game);
        mainView.createGameView(gameView);
    }
    private void initAiGame(boolean isWhitePlayer) {
        gameView = new AiGameView(this, game, isWhitePlayer);
        mainView.createGameView(gameView);
    }

    /**
     * Game board interaction
     */
    public void start() {
        game.start();
    }

    public void showDisksToUpturn(int row, int col) {
        if(game == null)
            return;
        LinkedList<CellCoord> cellCoords = game.getDisksToUpturn(row, col);
        gameView.showDisksToUpturn(cellCoords);

    }
    public void makeTurn(int row, int col) {
        if(game != null)
            game.makeTurn(row, col);
    }

    /**
     * Menu interaction
     */
    public void save() {
        if(gameView == null)
            return;
        try {
            File file = new File("src/save.ser");
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
            FileOutputStream fileOut = new FileOutputStream("src/save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /src/save.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void load() {
        ReversiGame game = null;
        try {
            FileInputStream fileIn = new FileInputStream("src/save.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (ReversiGame) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("ReversiGame class not found");
            c.printStackTrace();
            return;
        }
        if(game != null){
            this.game = game;
            this.gameView = new GameView(this, game);
            this.gameView.restore(game);
            mainView.loadGameView(this.gameView);
        }
    }
}
