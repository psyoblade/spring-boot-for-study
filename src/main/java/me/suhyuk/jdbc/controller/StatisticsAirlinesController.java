package me.suhyuk.jdbc.controller;

import me.suhyuk.jdbc.model.AirlineFlightsNumber;
import me.suhyuk.jdbc.service.StatisticsAirlinesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/statistics/airlines")
public class StatisticsAirlinesController {

    private StatisticsAirlinesService statisticsAirlinesService;


    public StatisticsAirlinesController(StatisticsAirlinesService statisticsAirlinesService) {
        this.statisticsAirlinesService = statisticsAirlinesService;
    }

    @GetMapping("/flights")
    public List<AirlineFlightsNumber> getFlightsByYear(@Size(min=4, max=4) @RequestParam("year") int year) {
        return statisticsAirlinesService.getFlightsByYear(year);
    }

}
