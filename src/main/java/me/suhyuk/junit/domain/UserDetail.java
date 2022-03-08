package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = UserDetail.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetail {
    public static final String TABLE_NAME = "USER_DETAIL";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "NICK_NAME", length = 50) private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Builder
    public UserDetail(String nickName) {
        this.nickName = nickName;
    }
}