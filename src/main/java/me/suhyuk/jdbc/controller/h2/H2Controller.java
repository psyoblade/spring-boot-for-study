package me.suhyuk.jdbc.controller.h2;

import me.suhyuk.jdbc.model.h2.Foo;
import me.suhyuk.jdbc.service.h2.H2Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/h2")
public class H2Controller {

    private final H2Service h2Service;

    public H2Controller(H2Service h2Service) {
        this.h2Service = h2Service;
    }

    @GetMapping("foo")
    public String foo() {
        return "foo";
    }

    @PostMapping("foo")
    public Foo createFoo(Foo foo) {
        return h2Service.save(foo);
    }


    @GetMapping("name")
    public Foo name(@RequestParam String name) {
        return h2Service.findByName(name);
    }
}
