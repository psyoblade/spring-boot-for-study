package me.suhyuk.jdbc.service.clickhouse;

import me.suhyuk.jdbc.model.AirlineFlightsNumber;
import me.suhyuk.jdbc.repository.clickhouse.StatisticsAirlinesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsAirlinesService {

    private StatisticsAirlinesRepository statisticsAirlinesRepository;

    public StatisticsAirlinesService(StatisticsAirlinesRepository statisticsAirlinesRepository) {
        this.statisticsAirlinesRepository = statisticsAirlinesRepository;
    }

    public List<AirlineFlightsNumber> getFlightsByYear(int year) {
        return statisticsAirlinesRepository.getFlightsByYear(year);
    }
}
