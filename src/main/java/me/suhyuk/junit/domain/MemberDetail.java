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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NICKNAME", length = 50) private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Builder
    public MemberDetail(String nickname) {
        this.nickname = nickname;
    }
}