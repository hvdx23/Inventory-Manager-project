package ictgradschool.industry.final_project;

import javax.swing.*;


public class ProjectFrame extends JFrame {
    public ProjectFrame(String ProductManager, int x, int y, int width, int height) {
//        JFrame frame = new JFrame("Product Manager");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        rootManagerFrame frameContent = new rootManagerFrame();
//        frame.setBounds(x,y,width,height);
//        frame.setVisible(true);

        setTitle(ProductManager);
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootManagerFrame contentpane = new rootManagerFrame();
        setContentPane(contentpane);
        pack();
//        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void setContentPane(rootManagerFrame contentpane) {


    }

//    private void setTitle(String productManager) {
//    }

//    private void setVisible(boolean b) {
//    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        @Override
                public void run() {
                    ProjectFrame frame = new ProjectFrame("Product Manager", 100, 100, 800, 800);

                }
        });

        }


    // TODO: Your code here
    }

