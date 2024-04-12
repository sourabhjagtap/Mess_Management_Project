package com.gui;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.dao.Mess;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDatapage extends JFrame implements ActionListener{
    JButton submit, reset, billButton, backButton;
    // usr_name;
    int manager_id=1;
    int usr_id;
    String user_name;
    DefaultTableModel model;
    ClientDatapage(String usr_name){
        super("This Months Transactions");
        this.user_name=usr_name;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Menu");
        model.addColumn("Amount");
        //model.addRow(new Object[]{12,"Dal Rice Roti", 10});
        //model.addRow(new Object[]{12,"Dal Rice Roti", 10});

        JTable table = new JTable(model);
        table.setBorder(null);

         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 50, 500, 400);
        add(scrollPane);
        
        
        
        
        backButton = new JButton("Back");
        backButton.setBounds(150, 500, 80, 30);
        backButton.addActionListener(this);
        add(backButton);
        
        billButton = new JButton("Bill");
        billButton.setBounds(260, 500, 80, 30);
        billButton.addActionListener(this);
        add(billButton);
        
        Mess mess=new Mess();
        ResultSet rs=mess.user_data(user_name);
        
        try {
        	if(rs.next()) {
        		usr_id =rs.getInt(1);
        		System.out.print("user id "+usr_id);
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        //calling to display menu
        displayMenuItems();
        
        setBounds(500, 200, 500, 600);
        setVisible(true);
    }
    
    private void displayMenuItems() {
        try {
            Mess mess = new Mess();
            // Fetch menu items from the database
          //fetching data from user_data
            ResultSet urs=mess.user_info(usr_id);
            while (urs.next()) {
                // Extract item name and price from the ResultSet
            	String date = urs.getString("Record_Date");
                //int usr_id = resultSet.getInt("user_id");
                String itemName = urs.getString("item_name");
                String price = urs.getString("Price");
                // Add the item to the table model
                model.addRow(new Object[]{date,itemName, price});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton ) {
            setVisible(false);
            new ClientHome(usr_id,manager_id);
        }
        
        else if (ae.getSource() == billButton ) {
            setVisible(false);
            new ClientBill(usr_id,manager_id);
        }
    }

    
    
    
    // public void actionPerformed(ActionEvent ee){
    //     if(ee.getSource() == submit){

    //     }else if(ee.getSource() == reset){

    //     }else if(ee.getSource() == cancle){
    //         setVisible(false);
    //     }
    // }


    public static void main(String args[]){
        new ClientDatapage("");
    }
}
