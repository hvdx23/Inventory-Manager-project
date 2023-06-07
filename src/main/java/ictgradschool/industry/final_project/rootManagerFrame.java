package ictgradschool.industry.final_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class rootManagerFrame extends JPanel implements ActionListener {

    private JButton open;
    private JButton create;

    private JButton close;
    private JFileChooser fileChooser;



    public rootManagerFrame() {
        JFrame frame = new JFrame("Product Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 800);

        JPanel contentpane = new JPanel(new FlowLayout(FlowLayout.CENTER));

        open = new JButton("Select existing Filestore");
//        Open.setPreferredSize(new Dimension(100, 30));
        contentpane.add(open);
        open.addActionListener(this);

        create = new JButton("Create new Filestore");
//        Create.setPreferredSize(new Dimension(100, 30));
        contentpane.add(create);
        create.addActionListener(this);

        close = new JButton("Close");
        contentpane.add(close);
        close.addActionListener(this);


        frame.setContentPane(contentpane);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("../src/main/resources/"));
            int returnVal =fc.showSaveDialog(rootManagerFrame.this);


            if(returnVal==JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String filePath=file.getAbsolutePath();

                if (file.exists()) {
                    JOptionPane.showMessageDialog(this, "File already exists");

                    return;
                }
                try {
                    String fileName=file.getName();
                    if (!fileName.toLowerCase().endsWith(".csv")){
                        fileName +=".csv";
                    }
                    file=new File(file.getParentFile(),fileName);
                    FileWriter writer = new FileWriter(file);
                    writer.close();
                    JOptionPane.showMessageDialog(this, "File created successfully");

                    PrimaryManager primaryManager=new PrimaryManager();
                }
                catch (IOException exc) {
                    exc.printStackTrace();
                }

            }



            // Handle Create button click event
        }
        if (e.getSource()== open){
            final JFileChooser fc = new JFileChooser();
//            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setCurrentDirectory(new File("../src/main/resources/"));
            int returnVal=fc.showDialog(rootManagerFrame.this,"Open");

            if(returnVal==JFileChooser.APPROVE_OPTION){
                File directory=fc.getCurrentDirectory().getAbsoluteFile();
                File file=fc.getSelectedFile();
                String fileName=file.getName();
                String nameExtension=fileName.substring(fileName.lastIndexOf(".")+1);
                if (nameExtension.equalsIgnoreCase("csv")){
                    JOptionPane.showMessageDialog(this,"You have selected to open "+fileName);
                    try {
                        InventoryItems inventoryItems=new InventoryItems(file.getAbsolutePath());
                        PrimaryManager primaryManager=new PrimaryManager();

                    }catch (IOException exc){
                        exc.printStackTrace();

                    }


                    System.out.println("FIle valid & opening");

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
}

