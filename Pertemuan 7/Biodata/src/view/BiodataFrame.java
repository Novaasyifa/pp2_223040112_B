package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.List;
import model.Biodata;
import dao.BiodataDao;

public class BiodataFrame extends JFrame {
    private JTextField textFieldNama;
    private JTextField textFieldJenisKelamin;
    private JTextField textFieldAlamat;
    private JTable table;
    private BiodataTableModel tableModel;
    private BiodataDao biodataDao;

    public BiodataFrame(BiodataDao biodataDao) {
        this.biodataDao = biodataDao;
        List<Biodata> biodataList = biodataDao.findAll();

        // Pengaturan jendela
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 500);
        this.setLayout(null);

        // Label dan input Nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 20, 150, 25);
        textFieldNama = new JTextField();
        textFieldNama.setBounds(120, 20, 200, 25);

        // Label dan input Jenis Kelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 60, 150, 25);
        textFieldJenisKelamin = new JTextField();
        textFieldJenisKelamin.setBounds(120, 60, 200, 25);

        // Label dan input Alamat
        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(15, 100, 150, 25);
        textFieldAlamat = new JTextField();
        textFieldAlamat.setBounds(120, 100, 200, 25);

        // Tombol Simpan
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 140, 100, 30);
        buttonSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBiodata();
            }
        });

        // Tombol Hapus
        JButton buttonHapus = new JButton("Hapus");
        buttonHapus.setBounds(120, 140, 100, 30);
        buttonHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBiodata();
            }
        });

        // Tabel untuk menampilkan data Biodata
        tableModel = new BiodataTableModel(biodataList);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 180, 550, 250);

        // Menambahkan komponen ke jendela
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelJenisKelamin);
        this.add(textFieldJenisKelamin);
        this.add(labelAlamat);
        this.add(textFieldAlamat);
        this.add(buttonSimpan);
        this.add(buttonHapus);
        this.add(scrollPane);

        this.setVisible(true);
    }

    private void saveBiodata() {
        String nama = textFieldNama.getText();
        String jenisKelamin = textFieldJenisKelamin.getText();
        String alamat = textFieldAlamat.getText();

        if (nama.isEmpty() || jenisKelamin.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi.");
            return;
        }

        Biodata biodata = new Biodata();
        biodata.setId(UUID.randomUUID().toString());
        biodata.setNama(nama);
        biodata.setJenisKelamin(jenisKelamin);
        biodata.setAlamat(alamat);

        biodataDao.insert(biodata);
        tableModel.add(biodata);
        clearFields();
    }

    private void deleteBiodata() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus.");
            return;
        }

        Biodata biodata = tableModel.getBiodataAt(selectedRow);
        biodataDao.delete(biodata);
        tableModel.remove(selectedRow);
    }

    private void clearFields() {
        textFieldNama.setText("");
        textFieldJenisKelamin.setText("");
        textFieldAlamat.setText("");
    }
}
