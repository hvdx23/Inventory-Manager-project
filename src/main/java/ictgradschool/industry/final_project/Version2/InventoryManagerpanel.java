package ictgradschool.industry.final_project.Version2;

import ictgradschool.industry.final_project.Version1.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//All buttons & tables for inventorymanagerpanel only in this class.
//declare all buttons
public class InventoryManagerpanel extends JPanel implements ActionListener {

    //inventorypanel
    private JButton add;
    private JButton delete;
    private JButton edit;
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
        edit = new JButton("Edit");
        edit.addActionListener(this);
        buttonpanel.add(edit);
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
        JTable inventorytable = new JTable();
        //trying to set the table to be editable
//        inventorytable.setEditable(true);
        List<InventoryItem> inventoryItems = inventoryDataProcessor.readInventoryFromFile(filepath);
        if (inventoryItems == null) {
            inventoryItems = new ArrayList<>();
        }
        tablemodel = new InventoryTableAdaptor(inventoryItems);
        inventorytable.setModel(tablemodel);
        add(inventorytable, BorderLayout.NORTH);

        //tableadaptor table display


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            //get values from all text fields
            //add item for new item working
            InventoryItem inventoryItem = new InventoryItem();
            inventoryItem.setIdentifier(identifierTextfield.getText());
            inventoryItem.setName(nameTextfield.getText());
            inventoryItem.setDescription(descriptionTextfield.getText());
            inventoryItem.setPrice(Double.parseDouble(priceTextfield.getText()));
            inventoryItem.setQuantity(Integer.parseInt(quantityTextfield.getText()));
            //for double use double.parseDouble
            //for int use Integer.parseInt
            //create new item
            //set values
            boolean itemexists = false;
            //code not working
            for (int i = 0; i < tablemodel.getRowCount(); i++) {
                if (tablemodel.getValueAt(i, 0).equals(inventoryItem.getIdentifier())) {
                    Object currentvalue = tablemodel.getValueAt(i, 4);
                    if (currentvalue instanceof Integer) {
                        int currentquantity = (Integer) currentvalue;
                        int newquantity = currentquantity + inventoryItem.getQuantity();
                        //setting the new quantity value not working
                        tablemodel.setValueAt(newquantity, i, 4);
                        tablemodel.fireTableCellUpdated(i, 4);
                    }
                    itemexists = true;
                    break;

                }


            }
            if(!itemexists) {
                //function working to add the data
                tablemodel.addInventoryData(inventoryItem);

            }


            //if identifier exists in table, get the inventory item and update the quantity value. tablemodel.firetablechanged();
            tablemodel.fireTableDataChanged();

            if (e.getSource() == delete) {
//                int selectedrow=inventorytable.getSelectedRow();
//                if(selectedrow>=0){
//                    InventoryItem selectedItem=tablemodel.getInventoryItem(selectedrow);
//                    inventoryDataProcessor.deleteInventoryItem(filepath,selectedItem);
//                    //setInventoryItems method not in classes
//                    tablemodel.setInventoryItems(inventoryDataProcessor.readInventoryFromFile(filepath));
//                }

            }
            if (e.getSource() == edit) {

            }
            if (e.getSource() == search) {

            }
            if (e.getSource() == sort) {

            }


        }
    }
}
