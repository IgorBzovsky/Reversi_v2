package Views.Components;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    JLabel playerLabel;
    public PlayerPanel() {
        setPreferredSize(new Dimension(50, 50));
        playerLabel = new JLabel();
        playerLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        add(playerLabel);
    }

    public void setMessage(String player){
        playerLabel.setText(player);
    }
}
