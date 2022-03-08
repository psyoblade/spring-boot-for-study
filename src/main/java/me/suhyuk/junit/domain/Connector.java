package me.suhyuk.junit.domain;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
public class Connector {
    private String name;
    private LocalDateTime createdTime;
    public Connector() {}
}