package me.suhyuk.jdbc.service.h2;

import me.suhyuk.jdbc.model.h2.Foo;
import me.suhyuk.jdbc.repository.h2.H2Repository;
import org.springframework.stereotype.Service;

@Service
public class H2Service {

    private final H2Repository h2Repository;

    public H2Service(H2Repository h2Repository) {
        this.h2Repository = h2Repository;
    }

    public Foo findByName(String name) {
        return h2Repository.findByName(name).orElse(Foo.builder().name("anonymous").build());
    }

    public Foo save(Foo foo) {
        return h2Repository.save(foo);
    }
}
