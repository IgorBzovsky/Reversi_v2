package Views;

import Controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private GameController gameController;
    private JPanel screens = null;

    public MainView(GameController gameController) throws HeadlessException {
        this.gameController = gameController;
        setTitle("Reversi v2");
        setSize(VisualSettings.getWidth(), VisualSettings.getHeight());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createMenu();
        screens = new JPanel(new CardLayout());
        screens.add(new SplashScreen(), VisualSettings.getSplashscreen());
        add(screens);
        displaySplashScreen();
        setVisible(true);
    }

    public void createGameView(GameView gameView) {
        if(gameView != null) {
            screens.add(gameView, VisualSettings.getGamescreen());
        }
    }

    public void displayGameView(GameView gameView){
        try{
            CardLayout cl = (CardLayout)(screens.getLayout());
            cl.show(screens, VisualSettings.getGamescreen());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void displaySplashScreen() {
        try {
            CardLayout cl = (CardLayout)(screens.getLayout());
            cl.show(screens, VisualSettings.getSplashscreen());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        /*Menu File*/
        JMenu file = new JMenu("File");
        JMenu newGame = new JMenu("New");

        JMenuItem pVpGame = new JMenuItem("Player vs Player");
        pVpGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.startPvPGame();
            }
        });
        newGame.add(pVpGame);

        JMenuItem pvAiGameEasy = new JMenuItem("Player vs AI Easy");
        pvAiGameEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.startPvAiEasyGame();
            }
        });
        newGame.add(pvAiGameEasy);
        JMenuItem pvAiGameMedium = new JMenuItem("Player vs AI Medium");
        pvAiGameMedium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.startPvAiMediumGame();
            }
        });
        newGame.add(pvAiGameMedium);
        file.add(newGame);
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        JMenuItem load = new JMenuItem("Load");
        file.add(load);
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        /*Menu Help*/
        JMenu help = new JMenu("Help");
        JMenuItem rules = new JMenuItem("Rules");
        help.add(rules);
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        menuBar.add(file);
        menuBar.add(help);
        setJMenuBar(menuBar);
    }
}
