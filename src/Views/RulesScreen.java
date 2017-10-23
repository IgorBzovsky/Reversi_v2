package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RulesScreen extends JPanel {
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    public RulesScreen() {
        setLayout(new BorderLayout());
        JLabel heading = new JLabel("Reversi rules");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(new EmptyBorder(20,0,20,0));
        heading.setFont(new Font("", Font.BOLD, 14));
        JTextArea textArea = new JTextArea();
        try {
            textArea.setText(readTextFile("src/rules.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        textArea.setBorder(new EmptyBorder(0, 30, 0, 30));
        textArea.setPreferredSize(new Dimension(700, 600));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        add(heading, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
    }

    String readTextFile(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        List<String> strings = Files.readAllLines(path, ENCODING);
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }
}
