package com.gui;

import javax.swing.*;

import com.dao.Mess;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerProfile extends JFrame implements ActionListener {
    private JButton backButton,logout;
    private String strn = "";
    private JLabel lblUserId, lblusername, lbladrs, lblUPI, lblMail, lblphoNo;
    private JLabel userId, userName, address, UPIid, email, phoNo;
    String name,username,mail,phone,manager_address,upiid;
    int user_id;
    int manager_id;
    public ManagerProfile(String str,int managerid) {
        super("Manager Profile");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        strn = str;
        this.manager_id=managerid;
        
        Mess mess=new Mess();
        ResultSet rs=mess.manager_Profile(managerid);
        
        try {
        	if(rs.next()) {
        		name=rs.getString(2);
        		manager_address=rs.getString(3);
        		mail=rs.getString(4);
        		phone=rs.getString(5);
        		username=rs.getString(6);
        		upiid=rs.getString(9);
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        // Labels
        lblUserId = new JLabel("ID");
        lblUserId.setBounds(150, 20, 50, 20);
        add(lblUserId);

        lblusername = new JLabel("Name");
        lblusername.setBounds(150, 60, 50, 20);
        add(lblusername);

        lbladrs = new JLabel("Address");
        lbladrs.setBounds(150, 100, 50, 20);
        add(lbladrs);

        lblUPI = new JLabel("UPI ID");
        lblUPI.setBounds(150, 140, 50, 20);
        add(lblUPI);

        lblMail = new JLabel("Email");
        lblMail.setBounds(150, 180, 50, 20);
        add(lblMail);

        lblphoNo = new JLabel("Phone No.");
        lblphoNo.setBounds(150, 220, 70, 20);
        add(lblphoNo);

        // Fields
        userId = new JLabel(Integer.toString(managerid));
        userId.setBounds(250, 20, 200, 20);
        add(userId);

        userName = new JLabel(name);
        userName.setBounds(250, 60, 200, 20);
        add(userName);

        address = new JLabel(manager_address);
        address.setBounds(250, 100, 200, 20);
        add(address);

        UPIid = new JLabel(upiid);
        UPIid.setBounds(250, 140, 200, 20);
        add(UPIid);

        email = new JLabel(mail);
        email.setBounds(250, 180, 200, 20);
        add(email);

        phoNo = new JLabel(phone);
        phoNo.setBounds(250, 220, 70, 20);
        add(phoNo);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(200, 300, 80, 30);
        backButton.addActionListener(this);
        add(backButton);

        setBounds(500, 200, 500, 400);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton && strn.equals("ClientHome")) {
            setVisible(false);
            new ClientHome(user_id,manager_id);
        } else if (ae.getSource() == backButton && strn.equals("ManagerPage")) {
            setVisible(false);
            new ManagerPage(manager_id);
        }
    }

    public static void main(String args[]) {
        new ManagerProfile("",1);
    }
}
