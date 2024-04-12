package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mess {

	Connection con=null;

		//insert into user table
	public int createUser(String name, String adress, String mail, String phone_no, String user_name, String new_pass,
			String selectedOption) {
		int i=0;
		
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into mess_user(name,address,email,phoneno,username,password,type) values (?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, adress);
			ps.setString(3, mail);
			ps.setString(4, phone_no);
			ps.setString(5, user_name);
			ps.setString(6, new_pass);
			ps.setString(7, selectedOption);
			
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
		
	}
	
	//check user available
	public ResultSet validateUser(String username1, String newpass) {
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from mess_user where username=? and password=?");
			ps.setString(1, username1);
			ps.setString(2, newpass);
			
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	//fetch user data
	public ResultSet user_Profile(int id) {
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from mess_user where user_id=?");
			ps.setInt(1, id);
			
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}

		//update user data
	public int updateProfile(String name, String adress, String mail, String phone_no, String selectedOption,
			int u_id) {
		int i=0;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("update mess_user set name=?,email=?,address=?,phoneno=?,pref=? where user_id=?");
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, adress);
			ps.setString(4, phone_no);
			ps.setString(5, selectedOption);
			ps.setInt(6, u_id);
			
			
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	//insert into manager table
	public int createManager(String name, String adress, String mail, String phone_no, String user_name,
			String new_pass, String selectedOption, String upiid) {
		// TODO Auto-generated method stub
		int i=0;
		
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into mess_manager(name,address,email,phoneno,username,password,type,upi_id) values (?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, adress);
			ps.setString(3, mail);
			ps.setString(4, phone_no);
			ps.setString(5, user_name);
			ps.setString(6, new_pass);
			ps.setString(7, selectedOption);
			ps.setString(8, upiid);
			
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	// check manager available
	public ResultSet validateManager(String managername1, String newpass) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from mess_manager where username=? and password=?");
			ps.setString(1, managername1);
			ps.setString(2, newpass);
			
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
		//fetch manager data
	public ResultSet manager_Profile(int managerid) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from mess_manager where manager_id=?");
			ps.setInt(1, managerid);
			
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

		//update manager profile
	public int update_manager_Profile(String name, String man_adress, String mail, String phone_no, String upi,int man_id) {
		// TODO Auto-generated method stub
		int i=0;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("update mess_manager set name=?,email=?,address=?,phoneno=?,upi_id=? where manager_id=?");
			ps.setString(1, name);
			ps.setString(2, mail);
			ps.setString(3, man_adress);
			ps.setString(4, phone_no);
			ps.setString(5, upi);
			ps.setInt(6,man_id);
			
			
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	public int insert_menu(String itemName, String price) {
		// TODO Auto-generated method stub
		
		int i=0;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into menu(item_name,price) values (?,?)");
			ps.setString(1, itemName);
			ps.setString(2, price);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	public int delete_menu(String itemNameToRemove) {
		// TODO Auto-generated method stub
		
		int i=0;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from menu where item_name = ?");
			ps.setString(1, itemNameToRemove);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	 // Method to retrieve menu items from the database
    public ResultSet getMenuItems() {
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT item_name, price FROM menu");
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

	public ResultSet getUserData() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT Record_date,user_id,item_name, Price FROM user_data");
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
	}

	public int insert_user_data(String usr_id, String itemName, String price) {
		// TODO Auto-generated method stub
		
		int i=0;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into user_data(Record_date,user_id,item_name,Price,status) values (?,?,?,?,?)");
			// Get the current date
	        Date currentDate = new Date(System.currentTimeMillis());
	        int userid = Integer.parseInt(usr_id);
	        int prc = Integer.parseInt(price);
	        ps.setDate(1, currentDate); // Set the current date to the PreparedStatement
			ps.setInt(2, userid);
			ps.setString(3, itemName);
			ps.setInt(4, prc);
			ps.setString(5, "Not Done");
			
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	//fetching data from mess user having user_name=usr_name
	public ResultSet user_data(String usr_name) {
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from mess_user where username=?");
			ps.setString(1, usr_name);
			
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}

	public ResultSet user_info(int usr_id) {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user_data where user_id=?");
			ps.setInt(1, usr_id);
			
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

	public int saveFeedbackToDatabase(int usr_id,int rating, String feedback) {
		// TODO Auto-generated method stub
		
		 int i = 0;
	        try {
	            con = DBConnection.getConnection();
	            PreparedStatement ps = con.prepareStatement("INSERT INTO user_feedback (user_id,rating, feedback) VALUES (?,?,?)");
	            ps.setInt(1, usr_id);
	            ps.setInt(2, rating);
	            ps.setString(3, feedback);
	            i = ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return i;
	}

	public ResultSet getFeedbackData() {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
        try {
            // Establish database connection
            con = DBConnection.getConnection();
            // Prepare the SQL statement
            PreparedStatement ps = con.prepareStatement("select * from user_feedback");
            // Execute the query and get the result set
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs; // Return the result set
	}

	
	//find total bill of particular client
	public ResultSet client_bill(int userId) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			System.out.println(userId);
			PreparedStatement ps=con.prepareStatement("select sum(Price) from user_data where user_id=? and status!=?");
			ps.setInt(1, userId);
			ps.setString(2, "Done");
			
			rs=ps.executeQuery();
			
//			try {
//	        	if(rs.next()) {
//	        		System.out.println(rs.getInt(1));
//	        	}
//				
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}

	 //find the upi_id of manager 
	public ResultSet manager_upi(int managerId) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("select upi_id from mess_manager where manager_id = ?");
			ps.setInt(1, managerId);
			
			rs=ps.executeQuery();
			}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public int user_data_update(int usr_id) {
		// TODO Auto-generated method stub
		
		int i=0;
		try {
			con=DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement("update user_data set status=? where user_id=?");
			ps.setString(1, "Done");
			ps.setInt(2, usr_id);
			
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	
	
}
