package Views.Components;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final Color BACKCOLOR = new Color(30, 113, 30);
    private static final Color SELECT_BACKCOLOR = new Color(0, 153, 51);

    private static final Color BORDERCOLOR = new Color(0, 153, 51);
    private static final Color SELECT_BORDERCOLOR = new Color(204, 102, 0);
    private static final Color HIGHLIGHT_BORDERCOLOR = new Color(255, 204, 0);

    private int row;
    private int col;

    private CellButtonType cellButtonType = CellButtonType.Empty;
    private CellButtonState cellButtonState = CellButtonState.None;

    enum CellButtonType {
        White,
        Black,
        Empty
    }

    enum CellButtonState {
        Selected,
        Highlighted,
        None
    }

    public CellButton(int row, int col) {
        this.row = row;
        this.col = col;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        reset();
    }

    public void select() {
        cellButtonState = CellButtonState.Selected;
        setBackground(SELECT_BACKCOLOR);
        setBorder(BorderFactory.createLineBorder(SELECT_BORDERCOLOR));
    }

    public void highlight() {
        if(cellButtonState == CellButtonState.None) {
            setBackground(BACKCOLOR);
            setBorder(BorderFactory.createLineBorder(HIGHLIGHT_BORDERCOLOR));
            cellButtonState = CellButtonState.Highlighted;
        }
    }

    public void reset() {
        cellButtonState = CellButtonState.None;
        setBackground(BACKCOLOR);
        setBorder(BorderFactory.createLineBorder(BORDERCOLOR));
    }

    public void setWhite() {
        cellButtonType = CellButtonType.White;
    }

    public void setBlack() {
        cellButtonType = CellButtonType.Black;
    }

    public void setEmpty() { cellButtonType = CellButtonType.Empty; }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

