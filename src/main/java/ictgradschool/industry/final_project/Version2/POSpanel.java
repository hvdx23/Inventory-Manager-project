package ictgradschool.industry.final_project.Version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//All buttons & tables for pospanel only in this class.
//declare all buttons
public class POSpanel extends JPanel implements ActionListener {
    private JButton addtocart;
    private JButton removefromcart;
    private JButton checkout;


//    private JTable postable;






    //multiple panels for displaying tables & buttons.//3 panels

    public void initcomponents(){
        setBackground(Color.WHITE);
        // All buttons & textfields for pos panel
        addtocart=new JButton("Add to cart");
        addtocart.addActionListener(this);
        add(addtocart);
        removefromcart=new JButton("Remove from cart");
        removefromcart.addActionListener(this);
        add(removefromcart);
        checkout =new JButton("Checkout");
        checkout.addActionListener(this);
        add(checkout);
//        postable=new JTable();
//        add(postable);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addtocart){

        }
        if(e.getSource()==removefromcart){

        }
        if (e.getSource()==checkout){

        }


    }

    private void openPOSPanel(POSpanel frame,String filepath){



//        POSpanel pospanel=new POSpanel();
//
//        this.frame.getContentPane().removeAll();
////        frame.getContentPane().removeAll();
//        this.frame.getContentPane().add(managerPanel);
//        this.frame.pack();
//        this.frame.setVisible(true);

    }
}
