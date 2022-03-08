package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@SuperBuilder
public class HDFSPath extends BasePath {
    @Column(name = "SCHEME", length = 50) private String scheme;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public HDFSPath() {}
}