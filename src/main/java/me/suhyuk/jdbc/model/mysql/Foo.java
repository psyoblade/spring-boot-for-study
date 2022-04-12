package me.suhyuk.jdbc.model.mysql;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = Foo.TABLE_NAME)
@NoArgsConstructor
public class Foo {
    public static final String TABLE_NAME = "FOO";
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID", nullable = false) private Long id;

    @Column(name = "NAME", length = 50) private String name;

    @Builder
    public Foo(String name) {
        this.name = name;
    }

}