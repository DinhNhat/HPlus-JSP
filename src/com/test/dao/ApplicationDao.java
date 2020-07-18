package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.beans.Product;
import com.test.beans.User;

public class ApplicationDao {
	
	// search product
	public List<Product> searchProducts(String searchString) {
		
		Product product = null;
		List<Product> products = new ArrayList<>();
		
		try {
			
			Connection connection = DBConnection.getConnectionToDatabase();
			
			String sql = "select * from products where product_name like '%"+searchString+"%'";
			
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery(sql);
			
			while(set.next()) {
				product = new Product();
				product.setId(set.getInt("product_id"));
				product.setName(set.getString("product_name"));
				product.setPath(set.getString("image_path"));
				products.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	// register User
	public int registerUser(User user) {
		int rowAffected = 0;
		
		try {
			
			// get the connection for the database
			Connection connection = DBConnection.getConnectionToDatabase();
			
			// write the insert query
			String insertQuery = "insert into users values(?,?,?,?,?,?)";
			
			// set parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getActivity());
			
			// execute the statement
			rowAffected = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return rowAffected;
	}
	
	public boolean validateUser(String username, String password) {
		try {
			
			// get the connection for the database
			Connection connection = DBConnection.getConnectionToDatabase();
			
			// write the select query
			String query = 
			
			// set parameters with PreparedStatement
			
			// execute the statement and check whether user exists
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
