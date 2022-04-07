package me.suhyuk.jdbc.repository.h2;

import me.suhyuk.jdbc.model.h2.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface H2Repository extends JpaRepository<Foo, Long> {
    Optional<Foo> findByName(String name);
}
