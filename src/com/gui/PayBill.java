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
public class PayBill extends JFrame implements ActionListener {
	JButton back_Btn , pay_Btn;
    JLabel lblBill, lblusername, lbladrs, lblshowBill, lblphoNo, lblpreference;
    JLabel UserId;
    JTextField userName, adrs, email;
    int usr_id,managerId=1;
    String Upi_id="";
    Mess mess;
    PayBill(int user_id,int manager_id){
    	super("Pay Bill");
        this.usr_id=user_id;
        this.managerId=manager_id;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(450, 200, 500, 300);
        setVisible(true);
        
        
        mess=new Mess();
        ResultSet rs=mess.manager_upi(managerId);
        
        try {
        	if(rs.next()) {
        		Upi_id=rs.getString(1);
        		System.out.println(Upi_id);
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
        
        lblBill = new JLabel("UPI ID : ");
        Font labelFont = new Font("Arial", Font.BOLD, 25); 
        lblBill.setFont(labelFont);
        lblBill.setBounds(140, 50, 200, 30);
        add(lblBill);
        
        lblshowBill = new JLabel(Upi_id);
        lblshowBill.setFont(labelFont);
        lblshowBill.setBounds(240, 50, 200,30);
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
            new ClientBill(usr_id,managerId);
        } else if (e.getSource() == pay_Btn) {
            // Add your code here for the "Pay" button action
        	//setVisible(false);
        	
        	mess=new Mess();
            int rs=mess.user_data_update(usr_id);
        	
        	JOptionPane.showMessageDialog(null, "Transaction completed successfully...");
		    setVisible(false);
//            new PayBill(userId,manager_id);
        	new ClientHome(usr_id,managerId);
        	
        }
		
	}
	
	public static void main(String args[]){
        //new PayBill(1,1);
    }

}
