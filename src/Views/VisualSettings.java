package Views;

import java.awt.*;

public class VisualSettings {

    /**
     * Window settings
     */
    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;

    /**
     * Game board cell settings
     */
    private static final int CELL_WIDTH = 60;
    private static final int CELL_HEIGHT = 60;

    private static final Color CELL_BACKCOLOR = new Color(27,166,107);
    private static final Color CELL_SELECT_BACKCOLOR = new Color(0, 153, 51);
    private static final Color CELL_HIGHLIGHT_BACKCOLOR = new Color(255, 92, 51);

    private static final Color CELL_BORDERCOLOR = new Color(60, 179, 113);
    private static final Color CELL_SELECT_BORDERCOLOR = new Color(204, 102, 0);
    private static final Color CELL_HIGHLIGHT_BORDERCOLOR = new Color(255, 204, 0);

    /**
     * Game board label settings
     */
    private static final int VERTICAL_LABEL_WIDTH = 30;
    private static final int VERTICAL_LABEL_HEIGHT = 60;
    private static final int HORIZONTAL_LABEL_WIDTH = 60;
    private static final int HORIZONTAL_LABEL_HEIGHT = 30;
    private static final int EDGE_LABEL_SIDE = 30;

    private static final Color LABEL_BACKCOLOR = new Color(57, 96, 96);
    private static final Color LABEL_FORECOLOR = Color.WHITE;
    private static final Color LABEL_BORDERCOLOR = Color.LIGHT_GRAY;

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getCellWidth() {
        return CELL_WIDTH;
    }

    public static int getCellHeight() {
        return CELL_HEIGHT;
    }

    public static Color getCellBackcolor() {
        return CELL_BACKCOLOR;
    }

    public static Color getCellSelectBackcolor() {
        return CELL_SELECT_BACKCOLOR;
    }

    public static Color getCellHighlightBackcolor() {
        return CELL_HIGHLIGHT_BACKCOLOR;
    }

    public static Color getCellBordercolor() {
        return CELL_BORDERCOLOR;
    }

    public static Color getCellSelectBordercolor() {
        return CELL_SELECT_BORDERCOLOR;
    }

    public static Color getCellHighlightBordercolor() {
        return CELL_HIGHLIGHT_BORDERCOLOR;
    }

    public static int getVerticalLabelWidth() {
        return VERTICAL_LABEL_WIDTH;
    }

    public static int getVerticalLabelHeight() {
        return VERTICAL_LABEL_HEIGHT;
    }

    public static int getHorizontalLabelWidth() {
        return HORIZONTAL_LABEL_WIDTH;
    }

    public static int getHorizontalLabelHeight() {
        return HORIZONTAL_LABEL_HEIGHT;
    }

    public static int getEdgeLabelSide() {
        return EDGE_LABEL_SIDE;
    }

    public static Color getLabelBackcolor() {
        return LABEL_BACKCOLOR;
    }

    public static Color getLabelBordercolor() {
        return LABEL_BORDERCOLOR;
    }

    public static Color getLabelForecolor() {
        return LABEL_FORECOLOR;
    }
}
