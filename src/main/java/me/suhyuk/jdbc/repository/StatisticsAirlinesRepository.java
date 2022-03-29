package me.suhyuk.jdbc.repository;

import me.suhyuk.jdbc.model.AirlineFlightsNumber;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatisticsAirlinesRepository {
    private JdbcTemplate jdbcTemplate;

    public StatisticsAirlinesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AirlineFlightsNumber> getFlightsByYear(int year) {
        String queryFlightsByYear = "select CarrierDelay, sum(`DOT_ID_Reporting_Airline`) as flights " +
                "from `ontime` where Year = ? group by CarrierDelay order by flights desc";
        return jdbcTemplate.query(queryFlightsByYear, new Object[] { year }, (rs, rowNum) -> {
            return new AirlineFlightsNumber(
                    rs.getString("CarrierDelay"),
                    rs.getLong("flights")
            );
        });
    }

}
