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

public class ClientBill extends JFrame implements ActionListener {
	JButton back_Btn , pay_Btn;
    JLabel lblBill, lblusername, lbladrs, lblshowBill, lblphoNo, lblpreference;
    JLabel UserId;
    JTextField userName, adrs, email;
    int userId,manager_id;
    //String amount="10";
    int amount=0;
    
    ClientBill(int usr_id,int managerId){
    	super("Total Bill");
        this.userId=usr_id;
        this.manager_id=managerId;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(450, 200, 500, 300);
        setVisible(true);
        
        
        Mess mess=new Mess();
        ResultSet rs=mess.client_bill(userId);
        
        try {
        	if(rs.next()) {
        		amount=rs.getInt(1);
        		System.out.println(amount);
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        lblBill = new JLabel("Total Amount : ");
        Font labelFont = new Font("Arial", Font.BOLD, 25); 
        lblBill.setFont(labelFont);
        lblBill.setBounds(140, 50, 200, 30);
        add(lblBill);
        
        lblshowBill = new JLabel(String.valueOf(amount));
        lblshowBill.setFont(labelFont);
        lblshowBill.setBounds(340, 50, 30,30);
        add(lblshowBill);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        back_Btn = new JButton("Back");
        back_Btn.addActionListener(this);
        back_Btn.setBounds(120, 200, 100, 30); // Adjusted bounds for better visibility
        add(back_Btn);

        pay_Btn = new JButton("Pay");
        pay_Btn.addActionListener(this);
        pay_Btn.setBounds(260, 200, 100, 30); // Adjusted bounds for better visibility
        add(pay_Btn);
        
        setVisible(true);
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == back_Btn) {
            // Add your code here for the "Back" button action
			setVisible(false);
            new ClientDatapage("");
        } else if (e.getSource() == pay_Btn) {
            // Add your code here for the "Pay" button action
        	setVisible(false);
        	System.out.println(manager_id);
            new PayBill(userId,manager_id);
        	
        }
    }
	
	public static void main(String args[]){
        //new ClientBill(0,1);
    }
}
