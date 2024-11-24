package com.Practice.JDBC.MyBatis;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data // Lombok: 자동으로 getter, setter, toString, equals, hashCode 메서드를 생성
@NoArgsConstructor // Lombok: 매개변수가 없는 기본 생성자를 생성
@AllArgsConstructor // Lombok: 모든 필드를 매개변수로 갖는 생성자를 생성
@Builder // Lombok: 빌더 패턴을 사용하여 객체를 생성할 수 있게 함
public class User {
    private int id;
    private String name;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String email;

    // Lombok 어노테이션이 적용되었으므로, 별도로 getter, setter가 자동으로 생성됨
}
