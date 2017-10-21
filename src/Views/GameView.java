package Views;

import Controllers.GameController;
import Model.*;
import Views.Components.BoardPanel;
import Views.Components.CellButton;
import Views.Components.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class GameView extends JPanel implements IGameObserver {

    private GameController gameController;
    private ReversiGame reversiGame;

    private BoardPanel board = null;
    private CellButton currentCell = null;
    private PlayerPanel playerPanel = null;
    private boolean isCurrentPlayerWhite = false;

    public GameView(GameController gameController, ReversiGame reversiGame) {
        this.gameController = gameController;
        this.reversiGame = reversiGame;
        setLayout(new BorderLayout());
        setSize(VisualSettings.getWidth(), 660);
        int rows = reversiGame.getBoardLength();
        int cols = reversiGame.getBoardHeight();
        isCurrentPlayerWhite = reversiGame.getIsCurrentPlayerWhite();
        createBoard(rows, cols);
        createPlayerPanel();
        setPlayer(isCurrentPlayerWhite);
        setVisible(true);
        reversiGame.addObserver(this);
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
        writeStatistics(reversiGame.getCurrentPlayerDiscsCount());
        switchPlayer();
        setPlayer(isCurrentPlayerWhite);
    }

    @Override
    public void gameOver() {
        playerPanel.setMessage("Game Over");
    }

    public void showDisksToUpturn(LinkedList<CellCoord> disks) {
        board.resetBackgroundHighlighting();
        board.highlightBackgroundCells(disks);
    }

    private void showAvailableMoves(LinkedList<CellCoord> cellCoords) {
        board.resetHighlighting();
        board.highlightCells(cellCoords);
    }

    public void missMove() {
        switchPlayer();
        /*Previous player missing move*/
        if(!isCurrentPlayerWhite)
            playerPanel.setMessage("White missed move! Current move: Black");
        else
            playerPanel.setMessage("Black missed move! Current move: White");
    }

    private void switchPlayer() {
        isCurrentPlayerWhite = reversiGame.getIsCurrentPlayerWhite();
        board.resetBackgroundHighlighting();
        showAvailableMoves(reversiGame.getAvailableMoves());
    }

    private void writeStatistics(int disksCount) {

    }

    private void createBoard(int rows, int cols) {
        board = new BoardPanel(rows, cols);
        board.setCellActionListener(new CellActionListener(currentCell));
        board.setCellMouseListener(new CellMouseListener());
        add(board, BorderLayout.CENTER);
        for (CellCoord cellCoord : reversiGame.getWhiteDisksStartPosition()) {
            board.putWhiteDisk(cellCoord.getRow(), cellCoord.getColumn());
        }
        for (CellCoord cellCoord : reversiGame.getBlackDisksStartPosition()) {
            board.putBlackDisk(cellCoord.getRow(), cellCoord.getColumn());
        }
        board.highlightCells(reversiGame.getAvailableMoves());
    }

    private void createPlayerPanel() {
        playerPanel = new PlayerPanel();
        add(playerPanel, BorderLayout.SOUTH);
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
                gameController.move(cell.getRow(), cell.getCol());
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
