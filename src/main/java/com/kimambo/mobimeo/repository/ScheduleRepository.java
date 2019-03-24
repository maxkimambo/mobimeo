package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.ScheduleItem;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository {
    List<ScheduleItem> getSchedule();

    List<ScheduleItem> getScheduleByStop(int id);

    List<ScheduleItem> getScheduleByLineId(int id);

    List<ScheduleItem> getScheduleByLineName(String name);

    List<ScheduleItem> getScheduleByStop(int x, int y);

    List<ScheduleItem> getScheduleByStopAndTime(int x, int y, LocalTime time);
}
