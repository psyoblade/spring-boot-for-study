package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table()
@SuperBuilder
public class LocalPath extends BasePath {

    public LocalPath() {
        super();
    }

    @Column(name = "HOST_NAME", length = 50) private String hostName;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}