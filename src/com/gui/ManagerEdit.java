package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

import com.dao.Mess;

public class ManagerEdit extends JFrame implements ActionListener {
    private JButton submit, reset, cancel, managerEditButton;
    private JLabel lblUserId, lblUsername, lblAddress, lblEmail, lblPhoneNo, lblUPIId;
    private JLabel userId, lblMail;
    private JTextField userName, address, email, upiId;
    private JFormattedTextField phoneNo;
    int managerid;
    String name,username,mail,phone,manager_address,upiid;
    ManagerEdit(int m_id){
        super("ManagerEdit");
        this.managerid=m_id;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        lblUserId = new JLabel("ID ");
        lblUserId.setBounds(170,20,50,20);
        add(lblUserId);
        
        Mess mess=new Mess();
        ResultSet rs=mess.manager_Profile(managerid);
        try {
        	if(rs.next()) {
        		name=rs.getString(2);
        		manager_address=rs.getString(3);
        		mail=rs.getString(4);
        		phone=rs.getString(5);
        		username=rs.getString(6);
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        

        userId = new JLabel(Integer.toString(managerid));
        userId.setBounds(270,20,200,20);
        add(userId);

        lblUsername = new JLabel("Name");
        lblUsername.setBounds(170,60,50,20);
        add(lblUsername);

        userName = new JTextField(name);
        userName.setBounds(270,60,200,20);
        add(userName);

        lblAddress = new JLabel("Address");
        lblAddress.setBounds(170,100,50,20);
        add(lblAddress);

        address = new JTextField(manager_address);
        address.setBounds(270,100,200,20);
        add(address);

        lblMail = new JLabel("Email");
        lblMail.setBounds(170,140,50,20);
        add(lblMail);

        email = new JTextField(mail);
        email.setBounds(270,140,200,20);
        add(email);

        lblPhoneNo = new JLabel("Phone No.");
        lblPhoneNo.setBounds(170,180,70,20);
        add(lblPhoneNo);

        try {
            phoneNo = new JFormattedTextField(new MaskFormatter("##########"));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        phoneNo.setBounds(270, 180, 100, 20);
        add(phoneNo);

        lblUPIId = new JLabel("UPI ID ");
        lblUPIId.setBounds(170,220,50,20);
        add(lblUPIId);

        upiId = new JTextField();
        upiId.setBounds(270,220,200,20);
        add(upiId);

        managerEditButton = new JButton("Save");
        managerEditButton.setBounds(250,280,100,30);
        managerEditButton.setBackground(Color.YELLOW);
        managerEditButton.setBorder(BorderFactory.createEmptyBorder());
        
        add(managerEditButton);

        setBounds(450, 200, 600, 400);
        setVisible(true);
        setResizable(false);
        
        managerEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ee) {
            	if (ee.getSource() == managerEditButton) {
            		
            		String name = userName.getText();
        			String man_adress = address.getText();
        			String mail = email.getText();
        			String phone_no = phoneNo.getText();
        			String upi = upiId.getText();
        			
        			Mess mess=new Mess();
        			int i=mess.update_manager_Profile(name,man_adress,mail,phone_no,upi,managerid);
        			
        			if (i > 0) {
        				JOptionPane.showMessageDialog(null, "Profile Updated..!");
        				 setVisible(false);
        		           new ManagerPage(managerid);
        				System.out.println("profile updatedd...");
        			} else {
        				System.out.println("error.....");
        			}
        			
        			
                }  else if(ee.getSource() == cancel){
                    setVisible(false);
                }
            	}
        });
    }

    public void actionPerformed(ActionEvent ee){
        if(ee.getSource() == "Save"){
    		String name = userName.getText();
			String man_adress = address.getText();
			String mail = email.getText();
			String phone_no = phoneNo.getText();
			String upi = upiId.getText();
			
			Mess mess=new Mess();
			int i=mess.update_manager_Profile(name,man_adress,mail,phone_no,upi,managerid);
			
			if (i > 0) {
				JOptionPane.showMessageDialog(null, "Profile Updated..!");
				 setVisible(false);
		           new ManagerPage(managerid);
				System.out.println("profile updatedd...");
			} else {
				System.out.println("error.....");
			}
			
			
        } else if(ee.getSource() == reset){

        } else if(ee.getSource() == cancel){
            setVisible(false);
        }
    }

    public static void main(String args[]){
        //new ManagerEdit();
    }
}
