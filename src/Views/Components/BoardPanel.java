package Views.Components;

import Model.CellCoord;
import Views.VisualSettings;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class BoardPanel extends JPanel {
    private CellButton[][] cellButtons = null;
    private int rows;
    private int cols;

    public BoardPanel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        createLabels(constraints);
        createButtons(constraints);
    }

    public CellButton getCell(int row, int col) {
        return cellButtons[row][col];
    }
    public void putWhiteCell(int row, int col) { cellButtons[row][col].setWhite(); }
    public void putBlackCell(int row, int col) { cellButtons[row][col].setBlack(); }
    public void highlightCell(int row, int col) { cellButtons[row][col].highlight(); }
    public void highlightCells(LinkedList<CellCoord> cellCoords){
        for (CellCoord cellCoord : cellCoords) {
            highlightCell(cellCoord.getRow(), cellCoord.getColumn());
        }
    }
    public void highlightBackgroundCell(int row, int col) { cellButtons[row][col].highlightBackground(); }
    public void highlightBackgroundCells(LinkedList<CellCoord> cellCoords){
        for (CellCoord cellCoord : cellCoords) {
            highlightBackgroundCell(cellCoord.getRow(), cellCoord.getColumn());
        }
    }
    public void resetHighlighting() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cellButtons[i][j].resetHighlighting();
            }
        }
    }
    public void resetBackgroundHighlighting() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cellButtons[i][j].resetBackgroundHighlighting();
            }
        }
    }

    public void setCellActionListener(ActionListener listener) {
        for (Component cell : getComponents()) {
            if(cell instanceof CellButton)
                ((CellButton)cell).addActionListener(listener);
        }
    }

    public void setCellMouseListener(MouseListener listener) {
        for (Component cell : getComponents()) {
            if(cell instanceof CellButton)
                cell.addMouseListener(listener);
        }
    }

    private void createButtons(GridBagConstraints constraints) {
        cellButtons = new CellButton[rows][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                constraints.gridy = i;
                constraints.gridx = j;
                CellButton cellButton = new CellButton(i-1, j-1);
                add(cellButton, constraints);
                cellButtons[i-1][j-1] = cellButton;
            }
        }
    }
    private void createLabels(GridBagConstraints constraints) {
        createVerticalLabels(constraints);
        createHorizontalLabels(constraints);
        createEdgeLabels(constraints);
    }
    private void createVerticalLabels(GridBagConstraints constraints) {
        int currentRow = rows;
        for (int row = 1; row <= rows; row++) {
            constraints.gridy = row;

            constraints.gridx = 0;
            CellLabel leftLabel = new CellLabel(Integer.toString(currentRow),
                    VisualSettings.getVerticalLabelWidth(), VisualSettings.getVerticalLabelHeight());
            leftLabel.setBorder(new MatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
            add(leftLabel, constraints);

            constraints.gridx = cols + 1;
            CellLabel rightLabel = new CellLabel(Integer.toString(currentRow--),
                    VisualSettings.getVerticalLabelWidth(), VisualSettings.getVerticalLabelHeight());
            rightLabel.setBorder(new MatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));
            add(rightLabel, constraints);
        }
    }

    private void createHorizontalLabels(GridBagConstraints constraints) {
        char currentColumn = 'A';
        for (int column = 1; column <= cols; column++) {
            constraints.gridx = column;

            constraints.gridy = 0;
            CellLabel upLabel = new CellLabel(Character.toString(currentColumn),
                    VisualSettings.getHorizontalLabelWidth(), VisualSettings.getHorizontalLabelHeight());
            upLabel.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            add(upLabel, constraints);

            constraints.gridy = rows + 1;
            CellLabel bottomLabel = new CellLabel(Character.toString(currentColumn++),
                    VisualSettings.getHorizontalLabelWidth(), VisualSettings.getHorizontalLabelHeight());
            bottomLabel.setBorder(new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
            add(bottomLabel, constraints);
        }
    }

    private void createEdgeLabels(GridBagConstraints constraints) {
        constraints.gridx = 0;
        constraints.gridy = 0;
        CellLabel leftUpLabel = new CellLabel("", VisualSettings.getEdgeLabelSide(),
                VisualSettings.getEdgeLabelSide());
        add(leftUpLabel, constraints);

        constraints.gridx = cols + 1;
        constraints.gridy = 0;
        CellLabel rightUpLabel = new CellLabel("", VisualSettings.getEdgeLabelSide(),
                VisualSettings.getEdgeLabelSide());
        add(rightUpLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = rows + 1;
        CellLabel leftBottomLabel = new CellLabel("", VisualSettings.getEdgeLabelSide(),
                VisualSettings.getEdgeLabelSide());
        add(leftBottomLabel, constraints);

        constraints.gridx = cols + 1;
        constraints.gridy = rows + 1;
        CellLabel rightBottomLabel = new CellLabel("", VisualSettings.getEdgeLabelSide(),
                VisualSettings.getEdgeLabelSide());
        add(rightBottomLabel, constraints);
    }
}
