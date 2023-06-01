package ictgradschool.industry.final_project;

import javax.swing.*;
import java.awt.*;import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;



public class ProjectUI {
    public ProjectUI(String productManager, int x, int y, int width, int height) {
        JFrame frame = new JFrame("Product Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(x,y,width,height);
        frame.setVisible(true);

    }

    private void setTitle(String productManager) {
    }

    private void setVisible(boolean b) {
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
        @Override
                public void run() {
                    ProjectUI frame = new ProjectUI ("Product Manager", 100, 100, 800, 800);

                }
        });
            
        }


    // TODO: Your code here
    }

