package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPanel extends JPanel implements ActionListener {

    // 3 panels inside Managerpanel
    //1 buttons for POS & inventorymanger & close
    //2 inventory manager
    //POS panel
    //panel

    private JPanel managerpanel =null;

    private JPanel POSpanel=null;

    private JPanel inventorypanel = null;

    private JPanel ManagerPanel=null;
    private JPanel WelcomeScreenPanel=null;
    private JButton POS;
    private JButton close;
    private JButton Inv;
    private JButton back;
    private JButton add;
    private JButton delete;
    private JButton edit;
    private JButton checkout;
    private JButton cancel;
    private JButton search;
    private JButton sort;
    private JButton addtocart;
    private JButton removefromcart;
    private String filepath;
    private JTable inventorytable;
    private JTable postable;


    private JFileChooser fileChooser;
    private InventoryDataProcessor inventoryDataProcessor=new InventoryDataProcessor();

    public ManagerPanel() {
    }

    public void initcomponents(){
        ManagerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        WelcomeScreenPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        POSpanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        inventorypanel=new JPanel(new FlowLayout(FlowLayout.CENTER));

        managerpanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
        POSpanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        inventorypanel=new JPanel(new FlowLayout(FlowLayout.CENTER));

        //managerpanel
        managerpanel.setBackground(Color.WHITE);
        POS=new JButton("POS");
        managerpanel.add(POS);
        POS.addActionListener(this);
        Inv=new JButton("Inventory Manager");
        Inv.addActionListener(this);
        managerpanel.add(Inv);
        back=new JButton("Back");
        back.addActionListener(this);
        managerpanel.add(back);

        //pospanel
        POSpanel.setBackground(Color.WHITE);
        addtocart=new JButton("Add to cart");
        addtocart.addActionListener(this);
        POSpanel.add(addtocart);
        removefromcart=new JButton("Remove from cart");
        removefromcart.addActionListener(this);
        POSpanel.add(removefromcart);
        checkout =new JButton("Checkout");
        checkout.addActionListener(this);
        POSpanel.add(checkout);
        postable=new JTable();
        POSpanel.add(postable);

        //inventorypanel
        inventorypanel.setBackground(Color.WHITE);
        add=new JButton("Add");
        add.addActionListener(this);
        inventorypanel.add(add);
        delete=new JButton("Delete");
        delete.addActionListener(this);
        inventorypanel.add(delete);
        edit=new JButton("Edit");
        edit.addActionListener(this);
        inventorypanel.add(edit );
        search=new JButton("Search");
        search.addActionListener(this);
        inventorypanel.add(search);
        sort=new JButton("Sort");
        sort.addActionListener(this);
        inventorypanel.add(sort);
        inventorytable=new JTable();
        inventorypanel.add(inventorytable);
        inventorytable=new JTable();
        inventorypanel.add(inventorytable);



    }

    public void showinitialScreen(){
        WelcomeScreenPanel.setVisible(true);
        ManagerPanel.setVisible(false);
        POSpanel.setVisible(false);
        inventorypanel.setVisible(false);

    }
    //similaryly show for all three options
    public void showManagerPanel(){
        JFrame frame = new JFrame("Manager Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(ManagerPanel);
        ManagerPanel.setPreferredSize(new Dimension(800,600));
//        setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        //set visibility for manager panel only
        ManagerPanel.setVisible(true);
        POSpanel.setVisible(false);
        inventorypanel.setVisible(false);
    }
    public void showInventoryManagerPanel(){
        //set visibility for inventory manager panel only
        ManagerPanel.setVisible(false);
        POSpanel.setVisible(false);
        inventorypanel.setVisible(true);
    }
    public void showPOSPanel(){
        //set visibility for POS panel only
        ManagerPanel.setVisible(false);
        POSpanel.setVisible(true);
        inventorypanel.setVisible(false);

    }

//check functionality
    public static void main(String[] args) {
        ManagerPanel managerPanel=new ManagerPanel();
        managerPanel.initcomponents();
        managerPanel.showManagerPanel();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
