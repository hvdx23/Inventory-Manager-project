package ictgradschool.industry.final_project.Version2;

import ictgradschool.industry.final_project.Version1.InventoryManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPanel extends JPanel implements ActionListener {

   //only 3 buttons for POS invrntory manager and back
    //shift all other to other  panels
    //only need buttons and action listenener

    private JButton POS;

    private JButton Inv;
    private JButton back;

    private String filepath;
    private JFileChooser fileChooser;
    private InventoryDataProcessor inventoryDataProcessor=new InventoryDataProcessor();
    private ProjectFrame frame;

    public ManagerPanel(String filepath) {

        this.filepath=filepath;


    }

    public void initcomponents(){


        //set flowlayout afterwards.
//        setLayout(FlowLayout.CENTER);
        //set layout if buttons are big or having trouble.

        setBackground(Color.WHITE);
        POS=new JButton("POS");
        add(POS);
        POS.addActionListener(this);
        Inv=new JButton("Inventory Manager");
        Inv.addActionListener(this);
        add(Inv);
        back=new JButton("Back");
        back.addActionListener(this);
        add(back);

    }

//    public void showinitialScreen(){
//        WelcomeScreenPanel.setVisible(true);
//        ManagerPanel.setVisible(false);
//        POSpanel.setVisible(false);
//        inventorypanel.setVisible(false);
//
//    }
    //similaryly show for all three options
//    public void showManagerPanel(){
//        JFrame frame = new JFrame("Manager Panel");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(ManagerPanel);
//        ManagerPanel.setPreferredSize(new Dimension(800,600));
////        setLocationRelativeTo(null);
//        frame.pack();
//        frame.setVisible(true);
//        //set visibility for manager panel only
//        ManagerPanel.setVisible(true);
//        POSpanel.setVisible(false);
//        inventorypanel.setVisible(false);
//    }
//    public void showInventoryManagerPanel(){
//        //set visibility for inventory manager panel only
//        ManagerPanel.setVisible(false);
//        POSpanel.setVisible(false);
//        inventorypanel.setVisible(true);
//    }
    public void showPOSPanel(){
        //create new instance
//        POSpanel pospanel=new POSpanel();
//        //calls initcomponents inside pospanel
//        pospanel.initcomponents();
//        //set visibility for POS panel only
////        ManagerPanel.setVisible(false);
////        POSpanel.setVisible(true);
////        inventorypanel.setVisible(false);
//        //How to get parent frame from JPanel in java.
//        JFrame frame=(JFrame)SwingUtilities.getWindowAncestor(this);
//        //no change for 148-151
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add(pospanel);
//        frame.pack();
//        frame.setVisible(true);

//        POSpanel pospanel=new POSpanel();
////        this.frame = frame;
//        frame.getContentPane().removeAll();
//        frame.getContentPane().add(pospanel);

        POSpanel pospanel=new POSpanel(filepath);
        pospanel.initcomponents();
        pospanel.setPreferredSize(new Dimension(800,600));
        JFrame frame=(JFrame)SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pospanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void showinventorymanagerpanel(){
        InventoryManagerpanel inventorymanagerpanel=new InventoryManagerpanel(filepath);
        inventorymanagerpanel.initcomponents();
        inventorymanagerpanel.setPreferredSize(new Dimension(800,600));
        JFrame frame=(JFrame)SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(inventorymanagerpanel);
        frame.pack();
        frame.setVisible(true);

    }

    //create inventorymanagerpanel just like showpospanel




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==POS){
            showPOSPanel();
        }
        if(e.getSource()==Inv){
            showinventorymanagerpanel();
        }
        if (e.getSource()==back){
            ProjectFrame frame = (ProjectFrame) SwingUtilities.getWindowAncestor(this);
            WelcomeScreenPanel welcomeScreenPanel = new WelcomeScreenPanel(frame);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(welcomeScreenPanel);
            frame.pack();
            frame.setVisible(true);
        }

    }
}
