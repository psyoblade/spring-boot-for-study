package me.suhyuk.jdbc.service;

import me.suhyuk.jdbc.repository.ClickHouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClickhouseService implements IClickhouseService {

    private final ClickHouseRepository clickHouseRepository;

    public ClickhouseService(ClickHouseRepository clickHouseRepository) {
        this.clickHouseRepository = clickHouseRepository;
    }

    @Override
    public List<String> showDatabases() {
        return clickHouseRepository.showDatabases();
    }

    @Override
    public List<String> showTables() {
        return clickHouseRepository.showTables();
    }

    @Override
    public List<String> describeTable(String tableName) {
        return clickHouseRepository.describeTable(tableName);
    }
}
