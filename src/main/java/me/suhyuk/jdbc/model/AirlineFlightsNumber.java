package me.suhyuk.jdbc.model;


import lombok.Builder;
import lombok.Getter;

@Getter
public class AirlineFlightsNumber {

    private final String airline;
    private final long flights;

    @Builder
    public AirlineFlightsNumber(String airline, long flights) {
        this.airline = airline;
        this.flights = flights;
    }

}
