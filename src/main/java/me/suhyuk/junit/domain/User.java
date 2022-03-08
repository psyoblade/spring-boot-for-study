package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = User.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    public static final String TABLE_NAME = "USER";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "NAME", length = 50) private String name;

    @OneToOne(orphanRemoval = true) @JoinColumn(name = "USER_DETAIL_ID") private UserDetail userDetail;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void addUserDetailInfo(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Builder
    public User (String name) {
        this.name = name;
    }
}