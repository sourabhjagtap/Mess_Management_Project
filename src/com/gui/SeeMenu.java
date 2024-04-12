package com.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.Mess;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeeMenu extends JFrame implements ActionListener {
    JButton backButton;
    String strn = "";
    int user_id;
    int manager_id=1;
    DefaultTableModel model;
    SeeMenu(String str) {
        super("Today's Menu");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        strn = str;

        model = new DefaultTableModel();
        model.addColumn("Item");
        model.addColumn("Price");
//        model.addRow(new Object[]{"Roti", 10});
//        model.addRow(new Object[]{"Rice", 20});

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
        
        
     // Fetch and display menu items from the database
        displayMenuItems();
        
        
        backButton = new JButton("Back");
        backButton.setBounds(200, 500, 80, 30);
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
        if (ae.getSource() == backButton) {
            setVisible(false);
            if (strn.equals("ClientHome")) {
                new ClientHome(user_id,manager_id);
            } else if (strn.equals("ManagerPage")) {
                new ManagerPage(manager_id);
            }
        }
    }

    public static void main(String args[]) {
        new SeeMenu("");
    }
}
