package com.gui;

import javax.swing.*;

import com.dao.Mess;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientHome extends JFrame implements ActionListener {
    private JButton menuBtn, dataBtn, feedBtn,logout;
    private JLabel heading, img1, dimg, fimg;
    private JMenuBar menubr;
    private JMenuItem i1, i2, i3;
    int u_Id;
    String userName="";
    int m_id;
    String man_userName="";
    
    
    public ClientHome(int userId,int manager_id){
        
        super("Home");
        System.out.println(userId);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null); 
        this.u_Id=userId;
        this.m_id=manager_id;
        
        //
        Mess mess=new Mess();
        ResultSet urs=mess.user_Profile(u_Id);
        try {
        	if(urs.next()) {
        		userName=urs.getString(6);
        		System.out.print("username" + userName);
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //
        
        ResultSet mrs=mess.manager_Profile(m_id);
        try {
        	if(mrs.next()) {
        		man_userName=mrs.getString(2);
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        menubr = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        i1 = new JMenuItem("View Information");
        i1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
               new UserProfile(u_Id);
            }
        });

        i2 = new JMenuItem("Edit Information");
        i2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ClientEdit(u_Id);
            }
        });
        
        i3 = new JMenuItem("Manager Info");
        i3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        i3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerProfile managerProf = new ManagerProfile("ClientHome",manager_id);
                setVisible(false);
                managerProf.setVisible(true);
            }
        });

        ImageIcon three = new ImageIcon("icons/more.png");
        Image three2 = three.getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT);

        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.setIcon(new ImageIcon(three2));
        menubr.add(menu);
        menubr.setBounds(0,0,1000,30);
        add(menubr);

        heading = new JLabel("Hello Client !");
        Font labelFont = new Font("Arial", Font.BOLD, 40); 
        heading.setFont(labelFont);
        heading.setBounds(350,40,400,50);
        add(heading);

        ImageIcon cust1 = new ImageIcon("icons/Menu1.png");
        Image cust2 = cust1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon cust3 = new ImageIcon(cust2);
        img1 = new JLabel(cust3);
        img1.setBounds(150, 150, 200, 200);
        add(img1);
        
        menuBtn = new JButton("Menu");
        menuBtn.setBounds(200, 350, 100, 40);
        add(menuBtn);
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new SeeMenu("ClientHome");
            }
        });

        ImageIcon dImg = new ImageIcon("icons/Data1.png");
        Image img2 = dImg.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon DImgu = new ImageIcon(img2);
        dimg = new JLabel(DImgu);
        dimg.setBounds(400, 150, 200, 200);
        add(dimg);
        
        dataBtn = new JButton("Data");
        dataBtn.setBounds(450, 350, 100, 40);
        add(dataBtn);

        dataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aa){
                setVisible(false);
                new ClientDatapage(userName);
            }
        });

        ImageIcon fImg = new ImageIcon("icons/feed.png");
        Image img3 = fImg.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon fImgu = new ImageIcon(img3);
        fimg = new JLabel(fImgu);
        fimg.setBounds(650, 150, 200, 200);
        add(fimg);

        feedBtn = new JButton("Feedback");
        feedBtn.setBounds(700, 350, 100, 40);
        feedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                FeedbackPage feedbackPage = new FeedbackPage(u_Id);
                setVisible(false);
                feedbackPage.setVisible(true);
            }
        });
        add(feedBtn);
        
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
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent as){

    }
    public static void main(String args[]){
        new ClientHome(1,1);
    }
   
}
