package Views;

import Controllers.GameController;
import Model.Cell;
import Model.CellType;
import Model.IGameObserver;
import Model.ReversiGame;
import Views.Components.BoardPanel;
import Views.Components.CellButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameView extends JPanel implements IGameObserver {
    private GameController gameController;
    private ReversiGame reversiGame;

    private BoardPanel board = null;
    private CellButton currentCell = null;

    public GameView(GameController gameController, ReversiGame reversiGame) {
        this.gameController = gameController;
        this.reversiGame = reversiGame;
        setLayout(new BorderLayout());
        setSize(800, 600);
        board = new BoardPanel();
        board.setCellActionListener(new CellActionListener(currentCell));
        //board.setCellMouseListener(new CellMouseListener());
        add(board, BorderLayout.CENTER);
        setVisible(true);
        reversiGame.addObserver(this);
    }

    @Override
    public void start(Cell[] startPosition) {
        for (Cell cell : startPosition) {
            if(cell.getCellType() == CellType.White)
                board.putWhiteCell(cell.getRow(), cell.getColumn());
            else if(cell.getCellType() == CellType.Black)
                board.putBlackCell(cell.getRow(), cell.getColumn());
        }
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
                cell.highlight();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Object obj = e.getSource();
            if (obj instanceof CellButton) {
                CellButton cell = (CellButton) obj;
                cell.reset();
            }
        }
    }
}
