package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = KafkaConnector.TABLE_NAME)
@SuperBuilder
public class KafkaConnector extends Connector {
    public static final String TABLE_NAME = "KAFKA_CONNECTOR";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    public KafkaConnector() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ElementCollection
    @CollectionTable(name = "BROKERS", joinColumns = @JoinColumn(name = "ID"))
    private List<String> brokers;

    public List<String> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<String> brokers) {
        this.brokers = brokers;
    }

    @ManyToOne @JoinColumn(name = "MEMBER_ID") private Member member;

    public Member getCreator() {
        return member;
    }

    public void createdBy(Member member) {
        this.member = member;
    }
}