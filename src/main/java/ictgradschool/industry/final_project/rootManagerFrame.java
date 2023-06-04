package ictgradschool.industry.final_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rootManagerFrame extends JPanel implements ActionListener {

    private JButton Open;
    private JButton Create;

    public rootManagerFrame() {
        JFrame frame = new JFrame("Product Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 800);

        JPanel contentpane = new JPanel(new FlowLayout(FlowLayout.CENTER));

        Open = new JButton("Select existing Filestore");
//        Open.setPreferredSize(new Dimension(100, 30));
        contentpane.add(Open);

        Create = new JButton("Create new Filestore");
//        Create.setPreferredSize(new Dimension(100, 30));
        contentpane.add(Create);
        Create.addActionListener(this);

        frame.setContentPane(contentpane);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Create) {
            // Handle Create button click event
        }
    }
}
