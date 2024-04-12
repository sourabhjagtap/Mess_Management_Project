package com.gui;

import javax.swing.*;

import com.dao.Mess;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame implements ActionListener {

    JButton login, signup, cancel;
    JTextField username;
    JPasswordField password;
    Choice loggingin;

    public login() {
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // UserName text 
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.setPreferredSize(new Dimension(640, 50));
        JLabel lblusername = new JLabel("Username");
        username = new JTextField(15);
        usernamePanel.add(lblusername);
        usernamePanel.add(username);

        // Password text
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel.setPreferredSize(new Dimension(640, 50));
        JLabel lblpassword = new JLabel("Password");
        password = new JPasswordField(15);

        ImageIcon eye1 = new ImageIcon("icons/eyeClosed.png");
        Image eyeClosed = eye1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon eye3 = new ImageIcon("icons/eyeOpen.png");
        Image eye4 = eye3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);

        JLabel showPasswordButton = new JLabel(new ImageIcon(eyeClosed));

        showPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (password.getEchoChar() != 0) {
                    password.setEchoChar((char) 0);
                    showPasswordButton.setIcon(new ImageIcon(eye4)); // Show password
                } else {
                    password.setEchoChar('\u2022');
                    showPasswordButton.setIcon(new ImageIcon(eyeClosed)); // Hide password
                }
            }
        });

        passwordPanel.add(lblpassword);
        passwordPanel.add(password);
        passwordPanel.add(showPasswordButton);

        // Logging in as
        JPanel loginAsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel logginginas = new JLabel("Logging in as");
        loginAsPanel.setPreferredSize(new Dimension(640, 50));
        loggingin = new Choice();
        loggingin.add("Customer");
        loggingin.add("Manager");
        loginAsPanel.add(logginginas);
        loginAsPanel.add(loggingin);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        login = new JButton("Login");
        login.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        buttonPanel.add(login);
        buttonPanel.add(cancel);

        // Sign Up
        JPanel signUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel txt = new JLabel("Don't have account ? ");
        JLabel signupLabel = new JLabel("Sign Up");
        signupLabel.setForeground(Color.BLUE);
        signupLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signUp signUpPage = new signUp();
                setVisible(false);
                signUpPage.setVisible(true);
            }
        });
        signUpPanel.add(txt);
        signUpPanel.add(signupLabel);

        centerPanel.add(usernamePanel);
        centerPanel.add(passwordPanel);
        centerPanel.add(loginAsPanel);
        centerPanel.add(buttonPanel);
        centerPanel.add(signUpPanel);

        add(centerPanel, BorderLayout.CENTER);

        ImageIcon i7 = new ImageIcon("icons/UserLogo.jpg");
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        add(image, BorderLayout.WEST);

        // Add mainPanel to the center of the JFrame
        add(mainPanel, BorderLayout.CENTER);

        //  // Adjust size of mainPanel to fill available space
        mainPanel.setPreferredSize(new Dimension(400, 300));
        mainPanel.add(centerPanel);
        add(mainPanel);
        setBounds(400, 200, 640, 300);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    int managerId=1;
    public void actionPerformed(ActionEvent ae) {
        
    	if (ae.getSource() == login) {
             if (loggingin.getSelectedItem().equals("Manager")) {
                // Perform manager login
            	try {
					String managername1=username.getText();
					char[] pass=password.getPassword();
					String newpass=new String(pass);
					
					Mess mess=new Mess();
					
					ResultSet rs=mess.validateManager(managername1,newpass);
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Logged in successfully...");
					    setVisible(false);
					    int managerId=rs.getInt(1);
					    //System.out.println(userId);
					    new ManagerPage(managerId);
					}else {
						JOptionPane.showMessageDialog(null, "Invalid username or password..!");

					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                setVisible(false);
            }
            
             else if (loggingin.getSelectedItem().equals("Customer")) {
                    // Perform client login
                	try {
    					String username1=username.getText();
    					char[] pass=password.getPassword();
    					String newpass=new String(pass);
    					
    					Mess mess=new Mess();
    					
    					ResultSet rs=mess.validateUser(username1,newpass);
    					
    					if(rs.next()) {
    						JOptionPane.showMessageDialog(null, "Logged in successfully...");
    					    setVisible(false);
    					    int userId=rs.getInt(1);
    					    //System.out.println(userId);
    					    new ClientHome(userId,1);
    					}else {
    						JOptionPane.showMessageDialog(null, "Invalid username or password..!");

    					}
    				} catch (HeadlessException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                	
                } 
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
