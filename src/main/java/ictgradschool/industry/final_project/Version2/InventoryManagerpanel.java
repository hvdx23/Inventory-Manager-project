package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setBackground(Color.WHITE);
        add = new JButton("Add");
        add.addActionListener(this);
        add(add);
        delete = new JButton("Delete");
        delete.addActionListener(this);
        add(delete);
        edit = new JButton("Edit");
        edit.addActionListener(this);
        add(edit);
        search = new JButton("Search");
        search.addActionListener(this);
        add(search);
        sort = new JButton("Sort");
        sort.addActionListener(this);
        add(sort);
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
