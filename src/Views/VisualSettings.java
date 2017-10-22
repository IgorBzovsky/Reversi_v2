package Views;

import java.awt.*;

public class VisualSettings {

    /**
     * Window settings
     */
    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;

    /**
     * Game board cell settings
     */
    private static final int CELL_WIDTH = 60;
    private static final int CELL_HEIGHT = 60;

    private static final Color CELL_BACKCOLOR = new Color(27,166,107);
    private static final Color CELL_HIGHLIGHT_BACKCOLOR = new Color(255, 92, 51);

    private static final Color CELL_BORDERCOLOR = new Color(60, 179, 113);
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

    private static final String SPLASHSCREEN = "Splashscreen";
    private static final String GAMESCREEN = "Gamescreen";

    /**
     * Fonts
     */
    private static final Font STANDART_FONT = new Font("", Font.BOLD, 12);

    /**
     * Info panel
     */
    private static final int INFO_PANEL_HEIGHT = 30;

    /**
     * Player panel
     */
    private static final int PLAYER_PANEL_HEIGHT = 50;

    /**
     * Statistics table
     */
    private static final Color TABLE_BACKGROUND = new Color(238,238,238);
    private static final int TABLE_WIDTH = 200;

    /**
     * Window settings
     */
    private static final int GAME_VIEW_WIDTH = 900;
    private static final int GAME_VIEW_HEIGHT = 660;

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

    public static Color getCellHighlightBackcolor() {
        return CELL_HIGHLIGHT_BACKCOLOR;
    }

    public static Color getCellBordercolor() {
        return CELL_BORDERCOLOR;
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

    public static String getSplashscreen() {
        return SPLASHSCREEN;
    }

    public static String getGamescreen() {
        return GAMESCREEN;
    }

    public static int getInfoPanelHeight() { return INFO_PANEL_HEIGHT; }

    public static int getPlayerPanelHeight() { return PLAYER_PANEL_HEIGHT; }

    public static Font getStandartFont() { return STANDART_FONT; }

    public static Color getTableBackground() { return TABLE_BACKGROUND; }

    public static int getTableWidth() { return TABLE_WIDTH; }

    public static int getGameViewWidth() { return GAME_VIEW_WIDTH; }

    public static int getGameViewHeight() { return GAME_VIEW_HEIGHT; }
}
