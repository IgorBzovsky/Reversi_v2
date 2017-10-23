package Views;

import Controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

public class MainView extends JFrame {

    private GameController gameController;
    private JPanel screens = null;
    private GameView gameView;

    public MainView(GameController gameController) throws HeadlessException {
        this.gameController = gameController;
        setTitle("Reversi v2");
        setSize(VisualSettings.getWidth(), VisualSettings.getHeight());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createMenu();
        screens = new JPanel(new CardLayout());
        screens.add(new SplashScreen(), VisualSettings.getSplashscreen());
        screens.add(new RulesScreen(), VisualSettings.getRulesscreen());
        add(screens);
        displaySplashScreen();
        setVisible(true);
    }

    public void createGameView(GameView gameView) {
        this.gameView = gameView;
        if(this.gameView != null) {
            screens.add(this.gameView, VisualSettings.getGamescreen());
        }
        try{
            CardLayout cl = (CardLayout)(screens.getLayout());
            cl.show(screens, VisualSettings.getGamescreen());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        Timer timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.start();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void loadGameView(GameView gameView){
        if(this.gameView != null)
            screens.remove(this.gameView);
        this.gameView = gameView;
        screens.add(gameView, VisualSettings.getGamescreen());
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
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.save();
            }
        });
        file.add(save);
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.load();
            }
        });
        file.add(load);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(exit);

        /*Menu Help*/
        JMenu help = new JMenu("Help");
        JMenuItem rules = new JMenuItem("Rules");
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CardLayout cl = (CardLayout)(screens.getLayout());
                    cl.show(screens, VisualSettings.getRulesscreen());
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        help.add(rules);
        //JMenuItem about = new JMenuItem("About");
        //help.add(about);

        menuBar.add(file);
        menuBar.add(help);
        setJMenuBar(menuBar);
    }
}
