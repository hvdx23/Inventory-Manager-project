package ictgradschool.industry.final_project.Version2;

import javax.swing.*;

public class ProjectFrame extends JFrame {
    private WelcomeScreenPanel welcomeScreenPanel;
    private ManagerPanel managerPanel;

    public ProjectFrame() {
        super("Product Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
//        welcomeScreenPanel=new WelcomeScreenPanel();
//        managerPanel=new ManagerPanel();
        setVisible(true);
    }

    //To remove for code celanup
//    public void switchToManagerPanel(){
//        getContentPane().removeAll();
//        add(managerPanel);
//        revalidate();
//        repaint();
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProjectFrame frame = new ProjectFrame();
                WelcomeScreenPanel welcomeScreenPanel = new WelcomeScreenPanel();
                frame.add(welcomeScreenPanel);
                frame.setVisible(true);
            }
        });

    }
}
