package com.kimambo.mobimeo;

import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.domain.ScheduleItem;
import com.kimambo.mobimeo.domain.Stop;
import com.kimambo.mobimeo.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.List;

@RunWith(SpringRunner.class)
public class ScheduleRepositoryTest {

    @TestConfiguration
    static class ScheduleRepositoryTestContextConfiguration {

        @Bean
        public ScheduleRepository scheduleRepository(){
            return new ScheduleRepositoryImpl(new LinesRepositoryImpl(), new StopRepositoryImpl(), new DelayRepositoryImpl());
        }
    }

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Test
    public void ShouldInitializeCorrectly(){

        List<ScheduleItem> schedule = scheduleRepository.getSchedule();
        int expected = 15;
        Assert.assertEquals(expected, schedule.size());
    }

    @Test
    public void ShouldReturnScheduleForAStop(){

        List<ScheduleItem> result = scheduleRepository.getScheduleByStop(1,7);

        int expected = 1;

        Assert.assertEquals(expected, result.size());
    }
    @Test
    public void ShouldReturnReturnSchduleForAStopGivenId(){

        List<ScheduleItem> result = scheduleRepository.getScheduleByStop(2);

        int expected = 1;

        Assert.assertEquals(expected, result.size());
    }

    @Test
    public void ShouldReturnVehicleForAStop(){
        List<ScheduleItem> result = scheduleRepository.getScheduleByStop(2);
        Line line = null;

        for(ScheduleItem si : result){
            line = si.getLine();
        }

        String expected = "M4";

        Assert.assertEquals(expected, line.getName());
    }

    @Test
    public void ShouldReturnVehicleForGivenStopAndTime(){
        LocalTime time = LocalTime.of(10,7,0);

        List<ScheduleItem> result = scheduleRepository.getScheduleByStopAndTime(2,9, time);

        Line line = null;
        Stop stop = null;

        for(ScheduleItem si: result){
            line = si.getLine();
            stop = si.getStop();
        }

        String expected  = "M4";
        Stop expectedStop = new Stop(3,2,9);

        Assert.assertEquals(expected, line.getName());
        Assert.assertEquals(expectedStop, stop);

    }


}
