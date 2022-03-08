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

    @OneToMany(mappedBy = "member", orphanRemoval = true) private List<KafkaConnector> kafkaConnectors = new ArrayList<>();

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
    public Member(String name) {
        this.name = name;
    }

    @OneToOne(orphanRemoval = true) @JoinColumn(name = "MEMBER_DETAIL_ID") private MemberDetail memberDetail;

    public MemberDetail getMemberDetail() {
        return memberDetail;
    }

    /*
    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
    }
     */

    public void updateDetailInfo(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
    }

    @OneToOne(orphanRemoval = true) @JoinColumn(name = "MEMBER_LOCKER_ID") private MemberLocker memberLocker;

    public MemberLocker getMemberLocker() {
        return memberLocker;
    }

    /**
    public void setMemberLocker(MemberLocker memberLocker) {
        this.memberLocker = memberLocker;
    }
     */

    public void registerLocker(MemberLocker memberLocker) {
        memberLocker.setMember(this);
        this.memberLocker = memberLocker;
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

    public void createKafkaConnector(KafkaConnector kafkaConnector) {
        kafkaConnector.createdBy(this);
        this.kafkaConnectors.add(kafkaConnector);
    }
}