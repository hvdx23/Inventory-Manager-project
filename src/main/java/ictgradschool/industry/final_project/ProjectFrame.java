package ictgradschool.industry.final_project;

import javax.swing.*;


public class ProjectFrame extends JFrame {
    private JFrame frame;
    public ProjectFrame(String ProductManager, int x, int y, int width, int height) {
//        JFrame frame = new JFrame("Product Manager");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        rootManagerFrame frameContent = new rootManagerFrame();
//        frame.setBounds(x,y,width,height);
//        frame.setVisible(true);

        setTitle(ProductManager);
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RootManagerFrame contentpane = new RootManagerFrame();
        setContentPane(contentpane);
        pack();
//        setLocationRelativeTo(null);
        setVisible(true);

    }



    private void setContentPane(RootManagerFrame contentpane) {


    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        @Override
                public void run() {
                    ProjectFrame frame = new ProjectFrame("Product Manager", 100, 100, 800, 800);
//                    JFileChooser fileChooser = new JFileChooser();
//            InventoryItems inventoryItems = new InventoryItems();
//            POSInventory POSInventory = new POSInventory();
                }
        });

        }


    // TODO: Your code here
    }

