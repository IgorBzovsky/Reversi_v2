package Views.Components;

import Views.VisualSettings;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel playerLabel;
    public InfoPanel() {
        setPreferredSize(new Dimension(0, VisualSettings.getInfoPanelHeight()));
        playerLabel = new JLabel();
        playerLabel.setFont(VisualSettings.getStandartFont());
        add(playerLabel);
    }

    public void setMessage(String player){
        playerLabel.setText(player);
    }
}
