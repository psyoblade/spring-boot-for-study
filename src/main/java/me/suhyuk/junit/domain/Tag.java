package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Tag.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    public static final String TABLE_NAME = "TAG";
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
    public Tag(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "tag", orphanRemoval = true) private List<TeamTag> teamTags = new ArrayList<>();

    public List<TeamTag> getTeamTags() {
        return teamTags;
    }

    public void setTeamTags(List<TeamTag> teamTags) {
        this.teamTags = teamTags;
    }

    public void registerTag(TeamTag teamTag) {
        teamTag.setTag(this);
        teamTags.add(teamTag);
    }
}