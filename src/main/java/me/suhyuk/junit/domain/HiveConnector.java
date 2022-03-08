package me.suhyuk.junit.domain;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = HiveConnector.TABLE_NAME)
@SuperBuilder
public class HiveConnector extends Connector {
    public static final String TABLE_NAME = "HIVE_CONNECTOR";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "SERVER_URL") private String serverURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HiveConnector() {
        super();
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }


}