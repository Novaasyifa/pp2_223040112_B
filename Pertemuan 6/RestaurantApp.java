import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantApp extends JFrame {
    private JTextField customerNameField;
    private JTextArea notesArea;
    private JComboBox<String> categoryComboBox;
    private JList<String> menuList;
    private JTable orderTable;
    private DefaultTableModel tableModel;

    public RestaurantApp() {
        setTitle("Restaurant Order App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem menuItem = new JMenuItem("New Order");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Panel for order input
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // Customer Name Field
        formPanel.add(new JLabel("Customer Name:"));
        customerNameField = new JTextField();
        formPanel.add(customerNameField);

        // Notes Area
        formPanel.add(new JLabel("Special Notes:"));
        notesArea = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(notesArea));

        // Category ComboBox
        formPanel.add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(new String[]{"Appetizers", "Main Course", "Desserts", "Drinks"});
        categoryComboBox.addActionListener(e -> updateMenuList());
        formPanel.add(categoryComboBox);

        // Menu List
        formPanel.add(new JLabel("Menu Items:"));
        menuList = new JList<>();
        updateMenuList();
        formPanel.add(new JScrollPane(menuList));

        // Order Table
        tableModel = new DefaultTableModel(new String[]{"Item", "Quantity"}, 0);
        orderTable = new JTable(tableModel);
        
        // Button to add order to table
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrderTable();
            }
        });

        // Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.WEST);
        getContentPane().add(new JScrollPane(orderTable), BorderLayout.CENTER);
        getContentPane().add(addButton, BorderLayout.SOUTH);
    }

    private void updateMenuList() {
        // Sample menu items for each category
        String[] appetizers = {"Spring Rolls", "Garlic Bread", "Salad"};
        String[] mainCourse = {"Steak", "Spaghetti", "Burger"};
        String[] desserts = {"Ice Cream", "Cake", "Pie"};
        String[] drinks = {"Coffee", "Juice", "Soda"};

        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        if (selectedCategory == null) return;

        switch (selectedCategory) {
            case "Appetizers" -> menuList.setListData(appetizers);
            case "Main Course" -> menuList.setListData(mainCourse);
            case "Desserts" -> menuList.setListData(desserts);
            case "Drinks" -> menuList.setListData(drinks);
        }
    }

    private void addToOrderTable() {
        // Add selected menu item to order table with default quantity 1
        String selectedItem = menuList.getSelectedValue();
        if (selectedItem != null) {
            tableModel.addRow(new Object[]{selectedItem, 1});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestaurantApp app = new RestaurantApp();
            app.setVisible(true);
        });
    }
}
