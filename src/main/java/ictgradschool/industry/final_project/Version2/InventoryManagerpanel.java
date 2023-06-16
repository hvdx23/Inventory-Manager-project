package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//All buttons & tables for inventorymanagerpanel only in this class.
//declare all buttons
public class InventoryManagerpanel extends JPanel implements ActionListener, ListSelectionListener {

    //inventorypanel
    private JButton add;
    private JButton delete;
    private JButton update;
    private JButton search;
    private JButton sort;
    // table adaptor private JTable inventorytable;

    private String filepath;

    JTextField identifierTextfield = null;
    JTextField nameTextfield = null;
    JTextField priceTextfield = null;
    JTextField quantityTextfield = null;
    JTextField descriptionTextfield = null;

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
        search = new JButton("Search");
        search.addActionListener(this);
        buttonpanel.add(search);
        sort = new JButton("Sort");
        sort.addActionListener(this);
        buttonpanel.add(sort);
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


        JPanel inventorytablepanel = new JPanel();
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
        add(inventorytable, BorderLayout.NORTH);

        //tableadaptor table display


    }


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

            //for double use double.parseDouble
            //for int use Integer.parseInt
            //create new item
            //set values
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
//                    inventoryDataProcessor.saveInventoryToFile(filepath, tablemodel.getInventoryItems());
                    inventorytable.clearSelection();
                    identifierTextfield.setText("");
                    nameTextfield.setText("");
                    descriptionTextfield.setText("");
                    priceTextfield.setText("");
                    quantityTextfield.setText("");


                }

            }
            if (e.getSource() == search) {


            }
            if (e.getSource() == sort) {

            }


        }

//Delete if other method not working
//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        int row=e.getFirstIndex();
//        if(row!=-1){
//            InventoryItem selectedItem=tablemodel.getInventoryItem(row);
//            identifierTextfield.setText(selectedItem.getIdentifier());
//            nameTextfield.setText(selectedItem.getName());
//            descriptionTextfield.setText(selectedItem.getDescription());
//            priceTextfield.setText(String.valueOf(selectedItem.getPrice()));
//            quantityTextfield.setText(String.valueOf(selectedItem.getQuantity()));
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

}

