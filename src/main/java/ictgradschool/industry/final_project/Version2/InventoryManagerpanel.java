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





    public void initcomponents() {
        JPanel  buttonpanel=new JPanel();

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
        add(buttonpanel,BorderLayout.CENTER);

        //Textfield panel

        JPanel textfieldpanel=new JPanel();
        textfieldpanel.setLayout(new GridLayout(5,2));
        JLabel identifierLabel=new JLabel("Identifier");
        textfieldpanel.add(identifierLabel);
        JTextField identifierTextfield=new JTextField();
        textfieldpanel.add(identifierTextfield);
        JLabel nameLabel=new JLabel("Name");
        textfieldpanel.add(nameLabel);
        JTextField nameTextfield=new JTextField();
        textfieldpanel.add(nameTextfield);
        JLabel descriptionLabel=new JLabel("Description");
        textfieldpanel.add(descriptionLabel);
        JTextField descriptionTextfield=new JTextField();
        textfieldpanel.add(descriptionTextfield);
        JLabel priceLabel=new JLabel("Price");
        textfieldpanel.add(priceLabel);
        JTextField priceTextfield=new JTextField();
        textfieldpanel.add(priceTextfield);
        JLabel quantityLabel=new JLabel("Quantity");
        textfieldpanel.add(quantityLabel);
        JTextField quantityTextfield=new JTextField();
        textfieldpanel.add(quantityTextfield);
        add(textfieldpanel,BorderLayout.EAST);



        //tableadaptor table display
//        List<Item> inventoryItems=new ArrayList<>(inventoryItems);
//        InventoryTableAdaptor tablemodel = new InventoryTableAdaptor();
//        inventorytable = new JTable();
//        add(inventorytable);


    }
    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == add) {

        }
        if(e.getSource()==delete){

        }
        if(e.getSource()==edit){

        }
        if(e.getSource()==search){

        }
        if(e.getSource()==sort){

        }


    }
}
