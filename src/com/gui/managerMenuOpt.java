package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class managerMenuOpt extends JFrame implements ActionListener {

    private JButton setMenu, seeMenu;
    private ImageIcon menuImage;

    public managerMenuOpt() {
        super("Options");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Set Menu button
        setMenu = new JButton("Set Menu");
        setMenu.setBounds(30,200,100,30);
        setMenu.addActionListener(this);
        add(setMenu);

        // See Menu button
        seeMenu = new JButton("See Menu");
        seeMenu.setBounds(150,200,100,30);
        seeMenu.addActionListener(this);
        add(seeMenu);  

        // Load image
        menuImage = new ImageIcon("icons/Menu1.png");

        JLabel imageLabel = new JLabel(menuImage);
        imageLabel.setBounds(15, 0, 250, 250);
        add(imageLabel);

        setBounds(600, 250, 300, 300);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == setMenu) {
            setVisible(false);
            new setMenu();
        } else if(ae.getSource() == seeMenu) {
            setVisible(false);
            new SeeMenu("ManagerPage");
        }
    }

    public static void main(String[] args) {
        new managerMenuOpt();
    }
}
