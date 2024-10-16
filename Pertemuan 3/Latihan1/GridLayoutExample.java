
import java.awt.*;
import javax.swing.*;

public class GridLayoutExample extends JFrame {

    public GridLayoutExample() {
        super("Grid Layout Example");
        setLayout(new GridLayout(3, 3));
        
        for (int i = 1; i <= 9; i++) {
            add(new JButton(String.valueOf(i)));
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(GridLayoutExample::new);
    }
}

