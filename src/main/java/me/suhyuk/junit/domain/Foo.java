package me.suhyuk.junit.domain;

import org.hibernate.cfg.JPAIndexHolder;

import javax.persistence.*;

@Entity
@Table(name = Foo.TABLE_NAME)
public class Foo {
    public static final String TABLE_NAME = "FOO";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "NAME", length = 50) private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}