import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Library extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtTitle, txtAuthor, txtYear, txtPublisher;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnShow;
    private Connection conn;

    public Library() {
        initComponents();
        connectDB();
        loadData();
    }

    private void initComponents() {
        setTitle("Sistem Manajemen Perpustakaan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Panel untuk input
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("ID Buku:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("Judul:"));
        txtTitle = new JTextField();
        inputPanel.add(txtTitle);

        inputPanel.add(new JLabel("Penulis:"));
        txtAuthor = new JTextField();
        inputPanel.add(txtAuthor);

        inputPanel.add(new JLabel("Tahun Terbit:"));
        txtYear = new JTextField();
        inputPanel.add(txtYear);

        inputPanel.add(new JLabel("Penerbit:"));
        txtPublisher = new JTextField();
        inputPanel.add(txtPublisher);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());

        btnAdd = new JButton("Tambah");
        btnUpdate = new JButton("Ubah");
        btnDelete = new JButton("Hapus");
        btnClear = new JButton("Bersihkan");
        btnShow = new JButton("Tampilkan");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnShow);

        // Tabel
        String[] columns = {"ID", "Judul", "Penulis", "Tahun", "Penerbit"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout utama
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Event handlers
        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnClear.addActionListener(e -> clearFields());
        btnShow.addActionListener(e -> loadData());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(table.getValueAt(row, 0).toString());
                    txtTitle.setText(table.getValueAt(row, 1).toString());
                    txtAuthor.setText(table.getValueAt(row, 2).toString());
                    txtYear.setText(table.getValueAt(row, 3).toString());
                    txtPublisher.setText(table.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db",
                "root",
                ""
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Koneksi database gagal: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            tableModel.setRowCount(0);
            String query = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Object[] row = {
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("year"),
                    rs.getString("publisher")
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void addBook() {
        try {
            String query = "INSERT INTO books (id, title, author, year, publisher) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, txtId.getText());
            pstmt.setString(2, txtTitle.getText());
            pstmt.setString(3, txtAuthor.getText());
            pstmt.setString(4, txtYear.getText());
            pstmt.setString(5, txtPublisher.getText());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Buku berhasil ditambahkan!");
            clearFields();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menambah buku: " + e.getMessage());
        }
    }

    private void updateBook() {
        try {
            String query = "UPDATE books SET title=?, author=?, year=?, publisher=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, txtTitle.getText());
            pstmt.setString(2, txtAuthor.getText());
            pstmt.setString(3, txtYear.getText());
            pstmt.setString(4, txtPublisher.getText());
            pstmt.setString(5, txtId.getText());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Buku berhasil diperbarui!");
            clearFields();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memperbarui buku: " + e.getMessage());
        }
    }

    private void deleteBook() {
        try {
            String query = "DELETE FROM books WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, txtId.getText());

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Buku berhasil dihapus!");
            clearFields();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menghapus buku: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtYear.setText("");
        txtPublisher.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Library().setVisible(true);
        });
    }
}
