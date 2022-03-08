package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = BasePath.TABLE_NAME)
@SuperBuilder
public class BasePath {
    public static final String TABLE_NAME = "BASE_PATH";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "BASE_PATH") private String basePath;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public BasePath() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}