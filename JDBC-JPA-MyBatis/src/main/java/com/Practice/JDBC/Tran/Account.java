package com.Practice.JDBC.Tran;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: 자동으로 getter, setter, toString, equals, hashCode 메서드를 생성
@NoArgsConstructor // Lombok: 매개변수가 없는 기본 생성자를 생성
@AllArgsConstructor // Lombok: 모든 필드를 매개변수로 갖는 생성자를 생성
@Builder // Lombok: 빌더 패턴을 사용하여 객체를 생성할 수 있게 함
public class Account {
    private int id;
    private String name;
    private String email;
    private int balance;
    
}
