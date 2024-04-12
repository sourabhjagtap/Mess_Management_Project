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


public class SetData extends JFrame implements ActionListener{
    JButton submit, reset, cancle, backButton,add,removeButton;
    JTextField itemNameField, priceField,userNameField,userIdField;
    JTable table;
    String strn = "";
    int user_id;
    int manager_id=1;
    DefaultTableModel model;
    SetData(String str){
        super("Customer List");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        strn = str;
        
        JLabel userNameLable = new JLabel("User:");
        userNameLable.setBounds(5, 10, 80, 30);
        add(userNameLable);

        userIdField = new JTextField();
        userIdField.setBounds(100, 10, 150, 30);
        add(userIdField);
        
        JLabel itemNameLabel = new JLabel("Item:");
        itemNameLabel.setBounds(5, 50, 80, 30);
        add(itemNameLabel);

        itemNameField = new JTextField();
        itemNameField.setBounds(100, 50, 150, 30);
        add(itemNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(5, 90, 80, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(100, 90, 150, 30);
        add(priceField);
        
        
        model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("User id");
        model.addColumn("Item name");
        model.addColumn("Price");
        //model.addRow(new Object[]{"Roti","abc",10});
        //model.addRow(new Object[]{"Rice","def", 20});

        table = new JTable(model);
        table.setBorder(null);
        
        

         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
     // Fetch and display menu items from the database
        displayMenuItems();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 130, 500, 350);
        add(scrollPane);
        
        add = new JButton("Add");
        add.setBounds(100, 490, 80, 30);
        add.addActionListener(this);
        add(add);

        removeButton = new JButton("Remove");
        removeButton.setBounds(200, 490, 100, 30);
        removeButton.addActionListener(this);
        add(removeButton);

        backButton = new JButton("Back");
        backButton.setBounds(320, 490, 80, 30);
        backButton.addActionListener(this);
        add(backButton);

        setBounds(500, 200, 500, 600);
        setVisible(true);
    }
    
    
    private int displayMenuItems() {
        try {
            Mess mess = new Mess();
            // Fetch menu items from the database
            // Assuming `getMenuItems()` method returns a ResultSet containing menu items
            ResultSet resultSet = mess.getUserData();
            while (resultSet.next()) {
                // Extract item name and price from the ResultSet
            	// Retrieve the "Record_Date" column from the ResultSet
                String date = resultSet.getString("Record_Date");
                int usr_id = resultSet.getInt("user_id");
                String itemName = resultSet.getString("item_name");
                String price = resultSet.getString("Price");
                // Add the item to the table model
                model.addRow(new Object[]{date,usr_id,itemName, price});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
    

    public void actionPerformed(ActionEvent ae) {
    	// Fetch and display menu items from the database
        displayMenuItems();
        if (ae.getSource() == backButton) {
            setVisible(false);
            new ManagerPage(manager_id);
        } else if (ae.getSource() == add) {
        	String usr_id = userIdField.getText();
            //String price = priceField.getText();
            String itemName = itemNameField.getText();
            String price = priceField.getText();
//            model.addRow(new Object[]{user_id,itemName, price});
//            itemNameField.setText("");
//            priceField.setText("");
            
            //insertion in database
			Mess mess=new Mess();
			int i=mess.insert_user_data(usr_id,itemName, price);
			
			if (i > 0) {
				JOptionPane.showMessageDialog(null, "Menu Added..!");
				// Fetch and display menu items from the database
		        //displayMenuItems();
				 setVisible(true);
		           //new ManagerPage(manager_id);
				System.out.println("Menu Added...");
			} else {
				System.out.println("error.....");
			}
            
			
        }
        
     
        
        else if (ae.getSource() == removeButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            	 String itemNameToRemove = (String) model.getValueAt(selectedRow, 0);
                model.removeRow(selectedRow);
                
              //Now you can use itemNameToRemove for further processing
    			 //For example, you can use it to delete the corresponding entry from your database
    			 Mess mess=new Mess();
    			 int i=mess.delete_menu(itemNameToRemove);
    			 if (i > 0) {
    			     JOptionPane.showMessageDialog(null, "Menu Removed..!");
    			  // Fetch and display menu items from the database
    			        displayMenuItems();
    			     setVisible(false);
    			     new ManagerPage(manager_id);
    			     System.out.println("Menu Removed...");
    			 } else {
    			     System.out.println("error.....");
    			 }
                
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to remove.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
            }
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
        new SetData("");
    }
}
