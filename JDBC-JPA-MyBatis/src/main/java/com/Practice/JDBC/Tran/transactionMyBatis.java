package com.Practice.JDBC.Tran;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class transactionMyBatis {
	public static void main(String[] args) throws IOException {
		// MyBatis 설정 파일 로드
		InputStream inputStream = Resources.getResourceAsStream("mybatis/mapper/account-config.xml");
		// SqlSessionFactory 생성
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// SqlSession 열기
		SqlSession session = sqlSessionFactory.openSession();

		try {
			// SQL 실행
			Account account = new Account(10, "Alice", "alice@example.com", 10000);
			session.insert("com.Practice.mapper.accountMapper.insertAccount", account);		
			
			Account updatedAccount = new Account(10, "Alice", "alice@example.com", account.getBalance() - 1000);

			if (updatedAccount.getBalance() < 0) {
				System.out.println("잔액 부족: 출금할 수 없습니다.");
				session.rollback(); // 트랜잭션 롤백
				return;
			}

			session.update("com.Practice.mapper.accountMapper.updateAccount", updatedAccount);

			// 커밋
			session.commit();
			System.out.println("[트랜잭션 성공] 잔액 : " + (updatedAccount.getBalance()));
		} catch (Exception e) {
			// 롤백
			session.rollback();
			System.out.println("트랜잭션 실패: 롤백 실행");

		}
	}
}
