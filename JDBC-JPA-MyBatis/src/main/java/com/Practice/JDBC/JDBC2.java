package com.Practice.JDBC;

import java.io.*;
import java.util.*;

import java.sql.*;

public class JDBC2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Practice";
        String user = "root";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
        	
            // INSERT
            String insertQuery = "INSERT INTO users (name, email) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, "Bob");
                pstmt.setString(2, "bob@example.com");
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
