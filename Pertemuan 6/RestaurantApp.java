import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RestaurantApp extends JFrame {
    private JTextField customerNameField;
    private JTextArea notesArea;
    private JComboBox<String> categoryComboBox;
    private JList<String> menuList;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private Map<String, Double> priceMap;
    private JRadioButton smallSize, mediumSize, largeSize;
    private JCheckBox extraCheese, extraSpice, extraSauce;
    private NumberFormat currencyFormat;

    public RestaurantApp() {
        setTitle("Restaurant Order App");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize price map with example prices
        initializePriceMap();

        // Setup Rupiah format
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

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

        // Portion Size Options
        formPanel.add(new JLabel("Portion Size:"));
        smallSize = new JRadioButton("Small");
        mediumSize = new JRadioButton("Medium");
        largeSize = new JRadioButton("Large");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallSize);
        sizeGroup.add(mediumSize);
        sizeGroup.add(largeSize);
        JPanel sizePanel = new JPanel();
        sizePanel.add(smallSize);
        sizePanel.add(mediumSize);
        sizePanel.add(largeSize);
        formPanel.add(sizePanel);

        // Extra Options
        formPanel.add(new JLabel("Extras:"));
        extraCheese = new JCheckBox("Extra Cheese");
        extraSpice = new JCheckBox("Extra Spice");
        extraSauce = new JCheckBox("Extra Sauce");
        JPanel extraPanel = new JPanel();
        extraPanel.add(extraCheese);
        extraPanel.add(extraSpice);
        extraPanel.add(extraSauce);
        formPanel.add(extraPanel);

        // Order Table
        tableModel = new DefaultTableModel(new String[]{"Item", "Size", "Extras", "Quantity", "Price", "Special Notes"}, 0);
        orderTable = new JTable(tableModel);

        // Button to add order to table
        JButton addButton = new JButton("Add to Order");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrderTable();
            }
        });

        // Total Label
        totalLabel = new JLabel("Total: Rp0");
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.WEST);
        getContentPane().add(new JScrollPane(orderTable), BorderLayout.CENTER);
        getContentPane().add(addButton, BorderLayout.SOUTH);
        getContentPane().add(totalLabel, BorderLayout.NORTH);
    }

    private void initializePriceMap() {
        // Initialize example prices
        priceMap = new HashMap<>();
        priceMap.put("Spring Rolls", 59900.0);
        priceMap.put("Garlic Bread", 34900.0);
        priceMap.put("Salad", 49900.0);
        priceMap.put("Steak", 149900.0);
        priceMap.put("Spaghetti", 124900.0);
        priceMap.put("Burger", 109900.0);
        priceMap.put("Ice Cream", 44900.0);
        priceMap.put("Cake", 59900.0);
        priceMap.put("Pie", 49900.0);
        priceMap.put("Coffee", 29900.0);
        priceMap.put("Juice", 34900.0);
        priceMap.put("Soda", 19900.0);
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
        String selectedItem = menuList.getSelectedValue();
        if (selectedItem != null) {
            // Determine the selected size
            String size = smallSize.isSelected() ? "Small" :
                    mediumSize.isSelected() ? "Medium" : "Large";

            // Add extra options
            StringBuilder extras = new StringBuilder();
            if (extraCheese.isSelected()) extras.append("Cheese ");
            if (extraSpice.isSelected()) extras.append("Spicy ");
            if (extraSauce.isSelected()) extras.append("Sauce ");

            // Get the base price and apply a multiplier based on size
            double basePrice = priceMap.getOrDefault(selectedItem, 0.0);
            double sizeMultiplier = size.equals("Small") ? 1 : size.equals("Medium") ? 1.5 : 2;
            double finalPrice = basePrice * sizeMultiplier;

            // Get the special notes
            String specialNotes = notesArea.getText();

            // Add row with price formatted to Rupiah
            tableModel.addRow(new Object[]{
                    selectedItem, 
                    size, 
                    extras.toString(), 
                    1, 
                    currencyFormat.format(finalPrice), 
                    specialNotes
            });
            updateTotal();
        }
    }

    private void updateTotal() {
        double total = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int quantity = (int) tableModel.getValueAt(i, 3);
            String priceText = (String) tableModel.getValueAt(i, 4);
            double price = Double.parseDouble(priceText.replaceAll("[^0-9]", ""));
            total += quantity * price;
        }
        totalLabel.setText("Total: " + currencyFormat.format(total));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestaurantApp app = new RestaurantApp();
            app.setVisible(true);
        });
    }
}
