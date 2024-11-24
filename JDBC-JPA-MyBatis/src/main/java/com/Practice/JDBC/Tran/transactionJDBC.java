package com.Practice.JDBC.Tran;

import java.sql.*;

public class transactionJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Practice";
        String user = "root";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 자동 커밋 비활성화
            conn.setAutoCommit(false);

            try (Statement stmt = conn.createStatement()) {
                // SQL 실행
                stmt.executeUpdate("INSERT INTO accounts (name, balance) VALUES ('Alice', 1000)");
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 500 WHERE name = 'Alice'");

                // 모든 작업 성공 시 커밋
                conn.commit();
                System.out.println("트랜잭션 성공");
            } catch (SQLException e) {
                // 오류 발생 시 롤백
                conn.rollback();
                System.out.println("트랜잭션 실패: 롤백 실행");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
