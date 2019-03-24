package com.kimambo.mobimeo.service;

import com.kimambo.mobimeo.domain.ScheduleItem;
import com.kimambo.mobimeo.exceptions.ScheduleNotFoundException;
import com.kimambo.mobimeo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {

        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleItem getVehicleForGivenTimeAndStop(int x, int y, LocalTime time) throws ScheduleNotFoundException {

        List<ScheduleItem> schedule = scheduleRepository.getScheduleByStopAndTime(x, y, time);

        if (schedule == null || schedule.size() == 0) {
            throw new ScheduleNotFoundException("Could not retrieve schedule for stop location " + x + " " + y + " and time " + time.toString());
        }

        if (schedule.size() > 1) {
            throw new ScheduleNotFoundException("More than one vehicle found for stop location " + x + " " + y + " and time " + time.toString());
        }
        Optional<ScheduleItem> item = schedule.stream().findFirst();

        return item.orElse(null);
    }

    /**
     *
     * @param x coordinates
     * @param y coordinates
     * @param time requested Time
     * @return List of schedule items
     * @throws ScheduleNotFoundException
     */
    @Override
    public List<ScheduleItem> getNextVehicleForGivenStop(int x, int y, LocalTime time) throws ScheduleNotFoundException{
        List<ScheduleItem> schedule = scheduleRepository.getScheduleByStop(x, y);
        List<ScheduleItem> stopSchedule =  schedule.stream()
                .filter(s -> s.getPlannedArrival().isAfter(time))
                .collect(Collectors.toList());
        if (schedule.size() ==0){
            throw new ScheduleNotFoundException("No vehicles found for this time stop combination");
        }
        return stopSchedule;
    }
}
