package me.suhyuk.junit.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Member.TABLE_NAME)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    public static final String TABLE_NAME = "MEMBER";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "NAME", length = 50) private String name;

    @OneToOne(orphanRemoval = true) @JoinColumn(name = "MEMBER_DETAIL_ID") private MemberDetail memberDetail;

    @OneToMany(mappedBy = "member", orphanRemoval = true) private List<KafkaConnector> kafkaConnectors = new ArrayList<>();

    public MemberDetail getMemberDetail() {
        return memberDetail;
    }

    public void addMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
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

    @ManyToOne @JoinColumn(name = "TEAM_ID") private Team team;

    public List<KafkaConnector> getKafkaConnectors() {
        return kafkaConnectors;
    }

    public void setKafkaConnectors(List<KafkaConnector> kafkaConnectors) {
        this.kafkaConnectors = kafkaConnectors;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Builder
    public Member (String name) {
        this.name = name;
    }

    public void createKafkaConnector(KafkaConnector kafkaConnector) {
        kafkaConnector.registeredBy(this);
        this.kafkaConnectors.add(kafkaConnector);
    }
}