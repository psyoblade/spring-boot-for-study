package me.suhyuk.jdbc.repository.mysql;

import me.suhyuk.jdbc.model.mysql.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MySQLRepository extends JpaRepository<Foo, Long> {
    Optional<Foo> findByName(String name);
}
