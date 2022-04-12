package me.suhyuk.jdbc.service.mysql;

import me.suhyuk.jdbc.model.mysql.Foo;
import me.suhyuk.jdbc.repository.mysql.MySQLRepository;
import org.springframework.stereotype.Service;

@Service
public class MySQLService {

    private final MySQLRepository mysqlRepository;

    public MySQLService(MySQLRepository mysqlRepository) {
        this.mysqlRepository = mysqlRepository;
    }

    public Foo findByName(String name) {
        return mysqlRepository.findByName(name).orElse(Foo.builder().name("anonymous").build());
    }

    public Foo save(Foo foo) {
        return mysqlRepository.save(foo);
    }
}
