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

public class GameView extends JPanel implements IGameObserver {

    private GameController gameController;
    private ReversiGame reversiGame;

    private BoardPanel board = null;
    private JPanel gameBoardContainer = null;
    private CellButton currentCell = null;
    private PlayerPanel playerPanel = null;
    private InfoPanel infoPanel = null;
    private StatisticsTable statisticsTable = null;
    private boolean isCurrentPlayerWhite = false;

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
     * AI Game constructor
     */
    public GameView(GameController gameController, ReversiGame reversiGame, boolean isWhitePlayer) {
        this(gameController, reversiGame);
        if(isWhitePlayer)
            infoPanel.setMessage("You are playing White");
        else
            infoPanel.setMessage("You are playing Black");
    }

    @Override
    /**
     * Activate cell buttons
     */
    public void start() {
        board.setCellActionListener(new CellActionListener(currentCell));
        board.setCellMouseListener(new CellMouseListener());
    }

    @Override
    public void updateGameBoard() {
        /**
         * Current player updates
         */
        LinkedList<CellCoord> updatedCells = reversiGame.getUpdatedCells();
        for (CellCoord cell : updatedCells) {
            board.putDisk(cell.getRow(), cell.getColumn(), isCurrentPlayerWhite);
        }
        writeStatistics();
        switchPlayer();
    }
    private void switchPlayer() {
        isCurrentPlayerWhite = reversiGame.getIsCurrentPlayerWhite();
        board.resetBackgroundHighlighting();
        showAvailableMoves(reversiGame.getAvailableMoves());
        setPlayer(isCurrentPlayerWhite);
    }

    @Override
    public void gameOver() {
        GameResult gameResult = reversiGame.getGameResult();
        if(gameResult == GameResult.WhiteWinner)
            playerPanel.setMessage("Game Over! White wins!!!");
        else if(gameResult == GameResult.BlackWinner)
            playerPanel.setMessage("Game Over! Black wins!!!");
    }

    public void showDisksToUpturn(LinkedList<CellCoord> disks) {
        board.resetBackgroundHighlighting();
        board.highlightBackgroundCells(disks);
    }

    public void missMove() {
        writeStatistics();
        switchPlayer();
        /*Previous player missing move*/
        if(!isCurrentPlayerWhite)
            playerPanel.setMessage("White missed move! Current move: Black");
        else
            playerPanel.setMessage("Black missed move! Current move: White");

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

    private void showAvailableMoves(LinkedList<CellCoord> cellCoords) {
        board.resetHighlighting();
        board.highlightCells(cellCoords);
    }

    private void writeStatistics() {
        statisticsTable.addRecord(reversiGame.getBlackDiscsCount(), reversiGame.getWhiteDiscsCount());
    }

    private void createBoard(int rows, int cols) {
        gameBoardContainer = new JPanel();
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

    private void setPlayer(boolean isCurrentPlayerWhite) {
        if(isCurrentPlayerWhite)
            playerPanel.setMessage("Current move: White");
        else
            playerPanel.setMessage("Current move: Black");
    }

    class CellActionListener implements ActionListener {
        private CellButton currentCell;
        CellActionListener(CellButton currentCell) {
            this.currentCell = currentCell;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if(obj instanceof CellButton) {
                if(currentCell != null)
                    currentCell.reset();
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
