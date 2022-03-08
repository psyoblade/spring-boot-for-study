package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = MemberDetail.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail {
    public static final String TABLE_NAME = "MEMBER_DETAIL";
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
    public MemberDetail(String nickName) {
        this.nickName = nickName;
    }
}