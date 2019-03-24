package com.kimambo.mobimeo;

import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.domain.ScheduleItem;
import com.kimambo.mobimeo.domain.Stop;
import com.kimambo.mobimeo.exceptions.ScheduleNotFoundException;
import com.kimambo.mobimeo.repository.*;
import com.kimambo.mobimeo.service.ScheduleService;
import com.kimambo.mobimeo.service.ScheduleServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ScheduleServiceTest {

    private ScheduleService scheduleService;

    @Before
    public void setUp() {

        ScheduleItem scheduleItem = new ScheduleItem(new Line(1, "M13"), new Stop(1, 5, 5), LocalTime.of(10, 10, 10), 5);
        ScheduleItem scheduleItem2 = new ScheduleItem(new Line(1, "M13"), new Stop(1, 5, 5), LocalTime.of(10, 10, 10), 5);
        List<ScheduleItem> schedule = new ArrayList<>();
        schedule.add(scheduleItem);

        List<ScheduleItem> multiItemSchedule = new ArrayList<>();
        multiItemSchedule.add(scheduleItem);
        multiItemSchedule.add(scheduleItem2);


        // setup single item return
        Mockito.when(scheduleRepository.getScheduleByStopAndTime(5, 5, LocalTime.of(10, 10, 10))).thenReturn(schedule);

        // setup multiItem return
        Mockito.when(scheduleRepository.getScheduleByStopAndTime( 3,3, LocalTime.of(3,3,3))).thenReturn(multiItemSchedule);

        scheduleService = new ScheduleServiceImpl(scheduleRepository);

    }


    @MockBean
    private ScheduleRepository scheduleRepository;

    @Test
    public void ShouldThrowScheduleNotFoundExceptionWhenNoScheduleIsReturned() {
        try {
            ScheduleItem scheduleItem = scheduleService.getVehicleForGivenTimeAndStop(10, 10, LocalTime.of(0, 0, 0));
        } catch (ScheduleNotFoundException s) {
            String exceptionText = "Could not retrieve schedule for stop location 10 10 and time 00:00";
            Assert.assertEquals(exceptionText, s.getMessage());
        }
    }

    @Test
    public void ShouldReturnAVehicleForAGivenStopAndTime() throws ScheduleNotFoundException {

        ScheduleItem scheduleItem = scheduleService.getVehicleForGivenTimeAndStop(5, 5, LocalTime.of(10, 10, 10));
        String expectedLine = "M13";
        Assert.assertEquals(expectedLine, scheduleItem.getLine().getName());
    }

    @Test
    public void ShouldThrowAnExceptionWhenMoreThanOneVehicleIsReturnedForAgivenStop(){

        try {
            ScheduleItem scheduleItem = scheduleService.getVehicleForGivenTimeAndStop(3, 3, LocalTime.of(3, 3, 3));
        } catch (ScheduleNotFoundException s) {
            String exceptionText = "More than one vehicle found for stop location 3 3 and time 03:03:03";
            Assert.assertEquals(exceptionText, s.getMessage());
        }
    }
}
