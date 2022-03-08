package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = TeamTag.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamTag {
    public static final String TABLE_NAME = "TEAM_TAG";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 50) private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Builder
    public TeamTag(String name) {
        this.name = name;
    }

    @ManyToOne @JoinColumn(name = "TEAM_ID") private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @ManyToOne @JoinColumn(name = "TAG_ID") private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TeamTag{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}