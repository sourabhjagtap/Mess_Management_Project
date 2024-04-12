package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.border.Border;

import com.dao.Mess;

public class FeedbackPage extends JFrame {
    private JLabel titleLabel, ratingLabel, feedbackLabel;
    private StarRatingPanel starRatingPanel;
    private JTextArea feedbackTextArea;
    private JButton submitButton;
    private JPanel mainPanel, titlePanel, ratingPanel, feedbackPanel, submitPanel;
    private JScrollPane feedbackScrollPane;
    int user_id=1;
    int manager_id=1;
    public FeedbackPage(int usr_id) {
        setTitle("Feedback Page");
        
        this.user_id=usr_id;
        
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Title Label
        titleLabel = new JLabel("Meal Feedback");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Rating Components
        ratingLabel = new JLabel("Rating:");
        ratingLabel.setFont(new Font("Arial", Font.BOLD,15));
        starRatingPanel = new StarRatingPanel(5);

        // Feedback Components
        feedbackLabel = new JLabel("Additional Feedback:");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD,13));
        feedbackTextArea = new JTextArea();
        feedbackTextArea.setLineWrap(true);
        feedbackTextArea.setWrapStyleWord(true);
        feedbackScrollPane = new JScrollPane(feedbackTextArea);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rating = starRatingPanel.getSelectedRating();
                String feedback = feedbackTextArea.getText();
                // Handle submission of feedback here (e.g., save to database)
                Mess mess=new Mess();
                
                int rs =mess. saveFeedbackToDatabase(user_id,rating, feedback); // Call method to save feedback to database
                
                JOptionPane.showMessageDialog(null, "Thank you for your feedback!");
                System.out.print("Feedback recorded");
                clearFields();
                setVisible(false);
                new ClientHome(user_id,manager_id);
            }

        });

        // Main Panel Layout and Components
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add spacing
        ratingPanel = new JPanel();
        ratingPanel.add(ratingLabel);
        ratingPanel.add(starRatingPanel);
        mainPanel.add(ratingPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add spacing
        feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BorderLayout());
        feedbackPanel.add(feedbackLabel, BorderLayout.NORTH);
        feedbackPanel.add(feedbackScrollPane, BorderLayout.CENTER);
        mainPanel.add(feedbackPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add spacing
        submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitPanel.add(submitButton);
        mainPanel.add(submitPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add spacing

        add(mainPanel);
        setVisible(true);
        setResizable(false);
    }

    protected void saveFeedbackToDatabase(int rating, String feedback) {
		// TODO Auto-generated method stub
		
	}

	private void clearFields() {
        starRatingPanel.setSelectedRating(0);
        feedbackTextArea.setText("");
    }

    public static void main(String[] args) {
    	// Initialize user_id here
        int user_id = 123; // Replace 123 with the actual user_id value

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FeedbackPage(user_id);
            }
        });
    }
}

// Star Rating Panel Class
class StarRatingPanel extends JPanel {
    private int maxStars;
    private int selectedRating;

    public StarRatingPanel(int maxStars) {
        this.maxStars = maxStars;
        this.selectedRating = 0;
        setLayout(new FlowLayout());

        // Create star labels and add mouse listener
        for (int i = 1; i <= maxStars; i++) {
            JLabel starLabel = new JLabel("\u2605"); // Unicode for star character
            starLabel.setForeground(Color.GRAY);
            starLabel.setFont(new Font(starLabel.getFont().getName(), Font.PLAIN, 30)); // Increase star size
            starLabel.addMouseListener(new StarMouseListener(i));
            add(starLabel);
        }
    }

    public void setSelectedRating(int rating) {
        if (rating >= 0 && rating <= maxStars) {
            selectedRating = rating;
            updateStarColors();
        }
    }

    public int getSelectedRating() {
        return selectedRating;
    }

    private void updateStarColors() {
        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JLabel) {
                JLabel starLabel = (JLabel) components[i];
                int starNumber = i + 1;
                if (starNumber <= selectedRating) {
                    starLabel.setForeground(Color.YELLOW);
                } else {
                    starLabel.setForeground(Color.GRAY);
                }
            }
        }
    }

    private class StarMouseListener extends MouseAdapter {
        private int starNumber;

        public StarMouseListener(int starNumber) {
            this.starNumber = starNumber;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            setSelectedRating(starNumber);
        }
    }
}
