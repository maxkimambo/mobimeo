package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.ScheduleItem;
import com.kimambo.mobimeo.domain.Time;
import com.kimambo.mobimeo.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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


        try {
            schedule = new ArrayList<>();

            // parse csvfile
            TimesParser scheduleParser = new TimesParser();
            CsvReader<Time> timesReader = new CsvReaderImpl<>("./data/times.csv", scheduleParser);

            List<Time> scheduleItems = timesReader.getValues();
            for (Time time : scheduleItems){

                schedule.add(new ScheduleItem(linesRepository.getById(time.getLineId()),
                stopRepository.getById(time.getStopId()),
                time.getArrivalTime(),
                delayRepository.getDelayByLineName(linesRepository.getById(time.getLineId()).getName())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }


    }

    private void init(){

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
