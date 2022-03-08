package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = KafkaConnector.TABLE_NAME)
@SuperBuilder
public class KafkaConnector extends Connector {
    public static final String TABLE_NAME = "KAFKA_CONNECTOR";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "SERVER_URL") private String serverURL;

    @ElementCollection @CollectionTable(name = "BROKERS", joinColumns = @JoinColumn(name = "ID")) private List<String> brokers;

    public List<String> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<String> brokers) {
        this.brokers = brokers;
    }

    public KafkaConnector() {
        super();
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne @JoinColumn(name = "MEMBER_ID") private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void registeredBy(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "KafkaConnector{" + "id=" + id + ", serverURL='" + serverURL + '\'' + '}';
    }
}