package com.kimambo.mobimeo.domain;

import java.time.LocalTime;

public class ScheduleItem {

    private Stop stop;
    private Line line;
    private LocalTime actualArrival;
    private LocalTime plannedArrival;

    private ScheduleItem() {

    }

    public ScheduleItem(Line l, Stop s, LocalTime arrival, int delay) {
        this.stop = s;
        this.line = l;
        this.plannedArrival = arrival;
        this.actualArrival = arrival.plusMinutes(delay);
    }

    public Stop getStop() {
        return stop;
    }

    public Line getLine() {
        return line;
    }

    public LocalTime getPlannedArrival() {
        return plannedArrival;
    }

    public LocalTime getActualArrival() {
        return actualArrival;
    }



}
