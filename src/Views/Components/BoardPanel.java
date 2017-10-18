package Views.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class BoardPanel extends JPanel {
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private CellButton[][] cellButtons = null;

    public BoardPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        cellButtons = new CellButton[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                constraints.gridy = i;
                constraints.gridx = j;
                CellButton cellButton = new CellButton(i, j);
                add(cellButton, constraints);
                cellButtons[i][j] = cellButton;
            }
        }
    }

    public CellButton getCell(int row, int col) {
        return cellButtons[row][col];
    }
    public void putWhiteCell(int row, int col) { cellButtons[row][col].setWhite(); }
    public void putBlackCell(int row, int col) { cellButtons[row][col].setBlack(); }

    public void setCellActionListener(ActionListener listener) {
        for (Component cell : getComponents()) {
            if(cell instanceof CellButton)
                ((CellButton)cell).addActionListener(listener);
        }
    }

    public void setCellMouseListener(MouseListener listener) {
        for (Component cell : getComponents()) {
            if(cell instanceof CellButton)
                ((CellButton)cell).addMouseListener(listener);
        }
    }
}
