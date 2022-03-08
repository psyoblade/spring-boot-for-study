package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = MemberLocker.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLocker {
    public static final String TABLE_NAME = "MEMBER_LOCKER";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @OneToOne(mappedBy = "memberLocker", orphanRemoval = true) private Member member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 50) private String name;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Builder
    public MemberLocker(String name) {
        this.name = name;
    }
}