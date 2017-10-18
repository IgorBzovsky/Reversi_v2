package Views;

import Controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private static final int FRAME_WIDTH  = 800;
    private static final int FRAME_HEIGHT  = 600;

    private GameController gameController;

    public MainView(GameController gameController) throws HeadlessException {
        this.gameController = gameController;
        setTitle("Reversi v2");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createMenu();
        setVisible(true);
    }

    public void createGameView(GameView gameView){

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
        newGame.add(pvAiGameEasy);
        JMenuItem pvAiGameMedium = new JMenuItem("Player vs AI Medium");
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
