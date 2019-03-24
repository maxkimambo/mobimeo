package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.domain.ScheduleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private List<ScheduleItem> schedule;

    private LinesRepository linesRepository;
    private StopRepository stopRepository;
    private DelayRepository delayRepository;

    @Autowired
    public ScheduleRepositoryImpl(LinesRepository linesRepository, StopRepository stopRepository, DelayRepository delayRepository) {
        this.linesRepository = linesRepository;
        this.stopRepository = stopRepository;
        this.delayRepository = delayRepository;


        schedule = new ArrayList<>();

        schedule.add(new ScheduleItem(linesRepository.getById(0),
                stopRepository.getById(0),
                LocalTime.of(10, 0),
                delayRepository.getDelayByLineName(linesRepository.getById(0).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(0), stopRepository.getById(1), LocalTime.of(10, 2), delayRepository.getDelayByLineName(linesRepository.getById(0).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(0), stopRepository.getById(2), LocalTime.of(10, 5), delayRepository.getDelayByLineName(linesRepository.getById(0).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(0), stopRepository.getById(3), LocalTime.of(10, 7), delayRepository.getDelayByLineName(linesRepository.getById(0).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(0), stopRepository.getById(4), LocalTime.of(10, 9), delayRepository.getDelayByLineName(linesRepository.getById(0).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(1), stopRepository.getById(5), LocalTime.of(10, 1), delayRepository.getDelayByLineName(linesRepository.getById(1).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(1), stopRepository.getById(6), LocalTime.of(10, 4), delayRepository.getDelayByLineName(linesRepository.getById(1).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(1), stopRepository.getById(7), LocalTime.of(10, 6), delayRepository.getDelayByLineName(linesRepository.getById(1).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(1), stopRepository.getById(3), LocalTime.of(10, 8), delayRepository.getDelayByLineName(linesRepository.getById(1).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(1), stopRepository.getById(8), LocalTime.of(10, 10), delayRepository.getDelayByLineName(linesRepository.getById(1).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(2), stopRepository.getById(3), LocalTime.of(10, 8), delayRepository.getDelayByLineName(linesRepository.getById(2).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(2), stopRepository.getById(9), LocalTime.of(10, 9), delayRepository.getDelayByLineName(linesRepository.getById(2).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(2), stopRepository.getById(4), LocalTime.of(10, 11), delayRepository.getDelayByLineName(linesRepository.getById(2).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(2), stopRepository.getById(10), LocalTime.of(10, 13), delayRepository.getDelayByLineName(linesRepository.getById(2).getName())));
        schedule.add(new ScheduleItem(linesRepository.getById(2), stopRepository.getById(11), LocalTime.of(10, 15), delayRepository.getDelayByLineName(linesRepository.getById(2).getName())));


    }

    @Override
    public List<ScheduleItem> getSchedule() {
        return schedule;
    }

    @Override
    public List<ScheduleItem> getScheduleByStop(int id) {

        List<ScheduleItem> stopSchedule = schedule.stream()
                .filter(s -> s.getStop().getId().equals(id))
                .collect(Collectors.toList());

        return stopSchedule;
    }


    @Override
    public List<ScheduleItem> getScheduleByStop(int x, int y) {
        List<ScheduleItem> stopSchedule = schedule.stream()
                .filter(s -> s.getStop().equals(x, y))
                .collect(Collectors.toList());
        return stopSchedule;
    }

    @Override
    public List<ScheduleItem> getScheduleByLineId(int id) {
        List<ScheduleItem> lineSchedule = schedule.stream()
                .filter(ls -> ls.getLine().getId().equals(id))
                .collect(Collectors.toList());
        return lineSchedule;
    }

    @Override
    public List<ScheduleItem> getScheduleByLineName(String name) {
        List<ScheduleItem> lineSchedule = schedule.stream()
                .filter(ls -> ls.getLine().getName().equals(name))
                .collect(Collectors.toList());

        return lineSchedule;
    }

    public List<ScheduleItem> getScheduleByStopAndTime(int x, int y, LocalTime time) {
        List<ScheduleItem> stopSchedule = schedule.stream()
                .filter(s -> s.getStop().equals(x, y) && s.getPlannedArrival().equals(time))
                .collect(Collectors.toList());
        return stopSchedule;
    }

}
