package com.Practice.JDBC.Tran;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.InputStream;
import java.util.List;

public class accountMapper {
	public static void main(String[] args) {
		try {
			// MyBatis 설정 파일 로드
			InputStream inputStream = Resources.getResourceAsStream("mybatis/mapper/account-config.xml");			
			// SqlSessionFactory 생성
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// SqlSession 열기
			SqlSession session = sqlSessionFactory.openSession();

			// INSERT 작업
			Account account = new Account();
			account.setName("Alice");
			account.setEmail("alice@example.com");
			account.setBalance(10000);
			session.insert("com.Practice.mapper.accountMapper.insertAccount", account); // INSERT SQL 실행
			session.commit(); // 커밋

			// SELECT 작업
			List<Account> accountInfo = session.selectList("com.Practice.mapper.accountMapper.getAllAccounts"); // SELECT SQL 실행
			if (accountInfo.isEmpty()) {
				System.out.println("데이터가 없습니다.");
			} else {
				for (Account u : accountInfo) {
					System.out.println(u.getId() + ": " + u.getName() + ", " + u.getEmail() + ", " + u.getBalance()); // 사용자 출력
				}
			}

			// UPDATE 작업
			//account.setName("Alice");
			//account.setEmail("alice@example.com");
			//account.setBalance(20000);
			//session.update("com.Practice.mapper.accountMapper.updateAccount", account); // UPDATE SQL 실행
			//session.commit(); // 커밋

			// SELECT 작업
			List<Account> accountInfo2 = session.selectList("com.Practice.mapper.accountMapper.getAllAccounts"); // SELECT SQL 실행
			if (accountInfo2.isEmpty()) {
				System.out.println("데이터가 없습니다.");
			} else {
				for (Account u : accountInfo2) {
					System.out.println(u.getId() + ": " + u.getName() + ", " + u.getEmail() + ", " + u.getBalance()); // 사용자 출력
				}
			}

			// DELETE 작업 (주석 처리됨)
			// Corrected DELETE call:
			//session.delete("com.Practice.mapper.accountMapper.deleteAccount", "Alice");
			//session.commit(); // 커밋

			// SELECT 작업
			List<Account> accountInfo3 = session.selectList("com.Practice.mapper.accountMapper.getAllAccounts"); // SELECT SQL 실행
			if (accountInfo3.isEmpty()) {
				System.out.println("데이터가 없습니다.");
			} else {
				for (Account u : accountInfo3) {
					System.out.println(u.getId() + ": " + u.getName() + ", " + u.getEmail() + ", " + u.getBalance()); // 사용자 출력
				}
			}

			session.close(); // 세션 종료

		} catch (Exception e) {
			e.printStackTrace(); // 예외 출력
		}
	}
}
