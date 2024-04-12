package com.gui;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.dao.Mess;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfile extends JFrame implements ActionListener {
    private JButton back;
    private JLabel UserId, userName, adrs, email, phoNo, preference;
    int u_id;
    String name,username,mail,phone,address;
    
    
    public UserProfile(int userId) {
    	
        super("User Profile");
        System.out.println(userId);
        this.u_id=userId;
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Mess mess=new Mess();
        ResultSet rs=mess.user_Profile(u_id);
        
        try {
        	if(rs.next()) {
        		name=rs.getString(2);
        		address=rs.getString(3);
        		mail=rs.getString(4);
        		phone=rs.getString(5);
        		username=rs.getString(6);
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        // UserID label
        JLabel lblUserId = new JLabel("User Id");
        lblUserId.setBounds(150, 20, 50, 20);
        add(lblUserId);

        // UserID field
        UserId = new JLabel(Integer.toString(userId));
        UserId.setBounds(250, 20, 200, 20);
        add(UserId);

        // UserName label
        JLabel lblusername = new JLabel("Name");
        lblusername.setBounds(150, 60, 50, 20);
        add(lblusername);

        // UserName field
        userName = new JLabel(name);
        userName.setBounds(250, 60, 200, 20);
        add(userName);

        // Address label
        JLabel lbladrs = new JLabel("Address");
        lbladrs.setBounds(150, 100, 50, 20);
        add(lbladrs);

        // Address field
        adrs = new JLabel(address);
        adrs.setBounds(250, 100, 200, 20);
        add(adrs);

        // Email label
        JLabel lblMail = new JLabel("Email");
        lblMail.setBounds(150, 140, 50, 20);
        add(lblMail);

        // Email field
        email = new JLabel(mail);
        email.setBounds(250, 140, 200, 20);
        add(email);

        // Phone number label
        JLabel lblphoNo = new JLabel("Phone No.");
        lblphoNo.setBounds(150, 180, 70, 20);
        add(lblphoNo);

        // Phone number field
        phoNo = new JLabel(phone);
        phoNo.setBounds(250, 180, 70, 20);
        add(phoNo);

        // Preference label
        JLabel lblpreference = new JLabel("Preference ");
        lblpreference.setBounds(150, 220, 70, 20);
        add(lblpreference);

        // Preference field
        preference = new JLabel("-");
        preference.setForeground(Color.RED);
        preference.setBounds(250, 220, 70, 20);
        add(preference);

        back = new JButton("Back");
        back.setBounds(190, 300, 100, 20);
        back.addActionListener(this);
        add(back);

        setBounds(500, 200, 500, 400);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new ClientHome(u_id,1);
        }
    }

    private MaskFormatter createFormatter(String format) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(format);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        return formatter;
    }

    private boolean isValidPassword(String password) {
        // Password constraints: minimum 8 characters, 1 capital letter, 3 numbers, 1 special character
        if (password.length() < 8) return false;

        int uppercaseCount = 0;
        int digitCount = 0;
        int specialCharCount = 0;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) uppercaseCount++;
            if (Character.isDigit(c)) digitCount++;
            if (!Character.isLetterOrDigit(c)) specialCharCount++;
        }

        return uppercaseCount >= 1 && digitCount >= 3 && specialCharCount >= 1;
    }

    
}
