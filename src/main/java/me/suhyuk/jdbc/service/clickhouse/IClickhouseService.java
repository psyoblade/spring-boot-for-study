package me.suhyuk.jdbc.service.clickhouse;

import java.util.List;

public interface IClickhouseService {
    List<String> showDatabases();

    List<String> showTables();

    List<String> describeTable(String tableName);
}
