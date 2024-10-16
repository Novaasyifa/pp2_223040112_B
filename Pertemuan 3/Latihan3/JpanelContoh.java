
import java.awt.*;
import javax.swing.*;

public class JpanelContoh extends JFrame {
    public JpanelContoh() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Membuat JPanel
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY); // Mengatur warna background panel
        
        // Membuat tombol
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        // Mengatur warna tombol
        button1.setBackground(Color.YELLOW);
        button2.setBackground(Color.GREEN);

        // Menambahkan tombol ke JPanel
        panel.add(button1);
        panel.add(button2);

        // Menambahkan panel ke frame
        this.add(panel);

        // Mengatur ukuran frame
        this.setSize(300, 300);
        this.setTitle("Panel Example");
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(JpanelContoh::new);
    }
}


