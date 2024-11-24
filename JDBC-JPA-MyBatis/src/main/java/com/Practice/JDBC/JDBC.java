package com.Practice.JDBC;

import java.io.*;
import java.util.*;

import java.sql.*;

public class JDBC {
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

            // SELECT
            String selectQuery1 = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectQuery1)) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
                }
            }
            
            // UPDATE
            String updateQuery = "UPDATE users SET email = ? WHERE name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.setString(1, "bob.updated@example.com");
                pstmt.setString(2, "Bobby");
                pstmt.executeUpdate();
            }

            // SELECT
            String selectQuery2 = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectQuery2)) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
                }
            }
            
            // DELETE
            String deleteQuery = "DELETE FROM users WHERE name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setString(1, "Bob");
                pstmt.executeUpdate();
            }

            // SELECT
            String selectQuery3 = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectQuery3)) {
            	
            	if (!rs.isBeforeFirst()) { // 데이터가 없으면
                    System.out.println("데이터가 없습니다.");
                } else {
                    while (rs.next()) {
                        System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
