package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class InventoryManagerpanel extends JPanel implements ActionListener, ListSelectionListener {


    private JButton add;
    private JButton delete;
    private JButton update;
    private JButton search;
    private JButton sort;


    private JButton back;

    private String filepath;

    private JComboBox<String> filterbox;

    JTextField identifierTextfield = null;
    JTextField nameTextfield = null;
    JTextField priceTextfield = null;
    JTextField quantityTextfield = null;
    JTextField descriptionTextfield = null;
    JTextField searchTextField = null;

    JScrollPane scrollPane = null;

    private InventoryDataProcessor inventoryDataProcessor = new InventoryDataProcessor();

    InventoryTableAdaptor tablemodel = null;

    JTable inventorytable = null;

    public InventoryManagerpanel(String filepath) {

        this.filepath = filepath;

    }


    public void initcomponents() {

        JPanel buttonpanel = new JPanel();

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add = new JButton("Add");
        add.addActionListener(this);
        buttonpanel.add(add);
        delete = new JButton("Delete");
        delete.addActionListener(this);
        buttonpanel.add(delete);
        update = new JButton("Update");
        update.addActionListener(this);
        buttonpanel.add(update);
        back=new JButton("Back");
        back.addActionListener(this);
        buttonpanel.add(back);
        add(buttonpanel, BorderLayout.CENTER);



        JPanel textfieldpanel = new JPanel();
        textfieldpanel.setLayout(new GridLayout(5, 2));
        JLabel identifierLabel = new JLabel("Identifier");
        textfieldpanel.add(identifierLabel);
        identifierTextfield = new JTextField();
        textfieldpanel.add(identifierTextfield);

        JLabel nameLabel = new JLabel("Name");
        textfieldpanel.add(nameLabel);
        nameTextfield = new JTextField();
        textfieldpanel.add(nameTextfield);


        JLabel descriptionLabel = new JLabel("Description");
        textfieldpanel.add(descriptionLabel);
        descriptionTextfield = new JTextField();
        textfieldpanel.add(descriptionTextfield);


        JLabel priceLabel = new JLabel("Price");
        textfieldpanel.add(priceLabel);
        priceTextfield = new JTextField();
        textfieldpanel.add(priceTextfield);


        JLabel quantityLabel = new JLabel("Quantity");
        textfieldpanel.add(quantityLabel);
        quantityTextfield = new JTextField();
        textfieldpanel.add(quantityTextfield);
        add(textfieldpanel, BorderLayout.SOUTH);




        JPanel filterpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel filterLabel = new JLabel("Filter");
        filterbox=new JComboBox<>(new String[]{"All items","In stock","Out of stock"});
        filterbox.setSelectedIndex(0);
        filterbox.addActionListener(this);
        filterpanel.add(filterLabel);
        filterpanel.add(filterbox);
        add(filterpanel,BorderLayout.WEST);

        JPanel searchpanel=new JPanel();
        searchTextField=new JTextField(20);
        searchTextField.addActionListener(this);
        filterpanel.add(searchTextField);
        search=new JButton("Search");
        search.addActionListener(this);
        filterpanel.add(search);
        add(searchpanel,BorderLayout.EAST);





        inventorytable = new JTable();
        List<InventoryItem> inventoryItems = inventoryDataProcessor.readInventoryFromFile(filepath);
        if (inventoryItems == null) {
            inventoryItems = new ArrayList<>();
        }
        tablemodel = new InventoryTableAdaptor(inventoryItems);
        inventorytable.setModel(tablemodel);
        inventorytable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = inventorytable.getSelectionModel();
        selectionModel.addListSelectionListener(this);

        scrollPane=new JScrollPane(inventorytable);
        add(scrollPane, BorderLayout.NORTH);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            //get values from all text fields
            String identifier = identifierTextfield.getText();
            //validation of uppercase and numbers
            boolean isValidIdentifier=identifier.matches("^[A-Z0-9]{10}+$");
            if(!isValidIdentifier){
                JOptionPane.showMessageDialog(this,"Identifier must be 10 digits -uppercase and numbers only");
                return;
            }
            InventoryItem inventoryItem = new InventoryItem();
            inventoryItem.setIdentifier(identifierTextfield.getText());
            //check for identifier
            inventoryItem.setName(nameTextfield.getText());
            inventoryItem.setDescription(descriptionTextfield.getText());
            //check for double value
            try{
                inventoryItem.setPrice(Double.parseDouble(priceTextfield.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Price must be a number");
                return;
            }

            //check for integer value
            try{
                inventoryItem.setQuantity(Integer.parseInt(quantityTextfield.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity must be a number");
                return;
            }


            boolean itemexists = false;

            for (int i = 0; i < tablemodel.getRowCount(); i++) {
                if (tablemodel.getValueAt(i, 0).equals(inventoryItem.getIdentifier())) {
                    itemexists = true;
                    break;

                }


            }
            if (!itemexists) {
                tablemodel.addInventoryData(inventoryItem);
                inventoryDataProcessor.saveInventoryToFile(filepath, tablemodel.getInventoryItems());
                //delete the item details in text field
                identifierTextfield.setText("");
                nameTextfield.setText("");
                descriptionTextfield.setText("");
                priceTextfield.setText("");
                quantityTextfield.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "Item already exists");
            }
            tablemodel.fireTableDataChanged();
        }
            if (e.getSource() == delete) {
                int selectedrow=inventorytable.getSelectedRow();
                if(selectedrow>=0){
                    InventoryItem selectedItem=tablemodel.getInventoryItem(selectedrow);
                    //setInventoryItems method not in classes
                    tablemodel.deleteInventoryData(selectedItem);
                    inventoryDataProcessor.saveInventoryToFile(filepath, tablemodel.getInventoryItems());

                }

            }
            if (e.getSource() == update) {
                int selectedrow=inventorytable.getSelectedRow();
                if(selectedrow>=0){
                    InventoryItem selectedItem=tablemodel.getInventoryItem(selectedrow);
                    selectedItem.setName(nameTextfield.getText());
                    selectedItem.setDescription(descriptionTextfield.getText());
                    selectedItem.setPrice(Double.parseDouble(priceTextfield.getText()));
                    selectedItem.setQuantity(Integer.parseInt(quantityTextfield.getText()));
                    tablemodel.fireTableDataChanged();
                    inventoryDataProcessor.saveInventoryToFile(filepath, tablemodel.getInventoryItems());
                    inventorytable.clearSelection();
                    identifierTextfield.setText("");
                    nameTextfield.setText("");
                    descriptionTextfield.setText("");
                    priceTextfield.setText("");
                    quantityTextfield.setText("");


                }

            }
            if (e.getSource()==search){
                String searchText = searchTextField.getText();
                if (!searchText.isEmpty()) {
                    List<InventoryItem> searchResults = new ArrayList<>();

                    for (InventoryItem item : tablemodel.getInventoryItems()) {
                        if (item.getIdentifier().toLowerCase().contains(searchText.toLowerCase()) ||
                                item.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                                item.getDescription().toLowerCase().contains(searchText.toLowerCase())) {
                            searchResults.add(item);
                        }
                    }


                    tablemodel.setInventoryItems(searchResults);
                    tablemodel.fireTableDataChanged();
                } else {
                    tablemodel.setInventoryItems(inventoryDataProcessor.readInventoryFromFile(filepath));
                    tablemodel.fireTableDataChanged();
                }


            }

            if (e.getSource() == sort) {


            }
            if (e.getSource()==back){
                if(isSearchResultsDisplayed()){
                    refreshManagerPanel();
                }else {
                    ProjectFrame frame = (ProjectFrame) SwingUtilities.getWindowAncestor(this);
                    ManagerPanel managerPanel = new ManagerPanel(filepath);
                    managerPanel.initcomponents();
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(managerPanel);
                    managerPanel.setPreferredSize(new Dimension(800, 600));
                    frame.pack();
                    frame.setVisible(true);
                }


            }

            if (e.getSource()==filterbox){
                String selectedFilter = (String) filterbox.getSelectedItem();
                System.out.println(selectedFilter);
                if(selectedFilter.equals("All items")){
                    tablemodel.setInventoryItems(inventoryDataProcessor.readInventoryFromFile(filepath));
                }else if (selectedFilter.equals("In stock")){
                    List<InventoryItem> inStockItems = new ArrayList<>();
                    for (InventoryItem item : inventoryDataProcessor.readInventoryFromFile(filepath)) {
                        if (item.getQuantity() > 0) {
                            inStockItems.add(item);
                        }
                    }
                    tablemodel.setInventoryItems(inStockItems);
                }else if(selectedFilter.equals("Out of stock")){
                    List<InventoryItem> outOfStockItems = new ArrayList<>();
                    for (InventoryItem item : inventoryDataProcessor.readInventoryFromFile(filepath)) {
                        if (item.getQuantity() == 0) {
                            outOfStockItems.add(item);
                        }
                    }
                    tablemodel.setInventoryItems(outOfStockItems);
                }
                tablemodel.fireTableDataChanged();
            }



        }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = inventorytable.getSelectedRow();
            if (selectedRow != -1) {
                InventoryItem selectedItem = tablemodel.getInventoryItem(selectedRow);
                identifierTextfield.setText(selectedItem.getIdentifier());
                nameTextfield.setText(selectedItem.getName());
                descriptionTextfield.setText(selectedItem.getDescription());
                priceTextfield.setText(String.valueOf(selectedItem.getPrice()));
                quantityTextfield.setText(String.valueOf(selectedItem.getQuantity()));
            }
        }
    }

    private boolean isSearchResultsDisplayed() {
        return tablemodel.getInventoryItems().size() != inventoryDataProcessor.readInventoryFromFile(filepath).size();
    }

    private void refreshManagerPanel() {
        ProjectFrame frame = (ProjectFrame) SwingUtilities.getWindowAncestor(this);
        InventoryManagerpanel managerPanel = new InventoryManagerpanel(filepath);
        managerPanel.initcomponents();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(managerPanel);
        managerPanel.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }

}

