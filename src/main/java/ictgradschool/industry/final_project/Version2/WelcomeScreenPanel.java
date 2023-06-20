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
    private JLabel label;
    private JPanel buttonpanel;
    private JPanel labell;
    private JFileChooser fileChooser;
    private InventoryDataProcessor inventoryDataProcessor=new InventoryDataProcessor();




    public WelcomeScreenPanel(ProjectFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        welcomeScreenPanel=new JPanel(new BorderLayout());
        welcomeScreenPanel.setBackground(Color.WHITE);
        welcomeScreenPanel.setPreferredSize(new Dimension(400, 200));

        welcomeScreenPanel.setVisible(true);

        JPanel buttonpanel=new JPanel();
        open= new JButton("Open");
        buttonpanel.add(open);
        open.addActionListener(this);

        create = new JButton("Create");
        buttonpanel.add(create);
        create.addActionListener(this);

        close = new JButton("Close");
        buttonpanel.add(close);
        close.addActionListener(this);
        label=new JLabel("NeptuneX");
        label.setFont(new Font("Arial", Font.BOLD,24));

        welcomeScreenPanel.add(label,BorderLayout.CENTER);
        welcomeScreenPanel.add(buttonpanel,BorderLayout.NORTH);


        //cameron code
        add(welcomeScreenPanel);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(this);




    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("../src/main/resources/"));
            int returnVal = fc.showSaveDialog(WelcomeScreenPanel.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName=fc.getSelectedFile().getAbsolutePath()+".csv";
                inventoryDataProcessor.createFile(fileName);

                    JOptionPane.showMessageDialog(this, "File created successfully");
                    System.out.println("File created successfully");
                    //Panel creation
                inventoryDataProcessor.readInventoryFromFile(fileName);
                openManagerPanel(fileName);

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

        managerPanel.initcomponents();
        managerPanel.setPreferredSize(new Dimension(800, 600));


        this.frame.getContentPane().removeAll();

        this.frame.getContentPane().add(managerPanel);
        this.frame.pack();
        this.frame.setVisible(true);

    }




}
