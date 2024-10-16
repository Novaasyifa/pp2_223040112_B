
import java.awt.*;
import javax.swing.*;

public class FormBiodata extends JFrame {
    public FormBiodata() {
        setTitle("Form Biodata");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Form Biodata");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));

        formPanel.add(new JLabel("Nama:"));
        JTextField namaField = new JTextField();
        formPanel.add(namaField);

        formPanel.add(new JLabel("Nomor HP:"));
        JTextField hpField = new JTextField();
        formPanel.add(hpField);

        JPanel genderPanel = new JPanel(new GridLayout(3, 1));
        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        JRadioButton maleRadioButton = new JRadioButton("Laki-Laki", true);
        JRadioButton femaleRadioButton = new JRadioButton("Perempuan");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        genderPanel.add(genderLabel);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

        JPanel wnaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox wnaCheckBox = new JCheckBox("Warga Negara Asing");
        wnaPanel.add(wnaCheckBox);

        formPanel.add(genderPanel);
        formPanel.add(wnaPanel);

        JPanel formContainer = new JPanel();
        formContainer.add(formPanel);
        mainPanel.add(formContainer, BorderLayout.CENTER);

        JButton simpanButton = new JButton("Simpan");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(simpanButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormBiodata form = new FormBiodata();
            form.setVisible(true);
        });
    }
}
