package com.kimambo.mobimeo.repository;
import com.kimambo.mobimeo.domain.Time;

import java.time.LocalTime;

public class TimesParser implements MobimeoParser<Time> {
    @Override
    public Time parse(String[] input) {

        try {
            int lineid = Integer.parseInt(input[0]);
            int stopId = Integer.parseInt(input[1]);
            LocalTime arrivalTime = LocalTime.parse(input[2]);
            Time time = new Time(lineid, stopId, arrivalTime);

            return time;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
