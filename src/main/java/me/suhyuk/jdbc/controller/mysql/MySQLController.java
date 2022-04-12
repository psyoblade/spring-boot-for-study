package me.suhyuk.jdbc.controller.mysql;

import me.suhyuk.jdbc.model.mysql.Foo;
import me.suhyuk.jdbc.service.mysql.MySQLService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mysql")
public class MySQLController {

    private final MySQLService mysqlService;

    public MySQLController(MySQLService mysqlService) {
        this.mysqlService = mysqlService;
    }

    @GetMapping("foo")
    public String foo() {
        return "foo";
    }

    @PostMapping("foo")
    public Foo createFoo(Foo foo) {
        return mysqlService.save(foo);
    }


    @GetMapping("name")
    public Foo name(@RequestParam String name) {
        return mysqlService.findByName(name);
    }
}
