package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
public class Connector {
    private String name;
    private String createdBy;
    private LocalDateTime createdTime;
    public Connector() {}
}