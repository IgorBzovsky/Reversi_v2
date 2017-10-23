package Views;

import Controllers.GameController;
import Model.*;
import Views.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.LinkedList;
import javax.swing.Timer;

public class GameView extends JPanel implements IGameObserver {

    private GameController gameController;
    private PlayerPanel playerPanel = null;
    private StatisticsTable statisticsTable = null;
    private boolean isBoardActive = true;

    ReversiGame reversiGame;
    BoardPanel board = null;
    InfoPanel infoPanel = null;
    boolean isCurrentPlayerWhite = false;

    /**
     * PvP game constructor
     */
    public GameView(GameController gameController, ReversiGame reversiGame) {
        this.gameController = gameController;
        this.reversiGame = reversiGame;
        setLayout(new BorderLayout());
        setSize(VisualSettings.getGameViewWidth(), VisualSettings.getGameViewHeight());
        createBoard(reversiGame.getBoardLength(), reversiGame.getBoardHeight());
        createStatisticsPanel();
        setVisible(true);
        reversiGame.addObserver(this);
        isCurrentPlayerWhite = reversiGame.getIsCurrentPlayerWhite();
        setPlayer(isCurrentPlayerWhite);
    }

    /**
     * UI logic
     */
    @Override
    public void start() {
        board.setCellActionListener(new CellActionListener());
        board.setCellMouseListener(new CellMouseListener());
    }
    @Override
    public void updateGameBoard() {
        LinkedList<CellCoord> updatedCells = reversiGame.getUpdatedCells();
        for (CellCoord cell : updatedCells) {
            board.putDisk(cell.getRow(), cell.getColumn(), isCurrentPlayerWhite);
        }
        writeStatistics();
        board.resetBackgroundHighlighting();
        showAvailableMoves(reversiGame.getAvailableMoves());
        switchPlayer();
    }

    @Override
    public void missMove() {
        updateGameBoard();
        if(!isCurrentPlayerWhite)
            playerPanel.setMessage("White missed move! Current move Black!");
        else
            playerPanel.setMessage("Black missed move! Current move White!");
    }
    @Override
    public void gameOver() {
        GameResult gameResult = reversiGame.getGameResult();
        if(gameResult == GameResult.WhiteWinner)
            playerPanel.setMessage("Game Over! White wins!!!");
        else if(gameResult == GameResult.BlackWinner)
            playerPanel.setMessage("Game Over! Black wins!!!");
        deactivateButtons();
    }
    public void showDisksToUpturn(LinkedList<CellCoord> disks) {
        board.resetBackgroundHighlighting();
        board.highlightBackgroundCells(disks);
    }

    /**
     * Restoring view from game
     */
    public void restore(ReversiGame game) {
        reversiGame = game;
        board.clear();
        statisticsTable.clear();
        for (CellCoord cellCoord : game.getDisks(true)) {
            board.putWhiteDisk(cellCoord.getRow(), cellCoord.getColumn());
        }
        for (CellCoord cellCoord : game.getDisks(false)) {
            board.putBlackDisk(cellCoord.getRow(), cellCoord.getColumn());
        }
        for (CellCoord pair : game.getStatistics()) {
            statisticsTable.addRecord(pair.getRow(), pair.getColumn());
        }
        if(game instanceof PvAiReversiGame){
            if((((PvAiReversiGame) game).isWhitePlayer())){
                infoPanel.setMessage("You are playing White");
            }
            else
                infoPanel.setMessage("You are playing Black");
        }
        if(game.isGameOver()){
            gameOver();
        }
        else {
            showAvailableMoves(game.getAvailableMoves());
            isCurrentPlayerWhite = game.getIsCurrentPlayerWhite();
            setPlayer(isCurrentPlayerWhite);
            start();
        }
    }

    /**
     * Helper methods
     */
    void activateButtons() {
        isBoardActive = true;
    }
    void deactivateButtons() {
        isBoardActive = false;
    }
    void showAvailableMoves(LinkedList<CellCoord> cellCoords) {
        board.resetHighlighting();
        board.highlightCells(cellCoords);
    }
    void writeStatistics() {
        statisticsTable.addRecord(reversiGame.getBlackDiscsCount(), reversiGame.getWhiteDiscsCount());
    }
    void setPlayer(boolean isCurrentPlayerWhite) {
        if(isCurrentPlayerWhite)
            playerPanel.setMessage("Current move: White");
        else
            playerPanel.setMessage("Current move: Black");
    }
    void switchPlayer() {
        isCurrentPlayerWhite = reversiGame.getIsCurrentPlayerWhite();
        setPlayer(isCurrentPlayerWhite);
    }

    /**
     * Creation of components
     */
    private void createBoard(int rows, int cols) {
        JPanel gameBoardContainer = new JPanel();
        gameBoardContainer.setLayout(new BoxLayout(gameBoardContainer, BoxLayout.Y_AXIS));
        board = new BoardPanel(rows, cols);
        playerPanel = new PlayerPanel();
        infoPanel = new InfoPanel();
        gameBoardContainer.add(infoPanel);
        gameBoardContainer.add(board);
        gameBoardContainer.add(playerPanel);
        add(gameBoardContainer, BorderLayout.CENTER);
        for (CellCoord cellCoord : reversiGame.getWhiteDisksStartPosition()) {
            board.putWhiteDisk(cellCoord.getRow(), cellCoord.getColumn());
        }
        for (CellCoord cellCoord : reversiGame.getBlackDisksStartPosition()) {
            board.putBlackDisk(cellCoord.getRow(), cellCoord.getColumn());
        }
        board.highlightCells(reversiGame.getAvailableMoves());
    }

    private void createStatisticsPanel() {
        statisticsTable = new StatisticsTable();
        add(statisticsTable, BorderLayout.EAST);
    }

    /**
     * Action and Mouse listeners
     */
    class CellActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!isBoardActive)
                return;
            Object obj = e.getSource();
            if(obj instanceof CellButton) {
                CellButton cell = (CellButton) obj;
                gameController.makeTurn(cell.getRow(), cell.getCol());
            }
        }
    }

    class CellMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(!isBoardActive)
                return;
            Object obj = e.getSource();
            if (obj instanceof CellButton) {
                CellButton cell = (CellButton) obj;
                gameController.showDisksToUpturn(cell.getRow(), cell.getCol());
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            board.resetBackgroundHighlighting();
        }
    }
}
