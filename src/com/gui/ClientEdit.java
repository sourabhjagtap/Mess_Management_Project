package com.gui;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

import com.dao.Mess;

import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

public class ClientEdit extends JFrame implements ActionListener {
    JButton save;
    JLabel lblUserId, lblusername, lbladrs, lblMail, lblphoNo, lblpreference;
    JLabel UserId;
    JTextField userName, adrs, email;
    JFormattedTextField phoNo;
    JRadioButton vegOpt, nonVegOpt;
    int u_id;
    String name,username,mail,phone,address;
    ButtonGroup gender;
    
    ClientEdit(int userId) {
        super("ClientEdit");
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
        lblUserId = new JLabel("User Id");
        lblUserId.setBounds(170, 20, 50, 20);
        add(lblUserId);

        // UserID field
        UserId = new JLabel(Integer.toString(u_id));
        UserId.setBounds(270, 20, 100, 20);
        add(UserId);

        // UserName label
        lblusername = new JLabel("Name");
        lblusername.setBounds(170, 60, 50, 20);
        add(lblusername);

        // UserName field
        userName = new JTextField(name);
        userName.setBounds(270, 60, 200, 20);
        add(userName);

        // Address label
        lbladrs = new JLabel("Address");
        lbladrs.setBounds(170, 100, 50, 20);
        add(lbladrs);

        // Address field
        adrs = new JTextField(address);
        adrs.setBounds(270, 100, 200, 20);
        add(adrs);

        // Email label
        lblMail = new JLabel("Email");
        lblMail.setBounds(170, 140, 50, 20);
        add(lblMail);

        // Email field
        email = new JTextField(mail);
        email.setBounds(270, 140, 200, 20);
        add(email);

        // Phone number label
        lblphoNo = new JLabel("Phone No.");
        lblphoNo.setBounds(170, 180, 70, 20);
        add(lblphoNo);

        // Phone number field
        phoNo = new JFormattedTextField(createFormatter("##########"));
        phoNo.setBounds(270, 180, 100, 20);
        add(phoNo);

        // Preference label
        lblpreference = new JLabel("Preference ");
        lblpreference.setBounds(170, 220, 70, 20);
        add(lblpreference);

        // Radio buttons
        vegOpt = new JRadioButton("Veg");
        vegOpt.setBackground(Color.WHITE);
        vegOpt.setForeground(Color.GREEN);
        vegOpt.setBounds(270, 220, 50, 20);
        add(vegOpt);

        nonVegOpt = new JRadioButton("Non-veg");
        nonVegOpt.setBackground(Color.WHITE);
        nonVegOpt.setForeground(Color.RED);
        nonVegOpt.setBounds(350, 220, 80, 20);
        add(nonVegOpt);

         gender = new ButtonGroup(); // Grouping radio buttons
        gender.add(vegOpt);
        gender.add(nonVegOpt);

        
        
		
        save = new JButton("Save");
        save.setBounds(250, 280, 100, 30);
        save.setBackground(Color.YELLOW);
        save.addActionListener(this);
        add(save);

        setBounds(450, 200, 600, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == save) {
        	
        	String selectedOption = null;
    		Enumeration<AbstractButton> buttons = gender.getElements();
    		while (buttons.hasMoreElements()) {
    			AbstractButton button = buttons.nextElement();
    			if (button.isSelected()) {
    				selectedOption = button.getText();
    				break;
    			}
    		}
    		
    		String name = userName.getText();
			String adress = adrs.getText();
			String mail = email.getText();
			String phone_no = phoNo.getText();
			
//			char[] passwordq = passwordField.getPassword();
//			String new_pass = new String(password);

			Mess mess=new Mess();
			int i=mess.updateProfile(name,adress,mail,phone_no,selectedOption,u_id);
			
			if (i > 0) {
				JOptionPane.showMessageDialog(null, "Profile Updated..!");
				 setVisible(false);
		           new ClientHome(u_id,1);
				System.out.println("profile updatedd...");
			} else {
				System.out.println("error.....");
			}
           
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
