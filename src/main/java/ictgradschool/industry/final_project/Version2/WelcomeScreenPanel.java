package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WelcomeScreenPanel extends JPanel implements ActionListener {
    private final ProjectFrame frame;
    JPanel welcomeScreenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton open;
    private JButton close;
    private JButton create;

    private JFileChooser fileChooser;
    private InventoryDataProcessor inventoryDataProcessor=new InventoryDataProcessor();




    public WelcomeScreenPanel(ProjectFrame frame) {
        welcomeScreenPanel.setBackground(Color.WHITE);
        welcomeScreenPanel.setPreferredSize(new Dimension(800, 600));
        welcomeScreenPanel.setVisible(true);

        open= new JButton("Open");
        add(open);
        open.addActionListener(this);

        create = new JButton("Create");
        add(create);
        create.addActionListener(this);


        //cameron code
        this.frame = frame;
        frame.getContentPane().removeAll();
        frame.getContentPane().add(this);

//        close = new JButton("Close");
//        add(close);
//        close.addActionListener(this);


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("../src/main/resources/"));
            int returnVal = fc.showSaveDialog(WelcomeScreenPanel.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filepath=fc.getSelectedFile().getAbsolutePath();
                inventoryDataProcessor.createFile(filepath);

                    JOptionPane.showMessageDialog(this, "File created successfully");
                    System.out.println("File created successfully");
                    //Panel creation
                openManagerPanel(filepath);
//                    ManagerPanel managerPanel = new ManagerPanel();
//                    JFrame frame=(JFrame)SwingUtilities.getWindowAncestor(this);
//                    frame.getContentPane().removeAll();
//                    frame.getContentPane().add(managerPanel);
//                    frame.pack();
//                    frame.setVisible(true);


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
                String fileName=file.getAbsolutePath();
                String nameExtension=fileName.substring(fileName.lastIndexOf(".")+1);
                if (nameExtension.equalsIgnoreCase("csv")){
                    JOptionPane.showMessageDialog(this,"You have selected to open "+fileName);
                    System.out.println("FIle valid & opening");

                    //Below 2 codes to be shifted to InventoryMangerpanel
//                    List<Item> inventoryItemList=inventoryDataProcessor.readInventoryFromFile(file.getAbsolutePath());
                    inventoryDataProcessor.readInventoryFromFile(fileName);
                    openManagerPanel(fileName);

                    //
                }else{
                    System.out.println("File invalid");
                    JOptionPane.showMessageDialog(this,"File invalid");

                }
                System.out.println(directory);
            }
        }

        if (e.getSource()== close){
            System.exit(0);
        }
    }

    //asper cameron code
    private void openManagerPanel(String filepath){



        ManagerPanel managerPanel = new ManagerPanel(filepath);
        //call initcomponent.
        managerPanel.initcomponents();
        //showinitialscreenmethod()

        this.frame.getContentPane().removeAll();
//        frame.getContentPane().removeAll();
        this.frame.getContentPane().add(managerPanel);
        this.frame.pack();
        this.frame.setVisible(true);

    }


}
