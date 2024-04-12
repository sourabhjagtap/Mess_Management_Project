//package com.gui;
//
//import javax.swing.*;
//
//import com.dao.Mess;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SeeFeedback extends JFrame {
//    private Map<Integer, Integer> ratingCounts;
//    private Map<Integer, String> ratingMessages;
//    int manager_id;
//    
//    public SeeFeedback() {
//        setTitle("Feedback Analysis");
//        setSize(600, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        ratingCounts = new HashMap<>();
//        ratingMessages = new HashMap<>();
//
//        // Retrieve feedback data from the database and calculate averages
//        retrieveFeedbackData();
//
//        // Create bar graph panel
//        BarGraphPanel barGraphPanel = new BarGraphPanel(ratingCounts, ratingMessages);
//
//        // Add bar graph panel to frame
//        add(barGraphPanel);
//
//        // Add back button
//        JButton backButton = new JButton("Back");
//        backButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//                new ManagerPage(manager_id).setVisible(true);
//            }
//        });
//        add(backButton, BorderLayout.SOUTH);
//
//        setVisible(true);
//    }
//
//    private void retrieveFeedbackData() {
//        // Connect to the database and retrieve feedback data
//        try {
//            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
//            //Statement statement = connection.createStatement();
//            // resultSet = statement.executeQuery("SELECT rating, COUNT(*) AS count FROM feedback GROUP BY rating");
//        	
//        	Mess mess = new Mess();
//            ResultSet resultSet = mess.getFeedbackData(); // Assuming getFeedbackData() retrieves data from user_feedback table
//        	
//            int totalFeedbacks = 0;
//            int maxRating = 0;
//
//            while (resultSet.next()) {
//                int rating = resultSet.getInt("rating");
//                int count = resultSet.getInt("count");
//
//                ratingCounts.put(rating, count);
//
//                totalFeedbacks += count;
//                maxRating = Math.max(maxRating, rating);
//            }
//
//            // Calculate averages
//            for (Map.Entry<Integer, Integer> entry : ratingCounts.entrySet()) {
//                int rating = entry.getKey();
//                int count = entry.getValue();
//                double average = (double) count / totalFeedbacks;
//                ratingCounts.put(rating, (int) (average * 100)); // Scale for bar graph
//            }
//
//            // Load text messages corresponding to ratings
//            loadRatingMessages(maxRating);
//
//            //connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadRatingMessages(int maxRating) {
//        // You can load text messages corresponding to each rating from a file or database
//        // For demonstration purposes, I'm just assigning some generic messages here
//        for (int i = 1; i <= maxRating; i++) {
//            ratingMessages.put(i, "Message for rating " + i);
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(SeeFeedback::new);
//    }
//}
//
//class BarGraphPanel extends JPanel {
//    private Map<Integer, Integer> ratingCounts;
//    private Map<Integer, String> ratingMessages;
//
//    public BarGraphPanel(Map<Integer, Integer> ratingCounts, Map<Integer, String> ratingMessages) {
//        this.ratingCounts = ratingCounts;
//        this.ratingMessages = ratingMessages;
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        int barWidth = 50;
//        int spacing = 20;
//        int x = 50;
//        int y = 50;
//
//        for (Map.Entry<Integer, Integer> entry : ratingCounts.entrySet()) {
//            int rating = entry.getKey();
//            int count = entry.getValue();
//            int barHeight = count;
//
//            // Draw bar
//            g.setColor(Color.BLUE);
//            g.fillRect(x, y - barHeight, barWidth, barHeight);
//
//            // Draw rating label
//            g.setColor(Color.BLACK);
//            g.drawString(String.valueOf(rating), x + barWidth / 2 - 5, y + 15);
//
//            // Draw message
//            g.drawString(ratingMessages.get(rating), x, y + 30);
//
//            x += barWidth + spacing;
//        }
//    }
//
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(500, 300);
//    }
//}




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

public class SeeFeedback extends JFrame implements ActionListener{
    JButton submit, reset, cancle, backButton;
    // usr_name;
    int manager_id=1;
    int usr_id;
    String user_name;
    DefaultTableModel model;
    SeeFeedback(){
        super("Feedback");
        //this.user_name=usr_name;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        model = new DefaultTableModel();
        model.addColumn("User_id");
        model.addColumn("Rating");
        model.addColumn("Feedback");
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
        backButton.setBounds(200, 500, 80, 30);
        backButton.addActionListener(this);
        add(backButton);
        
    
        
//        try {
//        	if(rs.next()) {
//        		usr_id =rs.getInt(1);
//        		//System.out.print("user id "+usr_id);
//        	}
//			
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        
        //calling to display menu
        displayMenuItems();
        
        setBounds(500, 200, 500, 600);
        setVisible(true);
    }
    
    private void displayMenuItems() {
        try {
        	Mess mess=new Mess();
            ResultSet rs=mess.getFeedbackData();
            System.out.print("Done");
            //ResultSet urs=mess.user_info(usr_id);
            while (rs.next()) {
            	System.out.print("IN");
                // Extract item name and price from the ResultSet
            	int uid = rs.getInt("user_id");
                //int usr_id = resultSet.getInt("user_id");
                int rate = rs.getInt("rating");
                String feed = rs.getString("feedback");
                // Add the item to the table model
                model.addRow(new Object[]{uid,rate, feed});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backButton ) {
            setVisible(false);
            new ManagerPage(manager_id);
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

