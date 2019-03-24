package com.kimambo.mobimeo.domain;

import java.time.LocalTime;

/**
 * Metadata class use to construct ScheduleItem objects
 */
public class Time {

    public Time(  int lineId, int stopId, LocalTime arrivalTime){
        this.stopId = stopId;
        this.lineId = lineId;
        this.arrivalTime = arrivalTime;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    private int id;
    private int stopId;
    private int lineId;
    private LocalTime arrivalTime;
}
