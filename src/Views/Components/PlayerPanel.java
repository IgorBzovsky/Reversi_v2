package Views.Components;

import Views.VisualSettings;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private JLabel playerLabel;
    public PlayerPanel() {
        setPreferredSize(new Dimension(0, VisualSettings.getPlayerPanelHeight()));
        playerLabel = new JLabel();
        playerLabel.setFont(VisualSettings.getStandartFont());
        add(playerLabel);
    }

    public void setMessage(String player){
        playerLabel.setText(player);
    }
}
