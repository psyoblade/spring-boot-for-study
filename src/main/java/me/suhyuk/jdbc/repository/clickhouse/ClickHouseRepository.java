package me.suhyuk.jdbc.repository.clickhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class ClickHouseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClickHouseRepository() {
    }

//    private final JdbcTemplate jdbcTemplate;
//
//    public ClickHouseRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }


    public List<String> showDatabases() {
        String query = "show databases";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public List<String> showTables() {
        String query = "show tables";
        return jdbcTemplate.queryForList(query, String.class);
    }

    public List<String> describeTable(String tableName) {
        String query = String.format("desc %s", tableName);
        List<Map<String, Object>> describe = jdbcTemplate.queryForList(query);
//        describe.stream().forEach(column -> System.out.println(column.get("name")));
        return Arrays.asList(describe.stream().map(column ->
                column.get("name").toString() + ":" + column.get("type").toString()
        ).toArray(String[]::new));
    }
}
