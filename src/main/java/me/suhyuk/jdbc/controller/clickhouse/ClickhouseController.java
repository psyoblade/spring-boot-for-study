package me.suhyuk.jdbc.controller.clickhouse;

import me.suhyuk.jdbc.service.clickhouse.ClickhouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clickhouse")
public class ClickhouseController {

    private final ClickhouseService clickhouseService;

    public ClickhouseController(ClickhouseService clickhouseService) {
        this.clickhouseService = clickhouseService;
    }

    @GetMapping("/database")
    public List<String> showDatabases() {
        return clickhouseService.showDatabases();
    }

    @GetMapping("/table")
    public List<String> showTables() {
        return clickhouseService.showTables();
    }

    @GetMapping("/table/{tableName}")
    public List<String> describeTable(@PathVariable("tableName") String tableName) {
        return clickhouseService.describeTable(tableName);
    }
}
