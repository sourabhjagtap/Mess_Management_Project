package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class clientMenuOpt extends JFrame implements ActionListener{
    private JButton selectOpts, seeMenu;
    private JLabel image;

    public clientMenuOpt() {
        super("Options");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        seeMenu = new JButton("Today's Menu");
        seeMenu.setBounds(30,200,130,30);
        seeMenu.addActionListener(this);
        add(seeMenu);  
        
        selectOpts = new JButton("Select Menu");
        selectOpts.setBounds(180,200,130,30);
        selectOpts.addActionListener(this);
        add(selectOpts);

        ImageIcon i7 = new ImageIcon("icons/Menu1.png");
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        image = new JLabel(i9);
        image.setBounds(45, 0, 250, 250);
        add(image);

        setBounds(600, 250, 360, 360);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == selectOpts) {
            setVisible(false);
            new SeeFeedback();
        } else if(ae.getSource() == seeMenu) {
            setVisible(false);
            new SeeMenu("ClientHome");
        }
    }

    public static void main(String[] args) {
        new clientMenuOpt();
    }
}
