package Views;

import Controllers.GameController;
import Model.*;
import Views.Components.BoardPanel;
import Views.Components.CellButton;

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

    public GameView(GameController gameController, ReversiGame reversiGame) {
        this.gameController = gameController;
        this.reversiGame = reversiGame;
        setLayout(new BorderLayout());
        setSize(VisualSettings.getWidth(), VisualSettings.getHeight());
        int rows = reversiGame.getBoardLength();
        int cols = reversiGame.getBoardHeight();
        createBoard(rows, cols);
        setVisible(true);
        reversiGame.addObserver(this);
    }


    @Override
    public void updateGameBoard() {

    }

    public void showDisksToUpturn(LinkedList<CellCoord> disks) {
        board.highlightBackgroundCells(disks);
    }

    private void createBoard(int rows, int cols) {
        board = new BoardPanel(rows, cols);
        board.setCellActionListener(new CellActionListener(currentCell));
        board.setCellMouseListener(new CellMouseListener());
        add(board, BorderLayout.CENTER);
        for (CellCoord cellCoord : reversiGame.getWhiteDisksStartPosition()) {
            board.putWhiteCell(cellCoord.getRow(), cellCoord.getColumn());
        }
        for (CellCoord cellCoord : reversiGame.getBlackDisksStartPosition()) {
            board.putBlackCell(cellCoord.getRow(), cellCoord.getColumn());
        }
        board.highlightCells(reversiGame.getAvailableMoves());
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
                currentCell = cell;
                cell.select();
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
