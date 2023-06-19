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

//All buttons & tables for inventorymanagerpanel only in this class.
//declare all buttons
public class InventoryManagerpanel extends JPanel implements ActionListener, ListSelectionListener {

    //welcome screen panel instance for back button TODO
    private JButton add;
    private JButton delete;
    private JButton update;
    private JButton search;
    private JButton sort;


    private JButton back;
    // table adaptor private JTable inventorytable;

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
//        search = new JButton("Search");
//        search.addActionListener(this);
//        buttonpanel.add(search);
//        sort = new JButton("Sort");
//        sort.addActionListener(this);
//        buttonpanel.add(sort);
        back=new JButton("Back");
        back.addActionListener(this);
        buttonpanel.add(back);

        add(buttonpanel, BorderLayout.CENTER);

        //Textfield panel

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


        //Filter panel

//        JPanel filterpanel = new JPanel();
//        JLabel filterLabel = new JLabel("Filter");
//        filterbox=new JComboBox<>(new String[]{"All items","In stock","Out of stock"});
//        filterbox.setSelectedIndex(0);
//        filterpanel.add(filterLabel);
//        filterpanel.add(filterbox);
//        add(filterpanel,BorderLayout.WEST);
//
//        //Search Panel
//        JPanel searchpanel=new JPanel();
//        searchTextField=new JTextField(20);
//        searchTextField.addActionListener(this);
//        searchpanel.add(searchTextField);
//        searchpanel.add(new JLabel("Search"));
//        add(searchpanel,BorderLayout.EAST);




        //Trying out new layout
        JPanel filterpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel filterLabel = new JLabel("Filter");
        filterbox=new JComboBox<>(new String[]{"All items","In stock","Out of stock"});
        filterbox.setSelectedIndex(0);
        filterpanel.add(filterLabel);
        filterpanel.add(filterbox);
        add(filterpanel,BorderLayout.WEST);

        //Search Panel
        JPanel searchpanel=new JPanel();
        searchTextField=new JTextField(20);
        searchTextField.addActionListener(this);
        filterpanel.add(searchTextField);
//        filterpanel.add(new JLabel("Search"));
        search=new JButton("Search");
        search.addActionListener(this);
        filterpanel.add(search);
        add(searchpanel,BorderLayout.EAST);


//        JPanel inventorytablepanel = new JPanel();
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
        //scrollpane not visible
//        scrollPane = new JScrollPane(inventorytable);
//        inventorytablepanel.add(scrollPane,BorderLayout.CENTER);
        add(inventorytable, BorderLayout.NORTH);

        //tableadaptor table display


    }

//    private void sortInventory(){
//        List<InventoryItem> inventoryItems = tablemodel.getInventoryItems();
//        inventoryItems.sort(new Comparator<InventoryItem>() {
//            @Override
//            public int compare(InventoryItem o1, InventoryItem o2) {
//                return 0;
//            }
//        })
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            //get values from all text fields
            String identifier = identifierTextfield.getText();
            //validation of uppercase and numbers
            boolean isValidIdentifier=identifier.matches("^[A-Z0-9]+$");
            if(!isValidIdentifier){
                JOptionPane.showMessageDialog(this,"Identifier must be uppercase and numbers only");
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
                //delete teh item details in text field
                identifierTextfield.setText("");
                nameTextfield.setText("");
                descriptionTextfield.setText("");
                priceTextfield.setText("");
                quantityTextfield.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "Item already exists");
            }


            //if identifier exists in table, get the inventory item and update the quantity value tablemodel.firetablechanged();
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
                //take selected row from Jtable
                //get inventory item from table model
                //update values from text fields
                //firetable changed
                //get all inventory from data processor
                //save to file
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

                    // Update the table model with the search results
                    tablemodel.setInventoryItems(searchResults);
                    tablemodel.fireTableDataChanged();
                } else {
                    // If the search text is empty, reset the table model to the original inventory items
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


        }

//    @Override
//    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//
//            search.doClick();
//        }
//    }




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

