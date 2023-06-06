package ictgradschool.industry.final_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class rootManagerFrame extends JPanel implements ActionListener {

    private JButton Open;
    private JButton Create;

    private JButton Close;
    private JFileChooser fileChooser;



    public rootManagerFrame() {
        JFrame frame = new JFrame("Product Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 800);

        JPanel contentpane = new JPanel(new FlowLayout(FlowLayout.CENTER));

        Open = new JButton("Select existing Filestore");
//        Open.setPreferredSize(new Dimension(100, 30));
        contentpane.add(Open);
        Open.addActionListener(this);

        Create = new JButton("Create new Filestore");
//        Create.setPreferredSize(new Dimension(100, 30));
        contentpane.add(Create);
        Create.addActionListener(this);

        Close = new JButton("Close");
        contentpane.add(Close);
        Close.addActionListener(this);


        frame.setContentPane(contentpane);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Create) {
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("../src/main/resources/"));
            int returnVal =fc.showSaveDialog(rootManagerFrame.this);


            if(returnVal==JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

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
                }
                catch (IOException exc) {
                    exc.printStackTrace();
                }
            }



            // Handle Create button click event
        }
        if (e.getSource()==Open){
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
                        Desktop.getDesktop().open(file);
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
        if (e.getSource()==Close){
            System.exit(0);
        }


    }
}

