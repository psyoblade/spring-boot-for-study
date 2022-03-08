package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = ClickhouseConnector.TABLE_NAME)
@SuperBuilder
public class ClickhouseConnector extends Connector {
    public static final String TABLE_NAME = "CLICKHOUSE_CONNECTOR";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "JDBC_CONNECTION_STRING") private String jdbcConnectionString;

    public ClickhouseConnector() {
        super();
    }

    public String getJdbcConnectionString() {
        return jdbcConnectionString;
    }

    public void setJdbcConnectionString(String jdbcConnectionString) {
        this.jdbcConnectionString = jdbcConnectionString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}