package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = Team.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    public static final String TABLE_NAME = "TEAM";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME") private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Builder
    public Team(String name) {
        this.name = name;
    }

    public void hire(Member suhyuk) {
        suhyuk.setTeam(this);
    }
}