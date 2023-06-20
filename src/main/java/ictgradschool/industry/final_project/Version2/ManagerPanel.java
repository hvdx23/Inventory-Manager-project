package ictgradschool.industry.final_project.Version2;

import ictgradschool.industry.final_project.Version1.InventoryManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerPanel extends JPanel implements ActionListener {


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


    public void showPOSPanel(){

        POSpanel pospanel=new POSpanel(filepath);
        pospanel.initcomponents();

        JFrame frame=(JFrame)SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pospanel);
        pospanel.setPreferredSize(new Dimension(800,600));
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
            welcomeScreenPanel.setPreferredSize(new Dimension(800,600));
            frame.pack();
            frame.setVisible(true);
        }

    }
}
