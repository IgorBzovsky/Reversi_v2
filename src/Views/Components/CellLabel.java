package Views.Components;

import Views.VisualSettings;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CellLabel extends JLabel {
    public CellLabel(String text, int width, int height) {
        super(text);
        setOpaque(true);
        setPreferredSize(new Dimension(width, height));
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(VisualSettings.getLabelBackcolor());
        setForeground(VisualSettings.getLabelForecolor());
    }
}
