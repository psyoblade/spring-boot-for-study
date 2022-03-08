package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Team.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    public static final String TABLE_NAME = "TEAM";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "NAME", length = 50) private String name;

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
    public Team(String name) {
        this.name = name;
    }

    public void hire(Member songhee) {
        songhee.setTeam(this);
    }

    @OneToMany(mappedBy = "team", orphanRemoval = true) private List<TeamTag> teamTags = new ArrayList<>();

    public List<TeamTag> getTeamTags() {
        return teamTags;
    }

    public void setTeamTags(List<TeamTag> teamTags) {
        teamTags.forEach(teamTag -> teamTag.setTeam(this));
        this.teamTags = teamTags;
    }
}