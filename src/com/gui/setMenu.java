
package com.gui;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.Mess;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class setMenu extends JFrame implements ActionListener {
    JButton submit, add, reset, backButton, removeButton;
    JTextField itemNameField, priceField;
    DefaultTableModel model;
    JTable table;
    int manager_id=1;
    setMenu() {
        super("Set Menu");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("Item");
        model.addColumn("Price");
        //model.addRow(new Object[]{"Roti", 10});
        //model.addRow(new Object[]{"Rice", 20});

        table = new JTable(model);
        table.setBorder(null);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 50, 500, 400);
        add(scrollPane);

        JLabel itemNameLabel = new JLabel("Item:");
        itemNameLabel.setBounds(10, 10, 80, 30);
        add(itemNameLabel);

        itemNameField = new JTextField();
        itemNameField.setBounds(100, 10, 150, 30);
        add(itemNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(270, 10, 80, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(350, 10, 100, 30);
        add(priceField);

        add = new JButton("Add");
        add.setBounds(100, 500, 80, 30);
        add.addActionListener(this);
        add(add);

        removeButton = new JButton("Remove");
        removeButton.setBounds(200, 500, 100, 30);
        removeButton.addActionListener(this);
        add(removeButton);

        backButton = new JButton("Back");
        backButton.setBounds(320, 500, 80, 30);
        backButton.addActionListener(this);
        add(backButton);
        
        
        setBounds(500, 200, 500, 600);
        setVisible(true);
        setResizable(false);
    }
    
    
    private void displayMenuItems() {
        try {
            Mess mess = new Mess();
            // Fetch menu items from the database
            // Assuming `getMenuItems()` method returns a ResultSet containing menu items
            ResultSet resultSet = mess.getMenuItems();
            while (resultSet.next()) {
                // Extract item name and price from the ResultSet
                String itemName = resultSet.getString("item_name");
                String price = resultSet.getString("price");
                // Add the item to the table model
                model.addRow(new Object[]{itemName, price});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
                          
    public void actionPerformed(ActionEvent ae) {
    	// Fetch and display menu items from the database
        //displayMenuItems();
        if (ae.getSource() == backButton) {
            setVisible(false);
            new ManagerPage(manager_id);
        } else if (ae.getSource() == add) {
            String itemName = itemNameField.getText();
            String price = priceField.getText();
            //model.addRow(new Object[]{itemName, price});
//            itemNameField.setText("");
//            priceField.setText("");
            
            //insertion in database
			Mess mess=new Mess();
			int i=mess.insert_menu(itemName,price);
			
			if (i > 0) {
				JOptionPane.showMessageDialog(null, "Menu Added..!");
				// Fetch and display menu items from the database
		        displayMenuItems();
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
    			        //displayMenuItems();
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

    public static void main(String args[]) {
        //new setMenu();
    }
}
