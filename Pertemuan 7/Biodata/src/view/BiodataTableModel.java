package view;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Biodata;

class BiodataTableModel extends AbstractTableModel {
    private String[] columnNames = { "ID", "Nama", "Jenis Kelamin", "Alamat" };
    private List<Biodata> data;

    public BiodataTableModel(List<Biodata> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Biodata biodata = data.get(row);
        switch (col) {
            case 0:
                return biodata.getId();
            case 1:
                return biodata.getNama();
            case 2:
                return biodata.getJenisKelamin();
            case 3:
                return biodata.getAlamat();
            default:
                return null;
        }
    }

    public Biodata getBiodataAt(int row) {
        return data.get(row);
    }

    public void add(Biodata biodata) {
        data.add(biodata);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }
}
