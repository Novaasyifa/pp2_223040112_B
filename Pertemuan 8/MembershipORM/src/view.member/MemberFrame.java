package view.member;

import dao.JenisMemberDao;
import dao.MemberDao;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import model.JenisMember;
import model.Member;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textField;
    private MemberTableModel tabelModel;
    private JComboBox comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    
    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.memberDao = memberDao;
        this.JenisMemberDao = jenisMemberDao;

        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberList.findAll();

        JLabel labelInput = new Jlabel("Nama: ");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15,60,350,30);

        JLabel labelJenis = new JLabel("Jenis Member: ");
        labelJenis.setBounds(15,100,350,10);

        comboJenis = new JComboBox();
        comboJenis.setBounds(15,120,150,30);

        JButton button = new JButton("Simpan");
        button.setBounds(15,160,100,40);

        javax.swing.JTable table = new JTable();
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setBounds(15,220,350,200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        MemberButtonSimpanActionListener actionListener
            = new MemberButtonSimpanActionListener(this, memberDao);


        button.addActionListener(actionListener);

        this.add(button);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollPaneTable);

        this.setSize(400,500);
        this.setLayout(null);
    }

    public void populateComboJenis()
    {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember: this.jenisMemberList)
        {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    public String getNama()
    {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember()
    {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member)
    {
        TableModel.add(member);
        textFieldNama.setText("");
    }

    public void showAlert(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    btnSave.addActionListener(e -> {
        Member member = new Member();
        member.setId(Integer.parseInt(txtId.getText()));
        member.setName(txtName.getText());
        member.setEmail(txtEmail.getText());
        member.setPhone(txtPhone.getText());
    
        MemberDao dao = new MemberDao();
        if (dao.update(member)) {
            JOptionPane.showMessageDialog(this, "Update berhasil!");
        } else {
            JOptionPane.showMessageDialog(this, "Update gagal!");
        }
    });

    
    btnDelete.addActionListener(e -> {
        int memberId = Integer.parseInt(txtId.getText());
    
        MemberDao dao = new MemberDao();
        if (dao.delete(memberId)) {
            JOptionPane.showMessageDialog(this, "Hapus berhasil!");
        } else {
            JOptionPane.showMessageDialog(this, "Hapus gagal!");
        }
    });
}