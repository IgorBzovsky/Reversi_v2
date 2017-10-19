package Views.Components;

import Views.VisualSettings;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {

    private int row;
    private int col;

    private CellButtonState cellButtonState = CellButtonState.None;

    enum CellButtonState {
        Selected,
        Highlighted,
        BackgroundHighlighted,
        None
    }

    public CellButton(int row, int col) {
        this.row = row;
        this.col = col;
        setPreferredSize(new Dimension(VisualSettings.getCellWidth(), VisualSettings.getCellHeight()));
        setFocusPainted(false);
        reset();
    }

    public void select() {
        cellButtonState = CellButtonState.Selected;
        setBackground(VisualSettings.getCellSelectBackcolor());
        setBorder(BorderFactory.createLineBorder(VisualSettings.getCellSelectBordercolor()));
    }

    public void highlight() {
        if(cellButtonState == CellButtonState.None) {
            setBackground(VisualSettings.getCellBackcolor());
            setBorder(BorderFactory.createLineBorder(VisualSettings.getCellHighlightBordercolor()));
            cellButtonState = CellButtonState.Highlighted;
        }
    }

    public void highlightBackground() {
        setBackground(VisualSettings.getCellHighlightBackcolor());
        setBorder(BorderFactory.createLineBorder(VisualSettings.getCellBordercolor()));
        cellButtonState = CellButtonState.BackgroundHighlighted;
    }

    public void reset() {
        cellButtonState = CellButtonState.None;
        setBackground(VisualSettings.getCellBackcolor());
        setBorder(BorderFactory.createLineBorder(VisualSettings.getCellBordercolor()));
    }

    public void resetBackgroundHighlighting() {
        if(cellButtonState == CellButtonState.BackgroundHighlighted)
            reset();
    }

    public void resetHighlighting() {
        if(cellButtonState == CellButtonState.Highlighted)
            reset();
    }

    public void setWhite() {
        setIcon(new ImageIcon(this.getClass().getResource("/icons/whitepiece.png")));
    }

    public void setBlack() {
        setIcon(new ImageIcon(this.getClass().getResource("/icons/blackpiece.png")));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

