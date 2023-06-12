package ictgradschool.industry.final_project.Version2;

import ictgradschool.industry.final_project.Version1.InventoryManagerPanel;
import ictgradschool.industry.final_project.Version1.PrimaryManager;
import ictgradschool.industry.final_project.Version1.RootManagerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WelcomeScreenPanel extends JPanel implements ActionListener {
    JPanel welcomeScreenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton open;
    private JButton close;
    private JButton create;

    private JFileChooser fileChooser;



    public WelcomeScreenPanel() {
        welcomeScreenPanel.setBackground(Color.WHITE);
        welcomeScreenPanel.setPreferredSize(new Dimension(800, 600));
        welcomeScreenPanel.setVisible(true);

        open= new JButton("Open");
        welcomeScreenPanel.add(open);
        open.addActionListener(this);

        create = new JButton("Create");
        welcomeScreenPanel.add(create);
        create.addActionListener(this);

        close = new JButton("Close");
        welcomeScreenPanel.add(close);
        close.addActionListener(this);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("../src/main/resources/"));
            int returnVal = fc.showSaveDialog(WelcomeScreenPanel.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                if (file.exists()) {
                    JOptionPane.showMessageDialog(this, "File already exists");
                    return;
                }
                try {
                    String fileName = file.getName();
                    if (!fileName.toLowerCase().endsWith(".csv")) {
                        fileName += ".csv";
                    }
                    file = new File(file.getParentFile(), fileName);
                    FileWriter writer = new FileWriter(file);
                    writer.close();
                    JOptionPane.showMessageDialog(this, "File created successfully");
                    PrimaryManager primaryManager = new PrimaryManager(file.getAbsolutePath());
                } catch (IOException exc) {
                    exc.printStackTrace();
                }

            }

        }

        if (e.getSource()== open){
            final JFileChooser fc = new JFileChooser();
//            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setCurrentDirectory(new File("../src/main/resources/"));
            int returnVal=fc.showDialog(WelcomeScreenPanel.this,"Open");

            if(returnVal==JFileChooser.APPROVE_OPTION){
                File directory=fc.getCurrentDirectory().getAbsoluteFile();
                File file=fc.getSelectedFile();
                String fileName=file.getName();
                String nameExtension=fileName.substring(fileName.lastIndexOf(".")+1);
                if (nameExtension.equalsIgnoreCase("csv")){
                    JOptionPane.showMessageDialog(this,"You have selected to open "+fileName);

                    InventoryManagerPanel inventoryManagerPanel=new InventoryManagerPanel(file.getAbsolutePath());
                    JFrame frame=new JFrame("Inventory Manager");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setBounds(100,100,800,800);
                    frame.setContentPane(inventoryManagerPanel);
                    frame.pack();
                    frame.setVisible(true);
                    System.out.println("FIle valid & opening");
                }else{
                    System.out.println("File invalid");
                    JOptionPane.showMessageDialog(this,"File invalid");

                }
                System.out.println(directory);
            }
        }
    }
}
