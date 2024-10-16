
import java.awt.*;
import javax.swing.*;

public class HelloGridBagLayout extends JFrame {
    public HelloGridBagLayout() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel headerLabel = new JLabel("GridBagLayout", JLabel.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300, 300);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JButton("Button One"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JButton("Button Two"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 20;
        panel.add(new JButton("Button Three"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JButton("Button Four"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JButton("Button Five"), gbc);

        controlPanel.add(panel);

        this.setLayout(new GridLayout(2, 1));
        this.add(headerLabel);
        this.add(controlPanel);

        this.setSize(400, 400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new HelloGridBagLayout().setVisible(true);
        });
    }
}

