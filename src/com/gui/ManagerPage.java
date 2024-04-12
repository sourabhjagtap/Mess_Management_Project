package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerPage extends JFrame implements ActionListener {
    private JButton menuBtn, dataBtn, feedBtn,logout;
    private JLabel heading;
    private ImageIcon cust3, DImgu, fImgu;
    private JLabel img1, dimg, fimg;
    int manager_id;
    public ManagerPage(int managerId) {
        super("Home");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null); 

        // Menu bar
        JMenuBar menubr = new JMenuBar();
        JMenu menu = new JMenu("Info");

        JMenuItem i1 = new JMenuItem("View Information");
        i1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ManagerProfile("ManagerPage",managerId);
            }
        });

        JMenuItem i2 = new JMenuItem("Edit Information");
        i2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ManagerEdit(managerId);
            }
        });

        ImageIcon three = new ImageIcon("icons/more.png");
        Image three2 = three.getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT);
        menu.add(i1);
        menu.add(i2);
        menu.setIcon(new ImageIcon(three2));
        menubr.add(menu);
        menubr.setBounds(0,0,1000,30);
        add(menubr);

        // Heading label
        heading = new JLabel("Hello Manager !");
        Font labelFont = new Font("Arial", Font.BOLD, 40);
        heading.setFont(labelFont);
        heading.setBounds(350,30,400,50);
        add(heading);

        // Menu button
        menuBtn = new JButton("Menu");
        menuBtn.setBounds(200, 350, 100, 40);
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                setVisible(false);
                new managerMenuOpt();
            }
        });
        add(menuBtn);

        // Data button
        dataBtn = new JButton("Data");
        dataBtn.setBounds(450, 350, 100, 40);
        dataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                setVisible(false);
                new SetData("");
            }
        });
        add(dataBtn);

        // See Feedback button
        feedBtn = new JButton("See Feedback");
        feedBtn.setBounds(700, 350, 120, 40);
        feedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                new SeeFeedback();
                setVisible(false);
            }
        });
        add(feedBtn);

        // Load images
        ImageIcon cust1 = new ImageIcon("icons/setMenuIcon.png");
        Image cust2 = cust1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        cust3 = new ImageIcon(cust2);
        img1 = new JLabel(cust3);
        img1.setBounds(150, 150, 200, 200);
        add(img1);

        ImageIcon dImg = new ImageIcon("icons/Data1.png");
        Image img2 = dImg.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        DImgu = new ImageIcon(img2);
        dimg = new JLabel(DImgu);
        dimg.setBounds(400, 150, 200, 200);
        add(dimg);

        ImageIcon fImg = new ImageIcon("icons/SeeFeedbackIcon.png");
        Image img3 = fImg.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        fImgu = new ImageIcon(img3);
        fimg = new JLabel(fImgu);
        fimg.setBounds(650, 150, 200, 200);
        add(fimg);
        
        logout = new JButton("Logout");
        logout.setBounds(455, 530, 80, 30);
        logout.setBackground(Color.YELLOW);
        logout.addActionListener(this);
        add(logout);
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                setVisible(false);
                new login();
            }
        });
        

        setBounds(300, 70, 1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent as){
    	
    }

    public static void main(String args[]){
        new ManagerPage(0);
    }
}
