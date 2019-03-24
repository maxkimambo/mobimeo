package com.kimambo.mobimeo.service;

import com.kimambo.mobimeo.domain.ScheduleItem;
import com.kimambo.mobimeo.exceptions.ScheduleNotFoundException;

import java.time.LocalTime;
import java.util.List;

public interface ScheduleService {
    ScheduleItem getVehicleForGivenTimeAndStop(int x, int y, LocalTime time) throws ScheduleNotFoundException;

    List<ScheduleItem> getNextVehicleForGivenStop(int x, int y, LocalTime time) throws ScheduleNotFoundException;
}
