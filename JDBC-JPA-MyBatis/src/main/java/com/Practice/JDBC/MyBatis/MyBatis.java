package com.Practice.JDBC.MyBatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.InputStream;
import java.util.List;

//User.java (Java Class): DTO, DB와 매핑하기 위한 모델 객체
//MyBatis.java (Main Java Class): 환경설정과 쿼리문을 제어하고 SQL세션을 생성 및 관리하는 컨트롤러
// -> MyBatis.java 파일에서 연산 부분만을 분리해서 객체화하면 DAO가 됨 ex) session.insert("com.Practice.mapper.UserMapper.insertUser", user); // INSERT SQL 실행
//mybatis-config.xml (MyBatis Configuration): 마이바티스의 환경설정 파일
//userMapper.xml (Mapper XML): 실질적인 SQL쿼리를 작성하는 파일
public class MyBatis {
	public static void main(String[] args) {
		try {
			// MyBatis 설정 파일 로드
			InputStream inputStream = Resources.getResourceAsStream("mybatis/mapper/mybatis-config.xml");
			// SqlSessionFactory 생성
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// SqlSession 열기
			SqlSession session = sqlSessionFactory.openSession();

			// INSERT 작업
			User user = new User();
			user.setName("Bob");
			user.setEmail("bob@example.com");
			session.insert("com.Practice.mapper.UserMapper.insertUser", user); // INSERT SQL 실행
			session.commit(); // 커밋

			// SELECT 작업
			List<User> users = session.selectList("com.Practice.mapper.UserMapper.selectUsers"); // SELECT SQL 실행
			if (users.isEmpty()) {
				System.out.println("데이터가 없습니다.");
			} else {
				for (User u : users) {
					System.out.println(u.getId() + ": " + u.getName() + ", " + u.getEmail()); // 사용자 출력
				}
			}

			// UPDATE 작업
			user.setName("Bob");
			user.setEmail("bob@naver.com");
			session.update("com.Practice.mapper.UserMapper.updateUser", user); // UPDATE SQL 실행
			session.commit(); // 커밋

			// SELECT 작업
			List<User> users2 = session.selectList("com.Practice.mapper.UserMapper.selectUsers"); // SELECT SQL 실행
			if (users2.isEmpty()) {
				System.out.println("데이터가 없습니다.");
			} else {
				for (User u : users2) {
					System.out.println(u.getId() + ": " + u.getName() + ", " + u.getEmail()); // 사용자 출력
				}
			}

			// DELETE 작업 (주석 처리됨)
			session.delete("com.Practice.mapper.UserMapper.deleteUser", "Bob");
			session.commit(); // 커밋

			// SELECT 작업
			List<User> users3 = session.selectList("com.Practice.mapper.UserMapper.selectUsers"); // SELECT SQL 실행
			if (users3.isEmpty()) {
				System.out.println("데이터가 없습니다.");
			} else {
				for (User u : users3) {
					System.out.println(u.getId() + ": " + u.getName() + ", " + u.getEmail()); // 사용자 출력
				}
			}

			session.close(); // 세션 종료

		} catch (Exception e) {
			e.printStackTrace(); // 예외 출력
		}
	}
}
