package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table()
@SuperBuilder
public class LocalPath extends BasePath {
    @Column(name = "LOCAL_PATH") private String localPath;

    public LocalPath() {
        super();
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}