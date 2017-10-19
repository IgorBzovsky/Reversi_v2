package Views;

import javax.swing.*;

public class SplashScreen extends JPanel {

    public SplashScreen() {
        setSize(VisualSettings.getWidth(), VisualSettings.getHeight());
        add(new JLabel(new ImageIcon(this.getClass().getResource("/images/reversi.jpg"))));
    }
}
