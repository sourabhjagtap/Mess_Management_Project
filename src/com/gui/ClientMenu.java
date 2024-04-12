package com.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class ClientMenu extends JFrame implements ActionListener {
    JButton submit, backButton;
    DefaultTableModel model;
    JTable table;
    int user_id;
    int manager_id=1;
    ClientMenu() {
        super("Menu Selection");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.addColumn("Select");
        model.addColumn("Item");
        model.addColumn("Price");
        model.addColumn("Quantity");

        model.addRow(new Object[]{false, "Roti", 10, 0});
        model.addRow(new Object[]{false, "Rice", 20, 0});

        table = new JTable(model);
        table.setBorder(null);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        add(submit, BorderLayout.SOUTH);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton, BorderLayout.NORTH);

        setBounds(500, 200, 500, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton) {
            setVisible(false);
            new ClientHome(user_id,manager_id);
            
        } else if (ae.getSource() == submit) {
            // Place order and show success message
            JOptionPane.showMessageDialog(this, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Redirect to ClientHome
            setVisible(false);
            new ClientHome(user_id,manager_id);
        }
    }

    public static void main(String args[]) {
        new ClientMenu();
    }
}